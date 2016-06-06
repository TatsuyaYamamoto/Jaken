package net.sokontokoro_factory.jaken.element;

import net.sokontokoro_factory.jaken.Jaken;
import net.sokontokoro_factory.jaken.JakenAlgorithm;
import net.sokontokoro_factory.jaken.util.JSONParseUtil;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by TATSUYA-PC4 on 2016/06/05.
 */
public class HeaderTest {
    @Test
    public void JSON形式のheader文字列を作成できる(){
        Header header = new Header();
        header.setType(Jaken.JWT_HEADER_TYPE);
        header.setAlgorithm(JakenAlgorithm.HMAC_using_SHA256.getValue());

        String headerString = JSONParseUtil.toJSON(header);
        Header headerJson = JSONParseUtil.toObject(Header.class, headerString);

        assertThat(headerJson.getType(), is("JWT"));
        assertThat(headerJson.getAlgorithm(), is(JakenAlgorithm.HMAC_using_SHA256.getValue()));
    }

    @Test
    public void JSON文字列をheaderオブジェクトに変換できる(){
        String actualType = "JWTT";
        String actualAlgrithm = "HS256";

        String source = "{\"typ\":\"" + actualType + "\",\"alg\":\""+ actualAlgrithm + "\"}";
        Header header = JSONParseUtil.toObject(Header.class, source);

        assertThat(header.getType(), is(actualType));
        assertThat(header.getAlgorithm(), is(actualAlgrithm));
    }

}