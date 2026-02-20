package com.msys.water_station.service.sales;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.msys.water_station.Model.Customer;
import com.msys.water_station.Model.InventoryItem;
import com.msys.water_station.Model.InventoryMovement;
import com.msys.water_station.Model.MovementType;
import com.msys.water_station.Model.Price;
import com.msys.water_station.Model.Sale;
import com.msys.water_station.Model.SaleItem;
import com.msys.water_station.Model.User;
import com.msys.water_station.dto.sale.request.CreateSaleDTO;
import com.msys.water_station.dto.sale.request.CreateSaleItemDTO;
import com.msys.water_station.dto.sale.response.SaleResponse;
import com.msys.water_station.exceptions.CustomerNotFoundException;
import com.msys.water_station.exceptions.UserNotFoundException;
import com.msys.water_station.repo.CustomerRepo;
import com.msys.water_station.repo.InventoryMovementRepo;
import com.msys.water_station.repo.InventoryRepo;
import com.msys.water_station.repo.MovementTypeRepo;
import com.msys.water_station.repo.SaleItemRepo;
import com.msys.water_station.repo.SaleRepo;
import com.msys.water_station.repo.UserRepo;
import com.msys.water_station.util.SaleMapper;

import jakarta.transaction.Transactional;

@Service
public class SaleService {
    @Autowired
    private SaleRepo saleRepo;

    @Autowired
    private SaleMapper saleMapper;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CustomerRepo customerRepo;

    @Autowired
    InventoryRepo inventoryRepo;

    @Autowired
    SaleItemRepo saleItemRepo;

    @Autowired
    InventoryMovementRepo inventoryMovementRepo;

    @Autowired
    MovementTypeRepo movementTypeRepo;

    // Get All Sales
    public Page<SaleResponse> getAllSales(Pageable pageable) {
        Page<Sale> salesPage = saleRepo.findAll(pageable);
        return salesPage.map(saleMapper::toDTO);

    }

    @Transactional
    public SaleResponse createSale(CreateSaleDTO dto) {

        // 1️⃣ Map basic sale fields
        Sale sale = saleMapper.toEntity(dto);

        // 2️⃣ Fetch user
        User user = userRepo.findByUsername(dto.getUsername());
        if (user == null) {
            throw new UserNotFoundException("User " + dto.getUsername() + " not found");
        }

        // 3️⃣ Fetch customer
        Customer customer = customerRepo.findByFullName(dto.getCustomer());
        if (customer == null) {
            throw new CustomerNotFoundException("Customer " + dto.getCustomer() + " not found");
        }

        sale.setUser(user);
        sale.setCustomer(customer);

        BigDecimal total = BigDecimal.ZERO;
        List<SaleItem> items = new ArrayList<>();

        // 4️⃣ Fetch movement type once
        MovementType saleMovementType = movementTypeRepo
                .findByName("OUT")
                .orElseThrow(() -> new RuntimeException("Movement type OUT not configured"));

        // 5️⃣ Build full object graph BEFORE saving
        for (CreateSaleItemDTO itemDTO : dto.getItems()) {

            InventoryItem inventory = inventoryRepo.findById(itemDTO.getInventoryItemId())
                    .orElseThrow(() -> new RuntimeException("Inventory item not found"));

            BigDecimal qty = BigDecimal.valueOf(itemDTO.getQuantity());

            if (inventory.getCurrentStock().compareTo(qty) < 0) {
                throw new RuntimeException(
                        "Insufficient stock for item: " + inventory.getName());
            }

            // Get active price
            Price price = inventory.getPrices()
                    .stream()
                    .filter(Price::getActive)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("No active price found for item: " + inventory.getName()));

            BigDecimal unitPrice = price.getPrice();
            BigDecimal subTotal = unitPrice.multiply(qty);

            SaleItem saleItem = new SaleItem();
            saleItem.setSale(sale);
            saleItem.setInventoryItem(inventory);
            saleItem.setQuantity(itemDTO.getQuantity());
            saleItem.setUnitPrice(unitPrice);
            saleItem.setSubTotal(subTotal);

            items.add(saleItem);

            // deduct stock
            inventory.setCurrentStock(
                    inventory.getCurrentStock().subtract(qty));

            total = total.add(subTotal);
        }
        sale.setSaleItems(items);
        sale.setTotalAmount(total);

        // 6️⃣ Save once (cascade handles SaleItems)
        Sale savedSale = saleRepo.save(sale);

        // 7️⃣ Log inventory movements AFTER sale has ID
        for (SaleItem item : savedSale.getSaleItems()) {

            InventoryMovement movement = new InventoryMovement();
            movement.setCreatedBy(user);
            movement.setInventoryItem(item.getInventoryItem());
            movement.setMovementType(saleMovementType);
            movement.setQuantity(
                    BigDecimal.valueOf(item.getQuantity()).negate());
            movement.setReferenceTypeId(1L); // SALE
            movement.setReferenceId(savedSale.getId());

            inventoryMovementRepo.save(movement);
        }

        return saleMapper.toDTO(savedSale);
    }

}
