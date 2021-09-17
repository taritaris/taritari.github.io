package com.taritari.blog.service.impl;

import com.taritari.blog.entity.Blog;
import com.taritari.blog.mapper.BlogMapper;
import com.taritari.blog.service.BlogService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Author: @taritari
 * @since 2021-09-17
 */
@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {

}
