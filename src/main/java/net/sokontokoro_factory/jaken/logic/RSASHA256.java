package net.sokontokoro_factory.jaken.logic;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.*;

/**
 * Created by TATSUYA-PC4 on 2016/06/05.
 */
public class RSASHA256 implements SignatureEngines {
    private static final String KEYPAIR_GENERATOR_ALGORITHM = "RSA";
    private  static final String MESSAGE_DIGEST_ALGORITHM = "SHA-256";
    private Key publicKey;

    @Override
    public Key getKey(){
        return publicKey;
    }

    @Override
    public void setKey(Key publicKey){
        this.publicKey = publicKey;
    }

    @Override
    public Key createKey(){
        /* 秘密鍵公開鍵の作成 */
        KeyPairGenerator keygen = null;
        try {
            keygen = KeyPairGenerator.getInstance(KEYPAIR_GENERATOR_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // ignore
            // アルゴリズムはJava仕様通りの文字列を指定しているため、exceptionの発生は想定していない
        }
        keygen.initialize(2048); // RSA2048

        return keygen.generateKeyPair().getPublic();
    }

    @Override
    public byte[] encrypt(String source){
        if(publicKey == null){
            publicKey = createKey();
        }
		/* ハッシュ化 */
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance(MESSAGE_DIGEST_ALGORITHM);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            // ignore
            // アルゴリズムはJava仕様通りの文字列を指定しているため、exceptionの発生は想定していない
        }
        md.update(source.getBytes());

        byte[] hashed = md.digest();

        byte[] encryptedData = null;
        try {
			/* 暗号化 */
            Cipher cipher = Cipher.getInstance(KEYPAIR_GENERATOR_ALGORITHM);
            cipher.init(Cipher.ENCRYPT_MODE, publicKey);
            encryptedData = cipher.doFinal(hashed);
        }catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }catch(NoSuchPaddingException e){

        }catch(InvalidKeyException e){

        }catch(BadPaddingException e) {

        }catch(IllegalBlockSizeException e){

        }
        return encryptedData;
    }
}
