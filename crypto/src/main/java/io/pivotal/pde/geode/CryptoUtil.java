package io.pivotal.pde.geode;

public class CryptoUtil {

    /**
     *
     * @param cipherType
     * @param iv
     * @param secretKey
     * @return
     */
    public static ICryptoService getCipher(CipherType cipherType, byte[] iv, byte[]secretKey) {
        switch (cipherType) {
            case  AES:
                return new CryptoAESImpl(iv, secretKey);
            default:
                throw new RuntimeException("Algorithm is not implemented for Cipher Type:" + cipherType);
        }
    }
}
