package com.msys.water_station.dto.sale;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SaleResponse {
    private Long id;
    private String customerName;
    private String staffName;
    private String paymentMethod;
    private String saleDate;
    private String total;
    private List<SaleItemResponse> saleItems;

}
