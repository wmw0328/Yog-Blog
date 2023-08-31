package com.blog.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.blog.api.entity.BlogUser;
import com.blog.api.service.BlogUserService;
import com.blog.mapper.BlogUserMapper;
import org.springframework.stereotype.Service;

/**
 * @author Wmw
 * @since 2023-08-31
 */
@Service
public class BlogUserServiceImpl extends ServiceImpl<BlogUserMapper, BlogUser> implements BlogUserService {

}
