package br.edu.ifpb.sr.dac.demo.service.token;

import org.springframework.security.core.Authentication;

public interface TokenService {
    String generateToken(Authentication authentication);
}
