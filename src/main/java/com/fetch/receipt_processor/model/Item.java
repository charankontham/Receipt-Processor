package com.fetch.receipt_processor.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class Item {
    @NotBlank(message = "Short description cannot be blank")
    @Pattern(regexp = "^[\\w\\s\\-]+$", message = "Invalid short description format")
    private String shortDescription;

    public @NotBlank(message = "Short description cannot be blank") @Pattern(regexp = "^[\\w\\s\\-]+$", message = "Invalid short description format") String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(@NotBlank(message = "Short description cannot be blank") @Pattern(regexp = "^[\\w\\s\\-]+$", message = "Invalid short description format") String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public @NotBlank(message = "Price cannot be blank") @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Invalid price format (e.g., 5.99)") String getPrice() {
        return price;
    }

    public void setPrice(@NotBlank(message = "Price cannot be blank") @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Invalid price format (e.g., 5.99)") String price) {
        this.price = price;
    }

    @NotBlank(message = "Price cannot be blank")
    @Pattern(regexp = "^\\d+\\.\\d{2}$", message = "Invalid price format (e.g., 5.99)")
    private String price;
}
