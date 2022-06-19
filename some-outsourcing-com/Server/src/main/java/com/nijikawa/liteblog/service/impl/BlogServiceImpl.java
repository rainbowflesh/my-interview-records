package com.nijikawa.liteblog.service.impl;
import com.nijikawa.liteblog.entity.Blog;
import com.nijikawa.liteblog.service.BlogService;
import com.nijikawa.liteblog.mapper.BlogMapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class BlogServiceImpl extends ServiceImpl<BlogMapper, Blog> implements BlogService {
}
