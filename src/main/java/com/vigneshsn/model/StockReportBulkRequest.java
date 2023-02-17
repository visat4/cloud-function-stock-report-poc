package com.vigneshsn.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class StockReportBulkRequest {
    @NotBlank
    private String reportId;

    @NotBlank
    private String reportTimestamp;

    @NotEmpty
    @Valid
    List<StockReportRequest> data;
}

