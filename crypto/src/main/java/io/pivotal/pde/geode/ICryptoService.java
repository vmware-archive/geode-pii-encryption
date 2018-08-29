package io.pivotal.pde.geode;

public interface ICryptoService {

    public String encrypt(String text);
    public String decrypt(String encryptedText);

}
