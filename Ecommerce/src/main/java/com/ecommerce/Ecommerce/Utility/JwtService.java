package com.ecommerce.Ecommerce.Utility;


import com.ecommerce.Ecommerce.Models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET_KEY = "QWERTYKIFPODPDK67896VCMCMFKKTIJKSDMMM32456FDDFGYTHVD";

    public String generateToken(User user)
    {
        return Jwts.builder()
                .setSubject(user.getEmail())
                .claim("role", user.getRole().name())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 86400000))
                .signWith(SignatureAlgorithm.ES256, SECRET_KEY)
                .compact();
    }


    //Jwts.builder() -- this is static method initializes the jwt builder
    //.setSubject() -- this method set the subject claim of the jwt, such their email and username.
    //setIssuedAt(user.getEmail()) -- issue date
    //claim("role", user.getRole().name()) -- it store the user's role as a string, this can be useful for role_based access control


}
