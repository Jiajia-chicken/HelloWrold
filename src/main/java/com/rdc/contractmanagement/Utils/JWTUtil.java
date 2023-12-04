package com.rdc.contractmanagement.Utils;

import cn.hutool.core.date.DateUnit;
import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.rdc.contractmanagement.entity.User;

import java.util.Calendar;
import java.util.Date;

public class JWTUtil {
    private static String secret = "contractmanagement";
    private static Algorithm algorithm = Algorithm.HMAC256(secret);

    public static String createToken(User user){
        Calendar instance = Calendar.getInstance();
        instance.add(Calendar.HOUR,168);
        String token = JWT.create().withClaim("userId", user.getId())
                .withClaim("privilege",user.getPermissionLevel().toString()).
                withExpiresAt(instance.getTime()).sign(algorithm);
        return token;
    }

    public static Boolean verifierToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .build();
            DecodedJWT jwt = verifier.verify(token);
        }catch (JWTVerificationException e){
            //无效的签名/声明
            System.out.println("token过期");
            return false;
        }
        return true;
    }

    public static DecodedJWT getToken(String token){
        return JWT.require(Algorithm.HMAC256(secret)).build().verify(token);
    }

}
