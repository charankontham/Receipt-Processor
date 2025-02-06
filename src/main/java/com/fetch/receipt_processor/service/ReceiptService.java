package com.fetch.receipt_processor.service;

import com.fetch.receipt_processor.exception.ReceiptNotFoundException;
import com.fetch.receipt_processor.model.Receipt;
import org.springframework.stereotype.Service;

import java.time.LocalTime;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class ReceiptService {
    private final Map<String, Receipt> receipts = new ConcurrentHashMap<>();

    public String processReceipt(Receipt receipt) {
        String id = UUID.randomUUID().toString();
        receipts.put(id, receipt);
        return id;
    }

    public int getPoints(String id) {
        Receipt receipt = receipts.get(id);
        if (receipt == null) {
            throw new ReceiptNotFoundException("Receipt not found");
        }
        return calculatePoints(receipt);
    }

    private int calculatePoints(Receipt receipt) {
        AtomicInteger points = new AtomicInteger();
        points.addAndGet(receipt.getRetailer().replaceAll("[^a-zA-Z0-9]", "").length());
        if (receipt.getTotal().endsWith(".00")) {
            points.addAndGet(50);
        }

        double total = Double.parseDouble(receipt.getTotal());
        if ((total * 100) % 25 == 0) {
            points.addAndGet(25);
        }

        int itemCount = receipt.getItems().size();
        points.addAndGet((itemCount / 2) * 5);

        receipt.getItems().forEach(item -> {
            String trimmedDesc = item.getShortDescription().trim();
            if (trimmedDesc.length() % 3 == 0) {
                double price = Double.parseDouble(item.getPrice());
                points.addAndGet((int) Math.ceil(price * 0.2));
            }
        });

        if (receipt.getPurchaseDate().getDayOfMonth() % 2 != 0) {
            points.addAndGet(6);
        }

        LocalTime time = receipt.getPurchaseTime();
        if (time.isAfter(LocalTime.of(14, 0)) && time.isBefore(LocalTime.of(16, 0))) {
            points.addAndGet(10);
        }

        return points.get();
    }
}
