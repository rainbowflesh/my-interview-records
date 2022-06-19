package com.nijikawa.liteblog.service.impl;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.nijikawa.liteblog.entity.Comment;
import com.nijikawa.liteblog.mapper.CommentMapper;
import com.nijikawa.liteblog.service.CommentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment> implements CommentService {
    @Override
    public List<Comment> selectByBigIntid(String bigIntId) {
        return baseMapper.selectByBigIntid(bigIntId);
    }
}
