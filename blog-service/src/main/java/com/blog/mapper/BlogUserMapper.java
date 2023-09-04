package com.blog.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.blog.api.entity.BlogUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BlogUserMapper extends BaseMapper<BlogUser> {

    List<BlogUser> test();
}
