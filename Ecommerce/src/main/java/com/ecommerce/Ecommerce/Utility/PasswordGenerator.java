package com.ecommerce.Ecommerce.Utility;

import org.springframework.context.annotation.Lazy;
import org.springframework.data.repository.core.support.FragmentNotImplementedException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.Map;

@Component
@Lazy
public class PasswordGenerator {

    private static final String UPPER = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWER = "abcdefghijklmnopqrstuvwxyz";
    private static final String DIGITS = "1234567890";
    private static final String SPECIAL = "!@#$%^&*()-_={}|:,.<>?";
    private static final String All = UPPER + LOWER + DIGITS + SPECIAL;

    private static final SecureRandom random = new SecureRandom();

    public ResponseEntity<String> generateRandomPassword(int length) {

        if(length < 12)
            throw new IllegalArgumentException("password should length of 12");

        StringBuilder password = new StringBuilder();

        password.append(UPPER.charAt(random.nextInt(UPPER.length())));
        password.append(LOWER.charAt(random.nextInt(LOWER.length())));
        password.append(DIGITS.charAt(random.nextInt(DIGITS.length())));
        password.append(SPECIAL.charAt(random.nextInt(SPECIAL.length())));

        for(int i = 4; i < length; i++)
        {
            password.append(All.charAt(random.nextInt(All.length())));
        }

        String resultantPassword = shuffleString(password.toString());

        return ResponseEntity.status(HttpStatus.CREATED).body(resultantPassword);
    }

    private static String shuffleString(String input)
    {
        char[] passwordArray = input.toCharArray();

        for(int i = 0; i < passwordArray.length; i++)
        {
            int idx = random.nextInt(passwordArray.length);

            char temp = passwordArray[i];
            passwordArray[i] = passwordArray[idx];
            passwordArray[idx] = temp;
        }

        return new String(passwordArray);
    }
}
