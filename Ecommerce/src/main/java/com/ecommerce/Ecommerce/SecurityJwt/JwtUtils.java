package com.ecommerce.Ecommerce.SecurityJwt;


import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Component
public class JwtUtils {

    private static final Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${spring.app.jwtExpirationMs}")
    private int jwtExpirationTimeMs;

    @Value("${spring.app.jwtsecret}")
    private String jwtSecret;

    //getting the jwt from headers
    public String getJwtFromHeaders(HttpServletRequest request)
    {
        String bearerToken = request.getHeader("Authorization");
        logger.debug("Authorization: {}", bearerToken);

        if(bearerToken != null && bearerToken.startsWith("Bearer "))
        {
            return bearerToken.substring(7);
        }

        return null;
    }


    //generate token from username

    public String GenerateTokenFromUsername(UserDetails userDetails)
    {
        String username = userDetails.getUsername();
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date((new Date().getTime() + jwtExpirationTimeMs)))
                .signWith(key())
                .compact();

    }

    //getting username for jwt token
    public String getUsernameFromJwtToken(String token)
    {
        return Jwts.parser()
                .verifyWith((SecretKey) key())
                .build().parseSignedClaims(token)
                .getPayload().getSubject();
    }


    //generate signing key
    public Key key(){
        return Keys.hmacShaKeyFor(
                Decoders.BASE64.decode(jwtSecret)
        );
    }

    //validate jwt token
    public Boolean validateJwtToken(String authToken)
    {
        try
        {
            System.out.println("validating....");
            Jwts.parser()
                    .verifyWith((SecretKey) key())
                    .build().parseSignedClaims(authToken);

            return true;
        }
        catch (MalformedJwtException exception)
        {
            logger.error("Invalid JWT Token: {}", exception.getMessage());
        }
        catch (ExpiredJwtException e)
        {
            logger.error("JWT token is expired: {}", e.getMessage());
        }
        catch (UnsupportedJwtException e)
        {
            logger.error("JWT token is unsupported: {}", e.getMessage());
        }
        catch (IllegalArgumentException e)
        {
            logger.error("JWT Claims String is empty : {}", e.getMessage());
        }

        return false;
    }

}
