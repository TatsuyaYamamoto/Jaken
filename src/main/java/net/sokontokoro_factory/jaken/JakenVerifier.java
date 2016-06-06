package net.sokontokoro_factory.jaken;

import net.sokontokoro_factory.jaken.element.ClaimSet;
import net.sokontokoro_factory.jaken.element.Header;
import net.sokontokoro_factory.jaken.logic.SignatureEngines;
import net.sokontokoro_factory.jaken.util.JSONParseUtil;

import java.security.Key;
import java.util.Base64;

import static net.sokontokoro_factory.jaken.JakenAlgorithm.HMAC_using_SHA256;
import static net.sokontokoro_factory.jaken.JakenAlgorithm.RSASSA_PKCS1_v1_5_using_SHA256;

/**
 * Created by TATSUYA-PC4 on 2016/06/05.
 */
public class JakenVerifier {

    private Jaken jaken;

    JakenVerifier(){
        this.jaken = new Jaken();
    }
    public JakenVerifier setJwt(String jwt){
        this.jaken.setJwt(jwt);
        return this;
    }

    public JakenVerifier setKey(Key key){
        this.jaken.setKey(key);
        return this;
    }
    public void confirms() throws JakenException{

        if(this.jaken.getJwt() == null || this.jaken.getKey() == null){
            throw new JakenException("Verifier is illegal. No Jaken or no key.");
        }

        Long now = System.currentTimeMillis();
        String[] jwtElement = this.jaken.getJwt().split(".");

        String headerString = Base64.getUrlDecoder().decode(jwtElement[0]).toString();
        String claimSetString = Base64.getUrlDecoder().decode(jwtElement[1]).toString();
        String signatureString = Base64.getUrlDecoder().decode(jwtElement[2]).toString();

        Header header = JSONParseUtil.toObject(Header.class, headerString);
        ClaimSet claimSet = JSONParseUtil.toObject(ClaimSet.class, claimSetString);

        // Header typeがJWTでない場合、
        if(header.getType().isEmpty() || !header.getType().equals(Jaken.JWT_HEADER_TYPE)){
            throw new JakenException();
        }

        // Header algorithmを読み込み、検証に使用する署名エンジンを確認する
        String  algorithm = header.getAlgorithm();
        SignatureEngines signatureEngines = null;
        if(algorithm.equals(HMAC_using_SHA256.getValue())){

        }else if(algorithm.equals(RSASSA_PKCS1_v1_5_using_SHA256)){

        }else{
            throw new JakenException("no algrithm or unsupported.");
        }

        // 改ざん検証する
        signatureEngines.setKey(this.jaken.getKey());
        String encodedSignature = Base64.getUrlEncoder().encodeToString(signatureEngines.encrypt(headerString + "." + claimSetString));
        if(encodedSignature.equals(signatureString)){
            throw new JakenException("signature is invalid");
        }


        // 有効期限を確認する
        Long expired = claimSet.getExpirationTime();
        if(now > expired){
            throw new JakenException("token is expired.");
        }
    }
}
