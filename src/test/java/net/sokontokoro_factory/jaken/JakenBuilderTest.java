package net.sokontokoro_factory.jaken;

import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.statements.ExpectException;
import org.junit.rules.ExpectedException;

import java.security.Key;

import static org.junit.Assert.*;

/**
 * Created by TATSUYA-PC4 on 2016/06/05.
 */
public class JakenBuilderTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void JWTと署名用Keyを含んだJakenオブジェクトを取得できる(){
        String jwtId = "";
        String issuer = "";
        String subject = "";
        Long expired = System.currentTimeMillis();

        Jaken jaken = Jaken.getBuilder()
                .setAlgorithm(JakenAlgorithm.RSASSA_PKCS1_v1_5_using_SHA256)
                .setJwtId(jwtId)
                .setIssuer(issuer)
                .setSubject(subject)
                .setExpired(expired)
                .build();

        assertNotNull(jaken.getJwt());
        assertNotNull(jaken.getKey());
    }

    @Test
    public void アルゴリズムをしてしない場合例外が発生する() throws Exception{
        expectedException.expect(JakenException.class);
        Jaken jaken = Jaken.getBuilder().build();
    }


    @Test
    public void 既存のkeyを使用してJWTを取得できる(){
        Jaken tmpjaken = Jaken.getBuilder()
                .setAlgorithm(JakenAlgorithm.HMAC_using_SHA256)
                .build();

        Key key = tmpjaken.getKey();

        Jaken jaken = Jaken.getBuilder()
                .setKey(key)
                .setAlgorithm(JakenAlgorithm.HMAC_using_SHA256)
                .build();

        System.out.println(jaken.getJwt());
        assertNotNull(jaken.getJwt());
        assertTrue(tmpjaken.getKey().equals(jaken.getKey()));
    }
}