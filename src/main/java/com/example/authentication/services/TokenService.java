package com.example.authentication.services;

import com.example.authentication.models.AppUser;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class TokenService {

@Autowired UserService userService;
    private final String CLAIMS_SUBJECT = "sub";
    private final String CLAIMS_CREATED = "created";

    @Value("${auth.expiration}")
    private Long TOKEN_VALIDITY = 604800L;

    @Value("${auth.secret}")
    private String TOKEN_SECRET;

    public String generateUserToken(AppUser userDetails){
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIMS_SUBJECT, userDetails.getUsername());
        claims.put("userId",userDetails.getId());
        claims.put("role",userDetails.getRole());
        claims.put(CLAIMS_CREATED, new Date());

        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(generateExpirationDate())
                .signWith(SignatureAlgorithm.HS512, TOKEN_SECRET)
                .compact();
    }

    public String getUserNameFromToken(String token) {
        try {
            Claims claims = getClaims(token);

            return claims.getSubject();
        }catch (Exception ex) {
            return null;
        }
    }
    public Date generateExpirationDate() {
        return new Date(System.currentTimeMillis() + TOKEN_VALIDITY * 1000);
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = getUserNameFromToken(token);

        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        Date expiration = getClaims(token).getExpiration();
        return expiration.before(new Date());
    }

    public Claims getClaims(String token) {
        Claims claims;
        try {
            claims = Jwts.parser().setSigningKey(TOKEN_SECRET)
                    .parseClaimsJws(token)
                    .getBody();
        }catch (Exception ex) {
            claims = null;
        }

        return claims;
    }

    public AppUser getUserDetailsFromToken(String token){
        try{
            String username = getUserNameFromToken(token);
            return (AppUser) userService.loadUserByUsername(username);
        }catch (Exception e){
            return null;
        }
    }

    public Map<Object,Object> isTokenValid(String token) {
        Map<Object,Object> res = new HashMap<>();
        try {
            String username = getUserNameFromToken(token);
            AppUser user = getUserDetailsFromToken(token);
            res.put("isValid",(username.equals(user.getUsername())
                    && !isTokenExpired(token)));
            res.put("userId",user.getId());
            res.put("role",user.getRole());
            res.put("userIsAdmin",user.getRole().equals("10"));
            res.put("token",token);
            return res;
        }catch (Exception e){
            res.put("isValid",false);
            res.put("userId","0");
            res.put("role","0");
            res.put("userIsAdmin",false);
            res.put("token",token);
            return res;
        }
    }
}
