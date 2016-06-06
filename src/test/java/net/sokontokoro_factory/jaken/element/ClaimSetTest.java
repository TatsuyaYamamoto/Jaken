package net.sokontokoro_factory.jaken.element;

import net.sokontokoro_factory.jaken.util.JSONParseUtil;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

/**
 * Created by TATSUYA-PC4 on 2016/06/05.
 */
public class ClaimSetTest {

    @Test
    public void JSON形式の文字列をclaimSetオブジェクトに変換できる(){
        String claimset = "{\"iss\":\"hoge\", \"iat\": 25252}";
        ClaimSet claimSet = JSONParseUtil.toObject(ClaimSet.class, claimset);

        assertThat(claimSet.getIssuer(), is("hoge"));
        assertThat(claimSet.getIssuedAt(), is(25252L));

        String claimset2 = "{\"iss\":\"hoge\"}";
        ClaimSet claimSet2 = JSONParseUtil.toObject(ClaimSet.class, claimset2);

        assertThat(claimSet2.getIssuer(), is("hoge"));
        assertNull(claimSet2.getIssuedAt());

    }

}