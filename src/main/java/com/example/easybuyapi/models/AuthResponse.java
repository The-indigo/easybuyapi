package com.example.easybuyapi.models;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthResponse {
    private int id;
    private String email;
    private String fullname;
    private String phoneNumber;
    private String address;
private Role role;
    private String token;
}
