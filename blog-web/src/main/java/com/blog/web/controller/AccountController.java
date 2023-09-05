package com.blog.web.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Assert;
import com.blog.api.dto.LoginDto;
import com.blog.api.entity.BlogUser;
import com.blog.api.entity.Result;
import com.blog.api.service.BlogUserService;
import com.blog.web.authentication.JWTUtil;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;


@RestController
@RequestMapping("/admin")
public class AccountController {

    @DubboReference(version = "1.0.0")
    BlogUserService blogUserService;

    @PostMapping("/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {

        System.out.println("用户名和密码:" + loginDto.getUsername() + " " + loginDto.getPassward());

        //获取到当前用户
        Subject subject = SecurityUtils.getSubject();

        //封装用户名和密码
        System.out.println("封装用户名和密码成功！！！");
        UsernamePasswordToken token = new UsernamePasswordToken(loginDto.getUsername(), loginDto.getPassward());

        try {
            //使用shiro进行用户验证
            subject.login(token);

            //如果验证通过再根据用户名查找到该用户
            BlogUser user = blogUserService.getOne(new QueryWrapper<BlogUser>().eq("username", loginDto.getUsername()));
            Assert.notNull(user, "用户不存在！");

            if (!user.getPassward().equals(loginDto.getPassward())) {
                return Result.fail("密码错误！");
            }
            //根据用户id生成一个jwt
            String jwt = JWTUtil.sign(user.getUsername());

            //将jwt写入
            response.setHeader("authorization", jwt);
            response.setHeader("Access-Control-Expose-Headers", "authorization");

            //正确就返回用户信息
            return Result.success("登陆成功");

        } catch (UnknownAccountException e) {
            return Result.fail("用户不存在！！");
        } catch (IncorrectCredentialsException e) {
            return Result.fail("密码不正确！！");
        }
    }


}
