package com.oliveira.willy.starwarsresistence.config.security.jwt;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UsernameAndPasswordAuthenticationRequest {
    private String username;
    private String password;
}
