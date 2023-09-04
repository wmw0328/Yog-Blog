package com.blog.web.controller;

import com.blog.api.entity.BlogUser;
import com.blog.api.service.BlogUserService;
import org.apache.dubbo.config.annotation.DubboReference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AccountController {

    @DubboReference(version = "1.0.0")
    BlogUserService blogUserService;

    @GetMapping("/test")
    List<BlogUser> test(){
        return blogUserService.test();
    }
}
