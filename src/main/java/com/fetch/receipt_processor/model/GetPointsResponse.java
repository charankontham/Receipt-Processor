package com.fetch.receipt_processor.model;

import lombok.Data;

@Data
public class GetPointsResponse {
    private final int points;

    public GetPointsResponse(int points) {
        this.points = points;
    }
}
