package com.blog.web.authentication;

import com.blog.api.entity.BlogUser;
import com.blog.api.service.BlogUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;


/**
 * @author: 24065
 * @create: 2023-09-04 15:06
 * @Description: 自定义实现 ShiroRealm，包含认证和授权两大模块
 */

@Slf4j
@Component
public class ShiroRealm extends AuthorizingRealm {

    @DubboReference(version = "1.0.0")
    public BlogUserService blogUserService;

    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * `
     * 授权模块，获取用户角色和权限
     *
     * @param token token
     * @return AuthorizationInfo 权限信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection token) {
        String username = JWTUtil.getUsername(token.toString());
        BlogUser user = blogUserService.getUserByName(username);

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();

        // 获取用户角色集（模拟值，实际从数据库获取）
        //simpleAuthorizationInfo.setRoles(user.getRole());

        // 获取用户权限集（模拟值，实际从数据库获取）
        //simpleAuthorizationInfo.setStringPermissions(user.getPermission());
        return simpleAuthorizationInfo;
    }

    /**
     * 用户认证
     *
     * @param authenticationToken 身份认证 token
     * @return AuthenticationInfo 身份认证信息
     * @throws AuthenticationException 认证相关异常
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 这里的 token是从 JWTFilter 的 executeLogin 方法传递过来的，已经经过了解密
        String token = (String) authenticationToken.getCredentials();

        String username = JWTUtil.getUsername(token);

        if (StringUtils.isBlank(username))
            throw new AuthenticationException("token校验不通过");

        // 通过用户名查询用户信息
        BlogUser user = blogUserService.getUserByName(username);

        if (user == null)
            throw new AuthenticationException("用户名或密码错误");
        if (!JWTUtil.verify(token, username))
            throw new AuthenticationException("token校验不通过");
        return new SimpleAuthenticationInfo(token, token, "shiro_realm");
    }





}
