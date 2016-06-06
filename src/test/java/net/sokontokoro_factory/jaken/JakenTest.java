package net.sokontokoro_factory.jaken;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.security.Key;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.cert.Extension;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by TATSUYA-PC4 on 2016/06/05.
 */
public class JakenTest {
    @Rule
    public ExpectedException expectedException = ExpectedException.none();

    @Test
    public void JWTの検証が出来る() throws JakenException {
        expectedException.expect(JakenException.class);

        Key key = null;
        String jwt = "aaaa.bbb.ccc";

        Jaken.getVerifier().setJwt(jwt).setKey(key).confirms();
    }
}