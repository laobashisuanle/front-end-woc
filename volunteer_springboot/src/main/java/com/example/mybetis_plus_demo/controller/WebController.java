package com.example.mybetis_plus_demo.controller;

import cn.hutool.core.util.StrUtil;
import com.example.mybetis_plus_demo.common.AuthAccess;
import com.example.mybetis_plus_demo.common.Result;
import com.example.mybetis_plus_demo.pojo.User;
import com.example.mybetis_plus_demo.service.WebService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/web")
public class WebController {


    @Resource
    private WebService webService;

    @PostMapping("/login")
    @ResponseBody
    @AuthAccess
    public Result login(@RequestBody User user){
        if(StrUtil.isBlank(user.getNumber())){
            return  Result.fail(400,"账号或密码为空，输入不合法");
        }
        if(StrUtil.isBlank(user.getPassword())){
            return  Result.fail(400,"账号或密码为空，输入不合法");
        }
        user =webService.login(user);
        return Result.suc("登录成功",user);
    }
@PostMapping("/register")
@ResponseBody
@AuthAccess
    public Result register(@RequestBody User user){
        if(StrUtil.isBlank(user.getNumber())||StrUtil.isBlank(user.getPassword())){
            return  Result.fail(400,"账号或密码为空，输入不合法");
        }
        user=webService.register(user);
        return Result.suc("注册成功",user);
    }
    @PutMapping("/password")
    @ResponseBody
    @AuthAccess
    public Result resetPassword(@RequestBody User user){
        if(StrUtil.isBlank(user.getNumber())||StrUtil.isBlank(user.getPhone())){
            return  Result.fail(400,"输入数据不合法");
        }
        user=webService.resetPassword(user);
        return Result.suc("重置密码成功",user);
    }




}
