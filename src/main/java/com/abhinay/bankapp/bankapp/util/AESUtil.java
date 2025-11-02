package com.abhinay.bankapp.bankapp.util;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class AESUtil {

    private static final String ALGORITHM = "AES";
    private static final String SECRET_KEY = "1234567890123456"; // 16-char key for AES-128

    public static String decrypt(String encryptedText) throws Exception {
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, key);
        byte[] decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(encryptedText));
        return new String(decryptedBytes);
    }

    //encryption method for testing purposes
    public static String encrypt(String plainText) throws Exception {
        System.out.println("plainText: " + plainText);
        SecretKeySpec key = new SecretKeySpec(SECRET_KEY.getBytes(), ALGORITHM);
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedBytes = cipher.doFinal(plainText.getBytes());
        return Base64.getEncoder().encodeToString(encryptedBytes);
    }

    public static void main(String[] args) {
        try {
//            String originalText = "{\"username\":\"abhinay\",\"password\":\"secret123\"}";
            String originalText = "Login Successful!";
//           String originalText = "zVf9oqvQ7ryY/ITX4V89RoUgWSRiPNPO4ViQ51+VeEdZkngECmIRW+PpvUdbvtb3";
            String encryptedText = encrypt(originalText);
            System.out.println("Original Text: " + originalText);
            System.out.println("Encrypted Text: " + encryptedText);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
