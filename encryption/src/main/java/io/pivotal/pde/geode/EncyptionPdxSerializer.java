package io.pivotal.pde.geode;

import io.pivotal.pde.geode.encryption.annotation.EnableEncryption;
import org.apache.commons.lang.StringUtils;
import org.apache.geode.pdx.ReflectionBasedAutoSerializer;

import java.lang.reflect.Field;

public class EncyptionPdxSerializer extends ReflectionBasedAutoSerializer {


    private ICryptoService cryptorService;


    public EncyptionPdxSerializer() {
        super();
        cryptorService=CryptoUtil.getCipher(CipherType.AES, "0123456789012345".getBytes(), "01234567890123456789012345678901".getBytes());
    }

    @Override
    public boolean transformFieldValue(Field field, Class<?> type) {
        // Check if encryption is enabled on the field
        if (field.isAnnotationPresent( EnableEncryption.class)) {
            return true;
        } else
            return super.transformFieldValue(field, type);
    }

    @Override
    public Object writeTransform(Field field, Class<?> clazz, Object originalValue) {

        Object retObj=null;
        // Check if encryption is enabled on the field
        if (field.isAnnotationPresent(EnableEncryption.class)) {
            // Encrypt and return the value
            if (originalValue !=null && !StringUtils.isEmpty(originalValue.toString())) {
              // return object - tag + encrypted value
                String encryptedVal=cryptorService.encrypt(originalValue.toString());
                retObj= "v1¦"+encryptedVal;
            }
            return retObj;
        } else
            return originalValue;
    }

    @Override
    public Object readTransform(Field field, Class<?> clazz, Object serializedValue) {
        Object retObj="";
        // Check if encryption is enabled on the field
        if (field.isAnnotationPresent(EnableEncryption.class)) {
            if (serializedValue instanceof String) {
                String retSerializedValue= (String) serializedValue;
                String encryptedVal=retSerializedValue.replaceFirst("v1¦", "");
                retObj=cryptorService.decrypt(encryptedVal);
            }
            return retObj;
        } else
            return serializedValue;
    }

}

