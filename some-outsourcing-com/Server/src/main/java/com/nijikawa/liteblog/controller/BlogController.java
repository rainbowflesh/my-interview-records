package com.nijikawa.liteblog.controller;
import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nijikawa.liteblog.common.lang.Result;
import com.nijikawa.liteblog.entity.Blog;
import com.nijikawa.liteblog.entity.Comment;
import com.nijikawa.liteblog.entity.Search;
import com.nijikawa.liteblog.mapper.CommentMapper;
import com.nijikawa.liteblog.service.BlogService;
import com.nijikawa.liteblog.service.CommentService;
import com.nijikawa.liteblog.service.SearchService;
import com.nijikawa.liteblog.util.ShiroUtil;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

@RestController
public class BlogController {
    @Autowired
    BlogService blogService;
    @Autowired
    CommentService commentService;
    @Autowired
    SearchService searchService;
    //分页
    @GetMapping("/v1/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage) {
        Page page = new Page(currentPage, 3);
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));
        return Result.succ(pageData);
    }
    //查询文章
    @GetMapping("/v1/blog/{id}")
    public Result detail(@PathVariable(name = "id") Long id) {
        Blog blog = blogService.getById(id);
        Assert.notNull(blog, "该文章已被删除");
        return Result.succ(blog);
    }
    //编辑添加
    @RequiresAuthentication
    @PostMapping("/v1/blog/edit")
    public Result edit(@Validated @RequestBody Blog blog) {
        Blog temp = null;
        if (null != blog.getId()) {
            temp = blogService.getById(blog.getId());
            // 只能编辑自己的文章
//            System.out.println(ShiroUtil.getProfile().getId());
            Assert.isTrue(temp.getUserId().longValue() == ShiroUtil.getProfile().getId().longValue(), "没有权限编辑");
        } else {
            temp = new Blog();
            temp.setUserId(ShiroUtil.getProfile().getId());
            temp.setCreated(LocalDateTime.now());
            temp.setStatus(0);
        }
        BeanUtil.copyProperties(blog, temp, "id", "userId", "created", "status");
        blogService.saveOrUpdate(temp);
        return Result.succ(null);
    }
    //    评论点赞
    @PostMapping("/v1/blog/comment")
    public Result comment(@Validated @RequestBody Comment comment) {
        Comment query = new Comment();
//        String bigIntId = String.valueOf(comment.getId());
//        TODO: 获取点赞+评论, 绑定, 然后query
//        TODO: mbp 对 PostgreSQL 支持太差了, id 类型都不会自动转码, 或者不该给 id 设置成数值类型
//        List list = commentService.selectByBigIntid(bigIntId);
        return Result.succ("迫真点赞了");
//        return Result.succ(commentService.updateById(query));
    }
    //    搜索
//    前端传来的直接是三个参数, 在方法里遍历查找
    @PostMapping("/v1/blog/search")
    public Result search(@RequestBody Search search) {
        List<Search> title, content, description;
        title = searchService.list(new QueryWrapper<Search>().like("title", search.getTitle()));
        if (title.isEmpty()) {
            content = searchService.list(new QueryWrapper<Search>().like("content", search.getTitle()));
            if (content.isEmpty()) {
                description = searchService.list(new QueryWrapper<Search>().like("description", search.getDescription()));
                if (description.isEmpty()) {
                    return Result.succ(null);
                } else {
                    return Result.succ(description);
                }
            } else {
                return Result.succ(content);
            }
        } else {
            return Result.succ(title);
        }
    }
}
