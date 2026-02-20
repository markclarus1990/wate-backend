package com.msys.water_station.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.msys.water_station.dto.inventory.response.InventoryItemResponse;
import com.msys.water_station.service.inventory.InventoryService;

@RestController
@RequestMapping("/api/inventory")
public class InventoryController {
    @Autowired
    InventoryService inventoryService;

    @GetMapping
    public ResponseEntity<List<InventoryItemResponse>> getAllCustomer() {
        return new ResponseEntity<>(inventoryService.getAllInventory(), HttpStatus.OK);
    }
}
