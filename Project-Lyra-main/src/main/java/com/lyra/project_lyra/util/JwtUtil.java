package com.lyra.project_lyra.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.log4j.Log4j2;

import java.util.Date;

@Log4j2
public class JwtUtil {

    // Token에서 username 확인하기
    public static String getUserName(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().get("userName", String.class);
    }

    // Token 확인하여 파기시간 확인
    public static boolean isExpired(String token, String secretKey) {
        return Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
                .getBody().getExpiration().before(new Date());
    }

    // Token 발행하는 메서드
    public static String createJwt(String username, String secretKey, Long expriedMs) {
        Claims claims = Jwts.claims();
        claims.put("userName", username);   // claims에 username을 담기
        log.info("username =======> " + username);
        log.info("sk ============> " + secretKey);
        return Jwts.builder()
                .setClaims(claims)  // set 해줌
                .setIssuedAt(new Date(System.currentTimeMillis()))  // 생성 시간
                .setExpiration(new Date(System.currentTimeMillis() + expriedMs))    // 파기 시간
                .signWith(SignatureAlgorithm.HS256, secretKey)   // SignatureAlgorithm 종류
                .compact();
    }
}
