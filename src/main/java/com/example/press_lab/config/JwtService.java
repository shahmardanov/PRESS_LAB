package com.example.press_lab.config;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
@RequiredArgsConstructor
public class JwtService {



    @Value("${jwt.secret-key}")
    public String secretKey;

    public Claims tokenParser(String token) {
        return Jwts.parser()
                .verifyWith((SecretKey) generatedKey(secretKey))
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }


    public String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .signWith(generatedKey(secretKey))
                .issuedAt(new Date())
                .expiration(Date.from(Instant.now().plus(Duration.ofMinutes(40))))
                .claim("claims", claims)
                .compact();
    }



    public <T> T extractClaim(String token, Function<Claims, T> claimsFunction) {
        Claims claims = tokenParser(token);
        return claimsFunction.apply(claims);
    }

    private Key generatedKey(String secretKey) {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secretKey));
    }



    public Boolean validateToken(String tokenWithoutBearer){
        Date date = extractClaim(tokenWithoutBearer, Claims::getExpiration);
        if (date.before(new Date())) {
            return true;
        } else return false;
    }





}

