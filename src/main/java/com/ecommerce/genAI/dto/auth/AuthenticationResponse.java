package com.ecommerce.genAI.dto.auth;

import com.ecommerce.genAI.dto.user.UserResponse;
import lombok.Data;

@Data
public class AuthenticationResponse {
    private UserResponse user;
    private String token;
}
