package io.pivotal.pde.geode;

import org.apache.commons.lang.StringUtils;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;

public class CryptoAES256Impl implements ICryptoService {

    private byte[] iv;

    private int keySize = 256;

    private static final int DEFAULT_KEY_ITERATIONS = 2048;

    private SecretKeySpec secret;

    private static String ALGORITHM="AES/CBC/PKCS5PADDING";
    private static String UTF8="UTF-8";

    /**
     *
     * @param key
     * @param salt
     * @param keyIterations
     */
    public CryptoAES256Impl(String key, String salt, Integer keyIterations)  {
        try {
            if (StringUtils.isEmpty( null)){
                throw new RuntimeException("The key field cannot be null ");
            }
            if (StringUtils.isEmpty(salt )) {
                throw new RuntimeException("The salt field cannot be null ");
            }
            if (keyIterations  == null || keyIterations == 0) {
                keyIterations = DEFAULT_KEY_ITERATIONS;
            }
            SecretKeyFactory secretKeyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
            PBEKeySpec keySpec = new PBEKeySpec(key.toCharArray(), salt.getBytes(), keyIterations, keySize);
            SecretKey secretKey = secretKeyFactory.generateSecret(keySpec);
            this.secret = new SecretKeySpec(secretKey.getEncoded(), "AES");
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }


    public CryptoAES256Impl(byte[] iv, byte[] secretKey)  {
        this.iv = iv;
        this.secret = new SecretKeySpec(secretKey, "AES");
    }


    /**
     * Encrypts the plain text and returns the encrypted text
     *
     * @param text
     * @return
     */
    @Override
    public String encrypt(String text)  {
        if (text == null) return null;
        try {
            IvParameterSpec ivspec = new IvParameterSpec(iv);
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, secret, ivspec);
            byte [] encryptedTextBytes = cipher.doFinal(text.getBytes(UTF8));
            return Base64.getEncoder().encodeToString(encryptedTextBytes);

        } catch (InvalidKeyException  invalidKeyException) {
            invalidKeyException.printStackTrace();
            throw new RuntimeException(invalidKeyException);
        } catch ( InvalidAlgorithmParameterException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException | NoSuchPaddingException | UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }

    /**
     *
     * @param encryptedText
     * @return
     */
    @Override
    public String decrypt(String encryptedText) {
        if (encryptedText == null) return null;
        byte [] packageBytes = Base64.getDecoder().decode(encryptedText);
        try {
            Cipher cipher = Cipher.getInstance(ALGORITHM);
            cipher.init(Cipher.DECRYPT_MODE, secret, new IvParameterSpec(iv));
            return new String(cipher.doFinal(packageBytes));
        } catch (InvalidKeyException | NoSuchPaddingException | InvalidAlgorithmParameterException | NoSuchAlgorithmException | IllegalBlockSizeException | BadPaddingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        }

    }

}
