package org.example.springsecurity.utils;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.KEM;
import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtil {

    private final long EXPIRATION = 1000*60;
    private final String SECRERT =" im a good and bad guy in everone life at oppoint of tome  12234455r";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRERT.getBytes());


    public String generateToken(String username){



        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()+EXPIRATION))
                .signWith(key)
                .compact();


    }

    public String extractusername(String token){

        Claims body = getBody(token);

        return body.getSubject();
    }

    private Claims getBody(String token) {
        Claims body = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return body;
    }

    public boolean validateToken(String username, UserDetails userdetails,String token) {

        return username.equals(userdetails.getUsername())&&!isTokenExpired(token);


    }

    public boolean isTokenExpired(String token){

        return getBody(token).getExpiration().before(new Date());
        
        
    }
}
