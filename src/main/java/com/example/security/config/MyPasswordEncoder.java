package com.example.security.config;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author huang.beijin
 * @date 2020/3/3 22:16
 * @description
 */
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence);
    }
}
