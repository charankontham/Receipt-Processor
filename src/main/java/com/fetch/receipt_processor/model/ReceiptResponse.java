package com.fetch.receipt_processor.model;

import lombok.Data;

@Data
public class ReceiptResponse {
    private final String id;

    public ReceiptResponse(String id) {
        this.id = id;
    }
}
