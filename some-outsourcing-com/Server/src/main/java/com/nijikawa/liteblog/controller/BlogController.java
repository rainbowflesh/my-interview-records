package com.nijikawa.liteblog.controller;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.nijikawa.liteblog.common.lang.Result;
import com.nijikawa.liteblog.entity.Blog;
import com.nijikawa.liteblog.entity.Comment;
import com.nijikawa.liteblog.entity.Search;
import com.nijikawa.liteblog.service.BlogService;
import com.nijikawa.liteblog.service.CommentService;
import com.nijikawa.liteblog.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * 博客操作控制器
 *
 * @author nijikawa
 */
@RestController
public class BlogController {
    @Autowired
    private
    BlogService blogService;
    @Autowired
    private
    CommentService commentService;
    @Autowired
    private
    SearchService searchService;
    // 分页
    @GetMapping("/v1/blogs")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage) {
        Page page = new Page(currentPage, 3L);
        IPage pageData = blogService.page(page, new QueryWrapper<Blog>().orderByDesc("created"));
        return Result.succ(pageData);
    }
    // 查询文章
    @GetMapping("/v1/blog/{id}")
    public Result detail(@PathVariable(name = "id") String id) {
        int intId = Integer.parseInt(id);
        Blog blog = blogService.getById(intId);
        Assert.notNull(blog, "该文章已被删除");
        return Result.succ(blog);
    }
    // 发表文章
    @PostMapping("/v1/blog/post")
    public Result post(@Validated @RequestBody Blog blog) {
        Blog query = new Blog();
        query.setUser_id(blog.getUser_id());
        query.setTitle(blog.getTitle());
        query.setDescription(blog.getDescription());
        query.setContent(blog.getContent());
        query.setCreated(LocalDateTime.now());
        System.out.println(query);
        return Result.succ(blogService.save(query));
    }
    // 评论点赞
    @PostMapping("/v1/blog/comment")
    public Result comment(@Validated @RequestBody Comment comment) {
        Comment query = new Comment();
        query.setId(comment.getId());
        query.setRemark(comment.getRemark());
        return Result.succ(commentService.updateById(query));
    }
    @PostMapping("/v1/blog/votes")
    public Result votes(@Validated @RequestBody Comment comment) {
        Comment query = new Comment();
        query.setId(comment.getId());
        query.setVotes(comment.getVotes());
        return Result.succ(commentService.updateById(query));
    }
    // 搜索
    @PostMapping("/v1/blog/search")
    public Result search(@RequestBody Search search) {
        if (null == search.getContent() || null == search.getDescription() || null == search.getTitle()) {
            return Result.fail("搜索内容不能为空");
        }
        // 应该有更好的方法遍历
        // TODO: 优化搜索
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
