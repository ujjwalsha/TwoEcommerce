package com.ecommerce.Ecommerce.Utility;


import com.ecommerce.Ecommerce.Models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    @Value("${jwt.SECRET}")
    private String SECRET_KEY;

    @Value("${jwt.expiration}")
    private long jwtExpiration;

    public String generateToken(User user)
    {
        return Jwts.builder()
                .setSubject(user.getUsername())
                .claim("role", user.getRole().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .compact();
    }


    //Jwts.builder() -- this is static method initializes the jwt builder
    //.setSubject() -- this method set the subject claim of the jwt, such their email and username.
    //setIssuedAt(user.getEmail()) -- issue date
    //claim("role", user.getRole().name()) -- it store the user's role as a string, this can be useful for role_based access control
    //setExpiration() -- it uses the current time 86400000 millisecond(which equals to 24 hours)
    //signWith(SignatureAlgorithm.ES256, SECRET_KEY) -- signing algorithm, key size 256 bits)
    //compact() -- this method generate the final JWT string, after all builder, claim, signature, headers.
}
