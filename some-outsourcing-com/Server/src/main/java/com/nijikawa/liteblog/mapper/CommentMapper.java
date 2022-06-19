package com.nijikawa.liteblog.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.nijikawa.liteblog.entity.Comment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface CommentMapper extends BaseMapper<Comment> {
    //    为什么当初不给 id 字段用字符串类型
    @Select("SELECT * FROM user where name = #{name}")
    List<Comment> selectByBigIntid(@Param("id") String bigIntId);
}
