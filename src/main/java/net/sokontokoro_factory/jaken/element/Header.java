package net.sokontokoro_factory.jaken.element;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by TATSUYA-PC4 on 2016/06/02.
 */
public class Header {

    // JWTであることを明示する
    /**
     * トークンの仕様を指定する。このライブラリではJWT以外を想定していない
     * It is used by JWT applications to declare the media type [IANA.MediaTypes] of this complete JWT
     *
     * @see "https://self-issued.info/docs/draft-ietf-oauth-json-web-token.html#typHdrDef"
     */
    @JsonProperty("typ")
    @Getter
    @Setter
    private String type;

    /**
     * 署名のアルゴリズムを指定する
     * 指定するアルゴリズムはJakenAlgorithm.classを参照
     *
     * @see net.sokontokoro_factory.jaken.JakenAlgorithm
     */
    // 暗号化のアルゴリズム RSA using SHA-256 hash
    @JsonProperty("alg")
    @Getter
    @Setter
    private String algorithm;
}
