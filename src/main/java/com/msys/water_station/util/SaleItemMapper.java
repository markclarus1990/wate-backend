package com.msys.water_station.util;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.msys.water_station.Model.SaleItem;
import com.msys.water_station.dto.sale.response.SaleItemResponse;

@Mapper(componentModel = "spring")
public interface SaleItemMapper {

    @Mapping(target = "itemName", source = "inventoryItems.name")
    SaleItemResponse toDTO(SaleItem saleItem);
}
