package com.example.mybetis_plus_demo.service.impl;

import com.example.mybetis_plus_demo.exception.ServiceException;
import com.example.mybetis_plus_demo.mapper.UserMapper;
import com.example.mybetis_plus_demo.pojo.User;
import com.example.mybetis_plus_demo.service.WebService;
import com.example.mybetis_plus_demo.utils.TokenUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WebServiceImpl implements WebService {

    @Resource
    private UserMapper userMapper;

    @Override
    public User login(User user) {

        User dbUser =userMapper.getByNo(user.getNumber());
        if(dbUser==null){
            throw new ServiceException("不存在该用户");
        }
        if(!user.getPassword().equals(dbUser.getPassword())){
            throw new ServiceException("账号或密码输入错误");
        }
   //验证通过后就创建一个token，并返回到前端
        String token = TokenUtils.createToken(dbUser.getId().toString(),dbUser.getPassword());
        dbUser.setToken(token);
        return dbUser;
    }

    @Override
    public  User register(User user) {

       User dbUser =userMapper.getByNo(user.getNumber());
        if(dbUser!=null){
            throw new ServiceException("该账号已存在");
        }

         userMapper.save(user);
        return user;
    }

    @Override
    public User resetPassword(User user) {

        User dbUser =userMapper.getByNo(user.getNumber());
        if(dbUser==null){
            throw new ServiceException("不存在该用户");
        }
        if(!user.getPhone().equals(dbUser.getPhone())){
            throw new ServiceException("验证错误");
        }
        dbUser.setPassword("123");
        userMapper.update(dbUser);
        return dbUser;
    }
}
