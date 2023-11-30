package be.intecbrussel.projectmanagerbackend.security;

import be.intecbrussel.projectmanagerbackend.models.User;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class JwtUtil {
    // We choose the secret key our self's. We are obligated that it is a long key. Normally this key is not here
    // but in an env file. better is we the app reboots there will generate a new secret key. So when you do that
    // everybody will be logout because the token are not valid anymore.
    private final String secret_key = "mysecretkeymysecretkeymysecretkeymysecretkeymysecretkeymysecretkey";

    // 60 min
    private final long accessTokenValidity = 60*60*1000;

    // In the context of authentication and authorization, a
    // JwtParser is often used to parse and verify JSON Web Tokens
    private final JwtParser jwtParser;

    // This should be the same as the header in our request in the front for example the product service.
    private final String TOKEN_HEADER = "Authorization";
    private final String TOKEN_PREFIX = "Bearer ";

    // parser is made here with our key
    public JwtUtil(){
        this.jwtParser = Jwts.parserBuilder().setSigningKey(Keys
                .hmacShaKeyFor(secret_key.getBytes(StandardCharsets.UTF_8))).build();
    }

    public String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getEmail());
        // put extra data in the payload
        Date tokenCreateTime = new Date();
        Date tokenValidity = new Date(tokenCreateTime.getTime() + TimeUnit.MINUTES.toMillis(accessTokenValidity));
        return Jwts.builder()
                .setClaims(claims)
                .setExpiration(tokenValidity)
                .signWith(Keys.hmacShaKeyFor(secret_key.getBytes(StandardCharsets.UTF_8)), SignatureAlgorithm.HS512)
                .compact();
    }

    // This method facilitates extracting and working with the claims contained within a JWT.
    private Claims parseJwtClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

    // This method is typically used in authentication processes to handle JWT validation and extraction of claims.
    public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token != null) {
                return parseJwtClaims(token);
            }
            return null;
        } catch (ExpiredJwtException ex) {
            req.setAttribute("expired", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            req.setAttribute("invalid", ex.getMessage());
            throw ex;
        }
    }

    // This method is commonly used to retrieve JWT tokens from incoming HTTP requests during authentication processes.
    public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

    // This method is typically used to validate the expiration of JWT tokens during the authentication process.
    public boolean validateClaims(Claims claims) throws AuthenticationException {
        try {
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            throw e;
        }
    }

}
