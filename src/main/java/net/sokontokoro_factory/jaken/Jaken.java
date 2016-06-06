package net.sokontokoro_factory.jaken;

import lombok.Getter;
import lombok.Setter;
import net.sokontokoro_factory.jaken.element.*;
import net.sokontokoro_factory.jaken.element.Signature;

import java.security.*;

public class Jaken {
	public static final String JWT_HEADER_TYPE = "JWT";

	private String issuer;
	private String subject;
	private String audience;

	@Getter
	@Setter
	private String jwt;

	@Getter
	@Setter
	private Key key;
	@Getter
	@Setter
	private Header header;
	@Getter
	@Setter
	private ClaimSet claimSet;
	@Getter
	@Setter
	private Signature signature;

	public Jaken(){

	}

	public static JakenBuilder getBuilder(){
		return new JakenBuilder();
	}
	public static JakenVerifier getVerifier(){
		return new JakenVerifier();
	}

	public String create(
			String jwtId,
			long validPeriodMillis,
			long notBeforeTimeMillis){
		Header header = new Header();
		ClaimSet claimSet = new ClaimSet();

		String headerBase64 = "";
		String claimSetBase64 = "";
		String signatureBase64 = "";


		String signature;

		StringBuilder jaken = new StringBuilder();
		jaken.append(headerBase64);
		jaken.append(".");
		jaken.append(claimSetBase64);
		jaken.append(".");
		jaken.append(signatureBase64);

		return jaken.toString();
	}
}