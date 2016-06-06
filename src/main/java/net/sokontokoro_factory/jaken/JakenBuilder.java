package net.sokontokoro_factory.jaken;

import net.sokontokoro_factory.jaken.element.ClaimSet;
import net.sokontokoro_factory.jaken.element.Header;
import net.sokontokoro_factory.jaken.logic.HmacSHA256;
import net.sokontokoro_factory.jaken.logic.RSASHA256;
import net.sokontokoro_factory.jaken.logic.SignatureEngines;
import net.sokontokoro_factory.jaken.util.JSONParseUtil;

import java.security.Key;
import java.util.Base64;

public class JakenBuilder {
	private  Jaken jaken;
	private SignatureEngines signatureEngines;

	JakenBuilder(){
		this.jaken = new Jaken();

		/* jaken init */
		this.jaken.setHeader(new Header());
		this.jaken.getHeader().setType(Jaken.JWT_HEADER_TYPE);
		this.jaken.setClaimSet(new ClaimSet());
		this.jaken.getClaimSet().setIssuedAt(System.currentTimeMillis());
	}

	/* set key for signature */
	public JakenBuilder setKey(Key key){
		this.jaken.setKey(key);
		return this;
	}

	/* for setup header */
	public JakenBuilder setAlgorithm(JakenAlgorithm algorithm){
		this.jaken.getHeader().setAlgorithm(algorithm.getValue());
		switch (algorithm){
			case HMAC_using_SHA256:
				signatureEngines = new HmacSHA256();
				break;
			case RSASSA_PKCS1_v1_5_using_SHA256:
				signatureEngines = new RSASHA256();
				break;
		}
		return this;
	}

	/* for setup  claim set*/
	public JakenBuilder setJwtId(String jwtId){
		this.jaken.getClaimSet().setJwtId(jwtId);
		return this;
	}
	public JakenBuilder setIssuer(String issuer){
		this.jaken.getClaimSet().setIssuer(issuer);
		return this;
	}
	public JakenBuilder setSubject(String subject){
		this.jaken.getClaimSet().setSubject(subject);
		return this;
	}
	public JakenBuilder setExpired(Long expiredTimeMillis){
		this.jaken.getClaimSet().setExpirationTime(expiredTimeMillis);
		return this;
	}

	/**
	 * JWTを作成してJakenオブジェクを返却する
	 *
	 * @return
     */
	public Jaken build()throws JakenException{
		if(signatureEngines == null){
			throw new JakenException("not specified signature algorithm.");
		}

		String headerJson = JSONParseUtil.toJSON(jaken.getHeader());
		String claimSetJson = JSONParseUtil.toJSON(jaken.getClaimSet());

		String headerBase64 = Base64.getUrlEncoder().encodeToString(headerJson.getBytes());
		String claimSetBase64 = Base64.getUrlEncoder().encodeToString(claimSetJson.getBytes());

		// 署名用keyを設定されている場合、それを使用して署名を行う
		Key key = this.jaken.getKey();
		if(key != null){
			signatureEngines.setKey(key);
		}
		byte[] signature = signatureEngines.encrypt(headerBase64 + "." + claimSetBase64);
		String signatureBase64 = Base64.getUrlEncoder().encodeToString(signature);

		// JWTを作成する
		String jwt = headerBase64 + "." + claimSetBase64 + "." + signatureBase64;

		this.jaken.setJwt(jwt);
		this.jaken.setKey(signatureEngines.getKey());
		return this.jaken;
	}
}
