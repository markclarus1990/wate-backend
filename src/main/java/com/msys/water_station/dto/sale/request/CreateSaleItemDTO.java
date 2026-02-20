package com.msys.water_station.dto.sale.request;

import java.math.BigDecimal;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CreateSaleItemDTO {

    @NotNull
    private Long inventoryItemId;

    @NotNull
    @Min(1)
    private Integer quantity;

    @NotNull
    private BigDecimal unitPrice;

    // Optional: you can calculate this in backend instead
    private BigDecimal subTotal;
}