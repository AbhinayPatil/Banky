package com.abhinay.bankapp.bankapp.util;
import org.springframework.stereotype.Component;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;

@Component
public class SecurePasswordHasher {

    private static final int SALT_LENGTH = 16;       // 16 bytes
    private static final int ITERATIONS = 65536;     // Recommended: 65k+
    private static final int KEY_LENGTH = 256;       // 256-bit key

    // Generate a random salt
    public String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        return Base64.getEncoder().encodeToString(salt);
    }

    // Hash password using PBKDF2
    public String hashPassword(String password, String salt) {
        try {
            byte[] saltBytes = Base64.getDecoder().decode(salt);
            PBEKeySpec spec = new PBEKeySpec(password.toCharArray(), saltBytes, ITERATIONS, KEY_LENGTH);
            SecretKeyFactory skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            byte[] hash = skf.generateSecret(spec).getEncoded();
            return Base64.getEncoder().encodeToString(hash);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            throw new RuntimeException("Error while hashing password", e);
        }
    }

    // Store salt and hash together as "salt:hash"
    public String getPassword(String password) {
        String salt = generateSalt();
        String hash = hashPassword(password, salt);
        return salt + ":" + hash; // Database-friendly format
    }

    // Verify password against stored salt:hash
    public boolean verifyPassword(String password, String stored) {
        String[] parts = stored.split(":");
        if (parts.length != 2) return false;
        String salt = parts[0];
        String expectedHash = parts[1];
        String hash = hashPassword(password, salt);
        return hash.equals(expectedHash);
    }

    // Test
//    public static void main(String[] args) {
//        String password = "MySecurePassword123";
//
//        // Store password
//        String storedValue = getPassword(password);
//        System.out.println("Stored (salt:hash): " + storedValue);
//
//        // Verify correct password
//        boolean isMatch = verifyPassword(password, storedValue);
//        System.out.println("Password match: " + isMatch);
//
//        // Verify wrong password
//        boolean isWrong = verifyPassword("WrongPassword", storedValue);
//        System.out.println("Wrong password match: " + isWrong);
//    }
}