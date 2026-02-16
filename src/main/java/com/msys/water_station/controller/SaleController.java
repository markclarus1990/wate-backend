package com.msys.water_station.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msys.water_station.dto.sale.request.CreateSaleDTO;
import com.msys.water_station.service.sales.SaleService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    SaleService saleService;

    // Get All Sales
    @GetMapping()
    ResponseEntity<?> getAllSales(Pageable pageable) {

        return ResponseEntity.ok(saleService.getAllSales(pageable));
    }

    // Create Sales
    @PostMapping("create-sale")
    ResponseEntity<?> createSales(@Valid @RequestBody CreateSaleDTO createSaleDTO) {

        return ResponseEntity.ok(saleService.createSale(createSaleDTO));
    }

}
