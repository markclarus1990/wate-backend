package com.msys.water_station.dto.sale;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleItemResponse {
    private Long id;
    private BigDecimal quantity;
    private BigDecimal unitPrice;
    private BigDecimal subTotal;
    private String itemName;
}
