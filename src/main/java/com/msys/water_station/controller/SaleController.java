package com.msys.water_station.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.msys.water_station.service.SaleService;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    @Autowired
    SaleService saleService;

    @GetMapping()
    ResponseEntity<?> getAllSales(Pageable pageable) {

        return ResponseEntity.ok(saleService.getAllSales(pageable));
    }
}
