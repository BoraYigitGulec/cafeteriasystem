package com.example.YemekhaneB.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class TokenService {
    private static final String  secretKey = "kyuj33ghTyiXhXay9ZV9OyYJLJthR4Jv75qxu7Fq7027/XhxakDWIV1Pu2xU59uo";
    private static final  int Validity =  5*60*1000*12;
    public String generateToken(UserDetails userDetails){
        return generateToken(new HashMap<>(),userDetails);
    }
    public  String generateToken(Map<String,Object> extraClaims, UserDetails userDetails){
        long currentTimeMillis = System.currentTimeMillis();
        Date startTimer = new Date(currentTimeMillis);
        Date expirationTimer = new Date(currentTimeMillis + Validity);
        return Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuer("yemekhanesistemi")
                .setIssuedAt(startTimer)
                .setExpiration(expirationTimer)
                .signWith(SignatureAlgorithm.HS256,getSigningKey())
                .compact();
    }
    public boolean tokenValidate(String token,UserDetails userDetails){
        final String username = getUsernameToken(token);
        return( getUsernameToken(token)!= null && !isExpired(token)&& username .equals(userDetails.getUsername()));
    }
    public String getUsernameToken(String token){
       /*Claims claims= getClaims(token);
        return claims.getSubject();*/
        return extractClaim(token, Claims::getSubject);

    }
    public boolean isExpired(String token){
        Claims claims = getClaims(token);
        return claims.getExpiration().before(new Date(System.currentTimeMillis()));
    }
    public <T> T extractClaim(String token, Function<Claims,T> claimsResolver){
        final Claims claims = getClaims(token);
        return claimsResolver.apply(claims);
    }
    private  Claims getClaims(String token) {
        Claims claims = Jwts.parser().setSigningKey(getSigningKey()).parseClaimsJws(token).getBody();
        return claims;
    }

    private Key getSigningKey() {
        byte[] keyBytes = Base64.getDecoder().decode(secretKey);
        return new SecretKeySpec(keyBytes, "HmacSHA256");    }


}
