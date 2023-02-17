package com.vigneshsn.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class StockReportRequest {
    @NotBlank
    private String itemNumber;

    @NotBlank
    private String itemType;

    @NotNull
    private String unitOfMeasure;

    @NotNull
    private BigDecimal onHandQuantity;

    @NotNull
    private List<StorageAreaRequest> storage;

    @Valid
    @NotNull
    private List<ReservedStockReportItemRequest> reserved;
}
