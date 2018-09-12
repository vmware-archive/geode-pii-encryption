package io.pivotal.pde.geode;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
public class CryptoTest {


    @Test
    public void testEncryptName() {

        ICryptoService cryptoService = CryptoUtil.getCipher(CipherType.AES, "0123456789012345".getBytes(), "01234567890123456789012345678901".getBytes());
        String value = "Gideon and Niranjan";

        String encryptedValue = cryptoService.encrypt(value);
        String decryptedValue = cryptoService.decrypt(encryptedValue);
        assertThat(value, equalTo(decryptedValue));
    }

    @Test
    public void testEncryptAddress() {
        ICryptoService cryptoService = CryptoUtil.getCipher(CipherType.AES, "0123456789012345".getBytes(), "01234567890123456789012345678901".getBytes());

        String value = "222, Main Street, Apt 202";

        String encryptedValue = cryptoService.encrypt(value);
        String decryptedValue = cryptoService.decrypt(encryptedValue);
        assertThat(value, equalTo(decryptedValue));
    }

    @Test
    public void testEncryptDecrypt3() {

        ICryptoService cryptoService = CryptoUtil.getCipher(CipherType.AES, "0123456789012345".getBytes(), "01234567890123456789012345678901".getBytes());

        String value = "gideon.low@garmin.com";

        String encryptedValue = cryptoService.encrypt(value);
        String decryptedValue = cryptoService.decrypt(encryptedValue);
        assertThat(value, equalTo(decryptedValue));
    }

    @Test
    public void testEncryptDecrypt4() {

        ICryptoService cryptoService = CryptoUtil.getCipher(CipherType.AES, "0123456789012345".getBytes(), "01234567890123456789012345678901".getBytes());

        String value = "Nsarvi1.Sarvi23$.3342@garmin.com";

        String encryptedValue = cryptoService.encrypt(value);
        String decryptedValue = cryptoService.decrypt(encryptedValue);
        assertThat(value, equalTo(decryptedValue));
    }

    @Test
    public void testEncryptDecrypt5() {
        ICryptoService cryptoService = CryptoUtil.getCipher(CipherType.AES, "0123456789012345".getBytes(), "01234567890123456789012345678901".getBytes());

        String value = "Gideon12.low2!*3ywdd@abc.uk";

        String encryptedValue = cryptoService.encrypt(value);
        String decryptedValue = cryptoService.decrypt(encryptedValue);
        assertThat(value, equalTo(decryptedValue));
    }
}
