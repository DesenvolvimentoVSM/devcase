package com.vsm.devcase.config;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.security.crypto.password.PasswordEncoder;

public class MeuPasswordEncoder implements PasswordEncoder {
	
	private static final String SALT = "{matheus}";

    @Override
    public String encode(CharSequence charSequence) {
        return DigestUtils.sha256Hex(SALT + charSequence);
    }

    @Override
    public boolean matches(CharSequence charSequence, String password) {
        String encryptedPassword = DigestUtils.sha256Hex(SALT + charSequence);
        return encryptedPassword.equals(password);
    }

    public static void main(String[] args) {
        System.out.println(DigestUtils.sha256Hex(SALT + "angular"));
    }

}
