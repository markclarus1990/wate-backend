package com.msys.water_station.service.sales;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.msys.water_station.Model.Customer;
import com.msys.water_station.Model.Sale;
import com.msys.water_station.Model.User;
import com.msys.water_station.dto.sale.request.CreateSaleDTO;
import com.msys.water_station.dto.sale.response.SaleResponse;
import com.msys.water_station.exceptions.CustomerNotFoundException;
import com.msys.water_station.exceptions.UserNotFoundException;
import com.msys.water_station.repo.CustomerRepo;
import com.msys.water_station.repo.SaleRepo;
import com.msys.water_station.repo.UserRepo;
import com.msys.water_station.util.SaleMapper;

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

    // Get All Sales
    public Page<SaleResponse> getAllSales(Pageable pageable) {
        Page<Sale> salesPage = saleRepo.findAll(pageable);
        return salesPage.map(saleMapper::toDTO);

    }

    // Create Sale
    public SaleResponse createSale(CreateSaleDTO createSaleDTO) {
        System.out.println("dtox" + createSaleDTO.getUsername());
        Sale sale = saleMapper.toEntity(createSaleDTO);
        User user = userRepo.findByUsername(createSaleDTO.getUsername());

        if (user == null) {
            throw new UserNotFoundException("user " + createSaleDTO.getUsername() + " not found");
        }

        Customer customer = customerRepo.findByFullName(createSaleDTO.getCustomer());
        if (customer == null) {
            throw new CustomerNotFoundException("Customer " + createSaleDTO.getCustomer() + " not found");
        }

        sale.setCustomer(customer);
        sale.setUser(user);
        Sale saveCreatedSale = sale;
        saleRepo.save(saveCreatedSale);
        return saleMapper.toDTO(saveCreatedSale);

    }
}
