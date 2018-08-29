package io.pivotal.pde.geode.encryption.common;

import io.pivotal.pde.geode.CipherType;
import io.pivotal.pde.geode.CryptoUtil;
import io.pivotal.pde.geode.ICryptoService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class AppCryptoServiceImpl implements IAppCryptoService {

    private ICryptoService cryptorService;



    public AppCryptoServiceImpl() {
        super();
        cryptorService= CryptoUtil.getCipher(CipherType.AES, "0123456789012345".getBytes(), "01234567890123456789012345678901".getBytes());
    }

    public String encrypt(String text) {
        if (!StringUtils.isEmpty(text)) {
            String encryptedVal=cryptorService.encrypt(text);
            return "v1¦"+encryptedVal;
        }
        return text;
    }

}
