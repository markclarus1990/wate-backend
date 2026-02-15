package com.msys.water_station.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.msys.water_station.Model.Sale;
import com.msys.water_station.dto.sale.SaleResponse;
import com.msys.water_station.repo.SaleRepo;
import com.msys.water_station.util.SaleMapper;

@Service
public class SaleService {
    @Autowired
    private SaleRepo saleRepo;

    @Autowired
    private SaleMapper saleMapper;

    public Page<SaleResponse> getAllSales(Pageable pageable) {
        Page<Sale> salesPage = saleRepo.findAll(pageable);
        return salesPage.map(saleMapper::toDTO);

    }
}
