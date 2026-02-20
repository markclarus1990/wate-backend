package com.msys.water_station.service.inventory;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.msys.water_station.dto.inventory.response.InventoryItemResponse;
import com.msys.water_station.repo.InventoryRepo;
import com.msys.water_station.util.InventoryMapper;

@Service
public class InventoryService {

    @Autowired
    InventoryRepo inventoryRepo;

    @Autowired
    InventoryMapper inventoryMapper;

    public List<InventoryItemResponse> getAllInventory() {

        return inventoryMapper.toDTO(inventoryRepo.findAll());
    }
}
