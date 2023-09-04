package com.blog.api.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.blog.api.entity.BlogUser;
import org.apache.dubbo.config.annotation.DubboService;

import java.util.List;

/**
 * @author Wmw
 * @since 2023-08-31
 */

public interface BlogUserService extends IService<BlogUser> {

    List<BlogUser> test();

    //根据用户名称查询用户
    BlogUser getUserByName(String username);

    //用户登录
    BlogUser login(String username,String passward);


}
