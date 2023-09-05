package com.blog.web.authentication;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import java.util.Date;


/**
 * @author: 24065
 * @create: 2023-09-04 14:39
 * @Description: JWT工具类
 */
@Component
@Slf4j
public class JWTUtil {

//    private static Logger log = LoggerFactory.getLogger(JWTUtil.class);
//    private static final long EXPIRE_TIME = SpringContextUtil.getBean(SystemProperties.class).getJwtTimeOut() * 1000;

    // 设置过期时间为24h
    private static final long EXPIRE_TIME = 24 * 60 * 60 * 1000;
    // 密钥
    private static final String SECRET = "SHIRO+JWT";


    /**
     * 校验 token是否正确
     * @param token
     * @param username
     * @return
     */
    public static boolean verify(String token, String username) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim("username", username)
                    .build();
            verifier.verify(token);
            log.info("token is valid");
            return true;
        } catch (Exception e) {
            log.info("token is invalid{}", e.getMessage());
            return false;
        }
    }

    /**
     * 从 token中获取用户名
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("username").asString();
        } catch (JWTDecodeException e) {
            log.error("error：{}", e.getMessage());
            return null;
        }
    }

    /**
     * 生成 token
     *
     * @param username 用户名
     * @return token
     */
    public static String sign(String username) {
        try {
            username = StringUtils.lowerCase(username);
            Date date = new Date(System.currentTimeMillis() + EXPIRE_TIME);
            Algorithm algorithm = Algorithm.HMAC256(SECRET);
            return JWT.create()
                    .withClaim("username", username)
                    .withExpiresAt(date)
                    .sign(algorithm);
        } catch (Exception e) {
            log.error("error：{}", e);
            return null;
        }
    }
}
