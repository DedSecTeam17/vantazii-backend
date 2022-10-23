package com.example.vantazii.core.security.auth.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class NewCustomerResponse {
    private String message;
    private boolean isNew;
}
