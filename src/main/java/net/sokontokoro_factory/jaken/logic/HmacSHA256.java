package net.sokontokoro_factory.jaken.logic;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

/**
 * Created by TATSUYA-PC4 on 2016/06/04.
 */
public class HmacSHA256 implements SignatureEngines {
    private static String ALGORITHM = "HmacSHA256";

    Key secretKeySpec;

    @Override
    public void setKey(Key secretKeySpec){
        this.secretKeySpec = secretKeySpec;
    }

    @Override
    public Key getKey(){
        return secretKeySpec;
    }

    @Override
    public Key createKey(){
        String key = UUID.randomUUID().toString();
        secretKeySpec = new SecretKeySpec(key.getBytes(), ALGORITHM);

        return secretKeySpec;
    }

    @Override
    public byte[] encrypt(String source) {
        if(secretKeySpec == null){
            secretKeySpec = createKey();
        }

        Mac mac = null;
        try {
            mac = Mac.getInstance(ALGORITHM);
            mac.init(secretKeySpec);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }catch (InvalidKeyException e) {
            e.printStackTrace();
        }
        mac.doFinal(source.getBytes());

        byte[] encryptedSource = mac.doFinal();

        return encryptedSource;
    }

}
