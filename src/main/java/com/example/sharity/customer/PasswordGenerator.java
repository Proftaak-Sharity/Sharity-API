package com.example.sharity.customer;

import org.springframework.stereotype.Component;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

@Component
public  class PasswordGenerator {

    public  String getSHA512Password(String passwordToEncrypt, byte[] salt) {

        String generatedPassword = null;

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-512");
            md.update(salt);

            byte[] bytes = md.digest(passwordToEncrypt.getBytes());
            StringBuilder sb = new StringBuilder();

            for (byte aByte : bytes) {
                sb.append(Integer.toString((aByte & 0xff) + 0x100, 16).substring(1));
            }
            generatedPassword = sb.toString();
        }
        catch(NoSuchAlgorithmException e) {
            System.err.println("Password incorrect");
        }
        return generatedPassword;
    }

    public byte[] getSalt() throws NoSuchAlgorithmException {
        SecureRandom sr =  SecureRandom.getInstanceStrong();
        byte[] salt = new byte[16];
        sr.nextBytes(salt);

        return salt;
    }

}
