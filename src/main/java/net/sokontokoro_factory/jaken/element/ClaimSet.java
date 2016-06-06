package net.sokontokoro_factory.jaken.element;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by TATSUYA-PC4 on 2016/06/02.
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ClaimSet {

    /**
     * 各JWTの一意な識別子
     */
    @JsonProperty("jti")
    @Getter
    @Setter
    private String jwtId;

    /**
     * JWTの発行者を判別するための識別子
     * JWTを発行したサーバーのURI等を指定する
     */
    @JsonProperty("iss")
    @Getter
    @Setter
    private String issuer;

    /**
     * JWT発行者から払いだされたユーザーの識別子
     * JWTを使用するシステム上のユーザーID等を指定する
     */
    @JsonProperty("sub")
    @Getter
    @Setter
    private String subject;

    /**
     * JWTの有効期限を示す。有効期限以降のJWTを処理してはならない
     */
    @JsonProperty("exp")
    @Getter
    @Setter
    private Long expirationTime;

    /**
     * JWT を発行した時刻
     */
    @JsonProperty("iat")
    @Getter
    @Setter
    private Long issuedAt;
}