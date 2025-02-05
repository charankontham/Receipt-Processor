package com.fetch.receipt_processor.model;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Receipt {
    @NotBlank
    @Pattern(regexp = "^[\\w\\s\\-&]+$")
    private String retailer;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private LocalDate purchaseDate;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    private LocalTime purchaseTime;

    @Valid
    @Size(min = 1)
    private List<Item> items;

    @NotBlank
    @Pattern(regexp = "^\\d+\\.\\d{2}$")
    private String total;
}
