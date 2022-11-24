package top.yuchat.patch.server.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

import java.util.Calendar;

public class JWTUtils {

    private static final String sgin = "@$#%&*SGVCUbdhbc9734";

    public static String getToken(long userId) {
        Calendar instance = Calendar.getInstance();
        // 签名默认过期时间是7天
        instance.add(Calendar.HOUR, 12);
        JWTCreator.Builder builder = JWT.create();
        builder.withClaim("userId", userId);
        return builder.withExpiresAt(instance.getTime()).sign(Algorithm.HMAC256(sgin));
    }

    public static Long getUserId(String token) {
        DecodedJWT verify = JWT.require(Algorithm.HMAC256(sgin)).build().verify(token);
        return verify.getClaim("userId").asLong();
    }

}
