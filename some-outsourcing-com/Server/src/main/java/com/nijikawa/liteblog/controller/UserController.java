package com.nijikawa.liteblog.controller;
import com.nijikawa.liteblog.common.lang.Result;
import com.nijikawa.liteblog.entity.User;
import com.nijikawa.liteblog.service.UserService;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/user")
public class UserController {
    @Autowired
    UserService userService;
    //认证登录
    @RequiresAuthentication
    @GetMapping("/v1/index")
    public Result index() {
        User user = userService.getById(1L);
        return Result.succ(user);
    }
    @PostMapping("/v1/save")
    public Result save(@Validated @RequestBody User user) {
        return Result.succ(user);
    }
}
