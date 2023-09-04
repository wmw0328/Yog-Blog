package com.blog.web.authentication;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author: 24065
 * @create: 2023-09-04 14:55
 * @Description: token过滤器
 */
public class JWTToken implements AuthenticationToken {

    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }

}
