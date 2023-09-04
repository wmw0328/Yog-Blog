package com.blog.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.api.entity.BlogUser;
import com.blog.api.service.BlogUserService;
import com.blog.mapper.BlogUserMapper;
import org.apache.dubbo.config.annotation.DubboService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * @author Wmw
 * @since 2023-08-31
 */
@DubboService(version = "1.0.0")
public class BlogUserServiceImpl extends ServiceImpl<BlogUserMapper, BlogUser> implements BlogUserService {

    @Autowired
    BlogUserMapper blogUserMapper;

    @Override
    public List<BlogUser> test() {
        return Collections.singletonList(blogUserMapper.selectById(1));
    }

    @Override
    public BlogUser getUserByName(String username) {
        QueryWrapper<BlogUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        wrapper.eq("status",1);
        return blogUserMapper.selectOne(wrapper);
    }

    @Override
    public BlogUser login(String username, String passward) {
        QueryWrapper<BlogUser> wrapper = new QueryWrapper<>();
        wrapper.eq("username",username);
        wrapper.eq("passward",passward);
        wrapper.eq("status",1);
        return blogUserMapper.selectOne(wrapper);
    }
}
