package net.sokontokoro_factory.jaken;

/**
 *
 *
 * @see "http://tools.ietf.org/html/rfc7518#section-3"
 * Created by TATSUYA-PC4 on 2016/06/04.
 */
public enum JakenAlgorithm {
    HMAC_using_SHA256("HS256"),
    RSASSA_PKCS1_v1_5_using_SHA256("RS256");

    private String value;

    JakenAlgorithm(String value) {
        this.value = value;
    }

    public String getValue() { return value; }
}
