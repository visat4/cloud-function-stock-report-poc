package com.vigneshsn.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.math.BigDecimal;

@Data

public class ReservedStockReportItemRequest {
    @Size(max = 5)
    private String supplierBuCode;

    @Size(max = 3)
    private String supplierBuType;

    private String prodDate;

    @NotBlank
    @Size(max = 20)
    private String locationId;

    @NotNull
    private Integer storageArea;

    @NotNull
    private BigDecimal quantity;
}
