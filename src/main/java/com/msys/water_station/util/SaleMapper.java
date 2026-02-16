package com.msys.water_station.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.msys.water_station.Model.Sale;
import com.msys.water_station.dto.sale.request.CreateSaleDTO;
import com.msys.water_station.dto.sale.response.SaleResponse;

@Mapper(componentModel = "spring", uses = { SaleItemMapper.class })

public interface SaleMapper {
    @Mapping(target = "customerName", source = "customer.fullName")
    @Mapping(target = "staffName", source = "user.fullname")
    @Mapping(target = "saleItems", source = "saleItems")
    @Mapping(target = "total", source = "totalAmount")
    SaleResponse toDTO(Sale sale);

    @Mapping(target = "user.username", source = "username")
    @Mapping(target = "customer.fullName", source = "customer")
    Sale toEntity(CreateSaleDTO createSaleDTO);
}
