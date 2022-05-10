package uz.alex.climateappapi.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Service
@RequiredArgsConstructor
public class JwtUtils {

    private final String SECRET_KEY = "secret";

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    public Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
    }

    private Boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return createToken(claims, userDetails.getUsername());
    }

    private String createToken(Map<String, Object> claims, String subject) {
        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                //@Todo: write in db
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24 * 15))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
    }

    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername()));
    }

//    public Boolean validateToken(String token, UserDetails userDetails) {
////        try {
////            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
////            return true;
////        } catch (SignatureException ex) {
////            logger.error("Invalid JWT signature - {}", ex.getMessage());
////        } catch (MalformedJwtException ex) {
////            logger.error("Invalid JWT token - {}", ex.getMessage());
////        } catch (ExpiredJwtException ex) {
////            logger.error("Expired JWT token - {}", ex.getMessage());
////        } catch (UnsupportedJwtException ex) {
////            logger.error("Unsupported JWT token - {}", ex.getMessage());
////        } catch (IllegalArgumentException ex) {
////            logger.error("JWT claims string is empty - {}", ex.getMessage());
////        }
////        return false;
//        final String username = extractUsername(token);
//        return (username.equals(userDetails.getUsername()));
//    }

}
