package com.example.mybetis_plus_demo.utils;


import cn.hutool.core.date.DateUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.example.mybetis_plus_demo.mapper.UserMapper;
import com.example.mybetis_plus_demo.pojo.User;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Date;

@Component
public class TokenUtils {

    @Resource
    private UserMapper userMapper;

    private static UserMapper staticUserMapper;

    public  void setUserService(){
        staticUserMapper =userMapper;
    }

    public static String createToken(String userId, String sign){

        return JWT.create().withAudience(userId)    //将userid放入token中作为载荷
                .withExpiresAt(DateUtil.offsetHour(new Date(),2))   //2小时后token消失
                .sign(Algorithm.HMAC256(sign));      //这里用password做为token密钥

    }


    //根据当前token获取用户数据
    public static User getCurrentUser(){
        HttpServletRequest request =((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        try{
            String token =request.getHeader("token");
            if(!(token ==null)){
                String userId = JWT.decode(token).getAudience().get(0);
                return staticUserMapper.getById(Integer.valueOf(userId));
            }

        }catch(Exception e){
            return  null;
        }
        return null;
    }



}
