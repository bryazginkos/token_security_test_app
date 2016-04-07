package ru.kos.shop.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.naming.AuthenticationException;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

/**
 * Created by Константин on 05.04.2016.
 */
@Component
public class TokenService {

    @Value("${authentication.token.secret}")
    private String apiSecret;

    @Value("${authentication.token.issuer}")
    private String issuer;

    @Value("${authentication.token.subject}")
    private String subject;

    @Value("${authentication.token.lifetime.ms}")
    private long tokenLifeTimeMs;


    /**
     * Создать токен для пользователя
     * @param userId ID пользователя
     * @return токен
     */
    @NotNull
    public String createJWT(@NotNull Integer userId) {

        //The JWT signature algorithm we will be using to sign the token
        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        //We will sign our JWT with our ApiKey secret
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(apiSecret);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        //Let's set the JWT Claims
        JwtBuilder builder = Jwts.builder()
                .setId(Integer.toString(userId))
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(issuer)
                .signWith(signatureAlgorithm, signingKey);

        //if it has been specified, let's add the expiration
        if (tokenLifeTimeMs >= 0) {
            long expMillis = nowMillis + tokenLifeTimeMs;
            Date exp = new Date(expMillis);
            builder.setExpiration(exp);
        }

        //Builds the JWT and serializes it to a compact, URL-safe string
        return builder.compact();
    }

    /**
     * Расшифровать токен
     * @param jwt токен
     * @return Расшифровка токена
     * @throws Exception если токен не валиден (исключая случай, когда токен устарел)
     */
    @NotNull
    public TokenInfo getTokenInfo(@NotNull String jwt) throws Exception {
        //This line will throw an exception if it is not a signed JWS (as expected)
        Claims claims = Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(apiSecret))
                .parseClaimsJws(jwt).getBody();

        if (!subject.equals(claims.getSubject())) {
            throw new AuthenticationException("Unknown subject");
        }

        if (!issuer.equals(claims.getIssuer())) {
            throw new AuthenticationException("Unknown issuer");
        }

        String userIdStr = claims.getId();
        try {
            Integer userId = Integer.parseInt(userIdStr);
            return new TokenInfo(userId, claims.getExpiration());
        } catch (NumberFormatException e) {
            throw new AuthenticationException("Unknown user");
        }
    }
}
