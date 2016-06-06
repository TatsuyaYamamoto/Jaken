package net.sokontokoro_factory.jaken.logic;

import java.security.Key;

/**
 * Created by TATSUYA-PC4 on 2016/04/09.
 */
public interface SignatureEngines {
    Key getKey();
    void setKey(Key key);
    Key createKey();
    byte[] encrypt(String source);
}
