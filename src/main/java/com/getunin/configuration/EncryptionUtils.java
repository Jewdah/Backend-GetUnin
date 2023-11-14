package com.getunin.configuration;


import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

public class EncryptionUtils {
    private static final String KEY = "GET-UNIN-2023X.x";

    public static String encrypt(String plaintext) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        byte[] ciphertextBytes = cipher.doFinal(plaintext.getBytes());
        return Base64.getEncoder().encodeToString(ciphertextBytes);
    }

    public static String decrypt(String ciphertext) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(KEY.getBytes(), "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        byte[] ciphertextBytes = Base64.getDecoder().decode(ciphertext);
        byte[] plaintextBytes = cipher.doFinal(ciphertextBytes);
        String qrData = new String(plaintextBytes);
        qrData = qrData.replaceAll("ñ", "n");
        return qrData;
    }
}