package ru.team.scheduler.oapi.config;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Base64;
import java.util.Date;

@Slf4j
@Service
public class JwtProvider {

//   private String secretKey = "45T47fcXazP5pgMmWXKByCZWpWUzQrZrJMVegYVbcxhXFk22uVDWS9TAzpwmq8ZG";

    @Value("${jwt.ttl:3600}")
    private long tokenTtl;

    private Key key;

    @Value("${jwt.secret}")
    private String secretKey;

//    @Autowired
//    public JwtProvider (@Value("${jwt.secret}" ) String secretKey){
//
//    }

    public String createToken(String username) {
        Date issuedDate = new Date();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(issuedDate)
                .setExpiration(Date.from(LocalDate.now().plusDays(2).atStartOfDay(ZoneId.systemDefault()).toInstant()))
                .signWith(getKey())
                .compact();
    }

    public boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        }
    }

    public String getUsernameFromToken(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(getKey())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return null;
        }
    }

    private Key getKey() {
        System.out.println("secretKey " + secretKey);
        if (key == null) {
            if (secretKey != null) {
                byte[] decodeKey = Base64.getDecoder().decode(secretKey);
                key = new SecretKeySpec(decodeKey, 0, decodeKey.length, SignatureAlgorithm.HS256.getJcaName());
            } else {
               key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
            }
        }
        return key;
    }
}
