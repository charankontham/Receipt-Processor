package com.fetch.receipt_processor.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Data
public class Receipt {
    @NotBlank
    @Pattern(regexp = "^[\\w\\s&'-]+$", message = "Invalid retailer name")
    private String retailer;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate purchaseDate;

    @NotNull
    @DateTimeFormat(pattern = "HH:mm")
    @JsonFormat(pattern = "HH:mm")
    private LocalTime purchaseTime;

    private List<Item> items;

    @NotBlank
    @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Invalid total format (e.g., 5.99)")
    private String total;

    public @NotBlank @Pattern(regexp = "^[\\w\\s&'-]+$", message = "Invalid retailer name") String getRetailer() {
        return retailer;
    }

    public void setRetailer(@NotBlank @Pattern(regexp = "^[\\w\\s&'-]+$", message = "Invalid retailer name") String retailer) {
        this.retailer = retailer;
    }

    public @NotNull LocalDate getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(@NotNull LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }

    public @NotNull LocalTime getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(@NotNull LocalTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public @NotBlank @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Invalid total format (e.g., 5.99)") String getTotal() {
        return total;
    }

    public void setTotal(@NotBlank @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Invalid total format (e.g., 5.99)") String total) {
        this.total = total;
    }
}
