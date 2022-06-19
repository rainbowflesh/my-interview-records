package com.nijikawa.liteblog.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.nijikawa.liteblog.entity.Comment;

import java.util.List;

public interface CommentService extends IService<Comment> {
    List<Comment> selectByBigIntid(String bigIntId);
}
