package koreatechbus.auth;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.time.LocalDateTime;

@Component
public class JWTProvider {
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.expired_time}")
    private Integer expiredTime;

    public String createToken(Integer schoolId){
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .expiration(Date.valueOf(LocalDateTime.now().plusMinutes(expiredTime).toLocalDate()))
                .claim("schoolId", schoolId)
                .signWith(key)
                .compact();
    }

    public String getSchoolId(String token){
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        /*String schoolId = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseEncryptedClaims(token)
                .getPayload()
                .get("schoolId").toString();*/

        String schoolId2 = Jwts.parser()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody()
                .get("schoolId").toString();

        return schoolId2;
    }

    /*public String createToken(TokenDTO tokenDTO){
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        return Jwts.builder()
                .expiration(Date.valueOf(LocalDateTime.now().plusMinutes(expiredTime).toLocalDate()))
                .claim("schoolId", tokenDTO.getSchoolId())
                .claim("name", tokenDTO.getName())
                .claim("role", tokenDTO.getRole())
                .claim("bookmarks", tokenDTO.getBookmarks())
                .signWith(key)
                .compact();
    }*/

    /*private Long getRole(String token){
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        String role = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseEncryptedClaims(token)
                .getPayload()
                .get("role").toString();

        return Long.parseLong(role);
    }

    private String getName(String token){
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        String name = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseEncryptedClaims(token)
                .getPayload()
                .get("name").toString();

        return name;
    }

    private String getBookmarks(String token){
        SecretKey key = Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8));

        String bookmarks = Jwts.parser()
                .verifyWith(key)
                .build()
                .parseEncryptedClaims(token)
                .getPayload()
                .get("bookmarks").toString();

        return bookmarks;
    }

    public void getInformation(String token){
        System.out.println(getSchoolId(token));
        System.out.println(getBookmarks(token));
        System.out.println(getRole(token));
        System.out.println(getName(token));
    }*/
}
