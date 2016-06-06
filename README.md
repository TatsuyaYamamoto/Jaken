# Jaken

[![Build Status](https://travis-ci.org/TatsuyaYamamoto/Jaken.svg?branch=dev)](https://travis-ci.org/TatsuyaYamamoto/Jaken)

[![Coverage Status](https://coveralls.io/repos/github/TatsuyaYamamoto/Jaken/badge.svg?branch=dev)](https://coveralls.io/github/TatsuyaYamamoto/Jaken?branch=dev)

Jaken is a Library of generating and verifying JSON Web Token (JWT).
This is implemented against [draft-ietf-oauth-json-web-token](https://self-issued.info/docs/draft-ietf-oauth-json-web-token.html)


## USAGE

- generate JWT

    ```
    Jaken jaken = Jaken.getBuilder()
                    .setAlgorithm(JakenAlgorithm.RSASSA_PKCS1_v1_5_using_SHA256)
                    .setJwtId("jwtId")
                    .setIssuer("issuer")
                    .setSubject("subject")
                    .setExpired("expired")
                    .build();

    ```

- verify JWT

    ```
    Jaken.getVerifier().setJwt("jwt").setKey("key").confirms();
    ```