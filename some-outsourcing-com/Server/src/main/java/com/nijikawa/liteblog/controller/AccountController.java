package com.nijikawa.liteblog.controller;
import cn.hutool.core.map.MapUtil;
import cn.hutool.crypto.SecureUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.nijikawa.liteblog.common.dto.LoginDto;
import com.nijikawa.liteblog.common.lang.Result;
import com.nijikawa.liteblog.entity.User;
import com.nijikawa.liteblog.service.UserService;
import com.nijikawa.liteblog.util.JwtUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * 登录控制器
 *
 * @author nijikawa
 */
@RestController
public class AccountController {
    @Autowired
    private
    UserService userService;
    @Autowired
    private
    JwtUtils jwtUtils;
    @PostMapping("/v1/login")
    public Result login(@Validated @RequestBody LoginDto loginDto, HttpServletResponse response) {
        User user = userService.getOne(new QueryWrapper<User>().eq("username", loginDto.getUsername()));
        Assert.notNull(user, "DEV: NO_USER");
        if (!user.getPassword().equals(SecureUtil.md5(loginDto.getPassword()))) {
            return Result.fail("password incorrect");
        }
        String jwt = jwtUtils.generateToken(user.getId());
        response.setHeader("Authorization", jwt);
        response.setHeader("Access-control-Expose-Headers", "Authorization");
        return Result.succ(MapUtil.builder()
                        .put("id", user.getId())
                        .put("username", user.getUsername())
//                .put("avatar", user.getAvatar())
                        .put("email", user.getEmail())
                        .map()
        );
    }
    @RequiresAuthentication
    @GetMapping("/v1/logout")
    public static Result logout() {
        SecurityUtils.getSubject().logout();
        return Result.succ(null);
    }
}
