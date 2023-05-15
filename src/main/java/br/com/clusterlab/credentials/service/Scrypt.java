package br.com.clusterlab.credentials.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.scrypt.SCryptPasswordEncoder;

public class Scrypt {
    public static String encrypty(String password){
        PasswordEncoder sCryptPasswordEncoder = new SCryptPasswordEncoder(16384, 8, 1, 32, 64);
        return sCryptPasswordEncoder.encode(password);
    }
}
