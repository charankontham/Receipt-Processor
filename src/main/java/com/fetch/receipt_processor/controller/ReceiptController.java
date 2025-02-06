package com.fetch.receipt_processor.controller;

import com.fetch.receipt_processor.model.GetPointsResponse;
import com.fetch.receipt_processor.model.Receipt;
import com.fetch.receipt_processor.model.ReceiptResponse;
import com.fetch.receipt_processor.service.ReceiptService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/receipts")
public class ReceiptController {
    private final ReceiptService receiptService;

    public ReceiptController(ReceiptService receiptService) {
        this.receiptService = receiptService;
    }

    @PostMapping("/process")
    public ResponseEntity<ReceiptResponse> processReceipt(@Valid @RequestBody Receipt receipt) {
        String id = receiptService.processReceipt(receipt);
        return ResponseEntity.ok(new ReceiptResponse(id));
    }

    @GetMapping("/{id}/points")
    public ResponseEntity<GetPointsResponse> getPoints(@PathVariable String id) {
        int points = receiptService.getPoints(id);
        return ResponseEntity.ok(new GetPointsResponse(points));
    }
}
