package com.rmage.rmage_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RegisterLandlordRequest {
    private String email;
    private String password;
    // We will add validation annotations in a later step
}
