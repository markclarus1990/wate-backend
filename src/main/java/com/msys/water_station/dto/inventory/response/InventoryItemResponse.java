package com.msys.water_station.dto.inventory.response;

import lombok.Data;
import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class InventoryItemResponse {
    private Long id;
    private String name;
    private BigDecimal price;
}