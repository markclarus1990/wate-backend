package com.msys.water_station.util;

import java.util.List;

import org.mapstruct.Mapper;

import com.msys.water_station.Model.InventoryItem;
import com.msys.water_station.dto.inventory.response.InventoryItemResponse;

@Mapper(componentModel = "spring")
public interface InventoryMapper {
    List<InventoryItemResponse> toDTO(List<InventoryItem> inventoryItem);
}
