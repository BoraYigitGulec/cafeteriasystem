package com.example.YemekhaneB.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PurchaseRequest {
    private Long purchaseid;
    private String username;
    private Long amount;
    private Long workplaceid;
}
