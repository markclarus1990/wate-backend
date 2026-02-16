package com.msys.water_station.dto.sale.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSaleDTO {
    @NotBlank
    private String username;
    @NotBlank
    private String customer;
    @NotNull
    private BigDecimal totalAmount;
    @NotBlank
    private String paymentMethod;
    @NotBlank
    private String status;

    private LocalDateTime saleDate = LocalDateTime.now();
    private List<CreateSaleItemDTO> items;
}
