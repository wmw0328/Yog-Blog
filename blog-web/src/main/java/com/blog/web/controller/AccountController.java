package com.blog.web.controller;

import com.blog.api.entity.BlogUser;
import com.blog.api.entity.Result;
import com.blog.api.service.BlogUserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/admin")
public class AccountController {

    @DubboReference(version = "1.0.0")
    BlogUserService blogUserService;

    @GetMapping("/login")
    public Result login(@Param("username") String username, @Param("passward")String passward) {
        BlogUser user = blogUserService.login(username, passward);
        if (user == null) {
            return Result.fail("用户名称或密码不存在");
        }
        return Result.success("登录成功");
    }
}
