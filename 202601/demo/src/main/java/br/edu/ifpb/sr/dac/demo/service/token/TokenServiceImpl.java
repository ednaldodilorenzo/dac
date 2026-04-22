package br.edu.ifpb.sr.dac.demo.service.token;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TokenServiceImpl implements TokenService {

    private final Key key;

    public TokenServiceImpl(@Value("${JWT_SECRET_KEY}") String secret) {
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(Authentication authentication) {

        Instant now = Instant.now();
        Instant expiresAt = now.plusSeconds(3600);

        List<String> roles = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).filter(Objects::nonNull).map(role -> role.replace("ROLE_", "")).collect(Collectors.toList());

        return Jwts.builder().subject(authentication.getName()).claim("roles", roles).issuedAt(Date.from(now)).expiration(Date.from(expiresAt)).signWith(key).compact();
    }
}