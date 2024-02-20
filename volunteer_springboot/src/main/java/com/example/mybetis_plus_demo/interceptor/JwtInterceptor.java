package com.example.mybetis_plus_demo.interceptor;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.example.mybetis_plus_demo.common.AuthAccess;
import com.example.mybetis_plus_demo.exception.ServiceException;
import com.example.mybetis_plus_demo.pojo.User;
import com.example.mybetis_plus_demo.service.UserService;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

public class JwtInterceptor implements HandlerInterceptor {

    @Resource
    private UserService userService;

    public boolean PreHandle(HttpServletRequest request, HttpServletResponse response,Object handler){
        String token = request.getHeader("token");
        if(token.isBlank()){
            token = request.getParameter("token");
        }


         //拦截路径相关配置
        if(handler instanceof  HandlerMethod){
            AuthAccess annotation =((HandlerMethod) handler).getMethodAnnotation(AuthAccess.class);
            if(annotation!=null){
                return true;
            }
        }




        if(token.isBlank()){
            //401为权限问题
            throw  new ServiceException(401,"请登录");
        }
        String userId;
        try{
            userId = JWT.decode(token).getAudience().get(0);
        }catch (JWTDecodeException e){
            throw new ServiceException(401,"请登录");
        }
        User user = userService.getById(Integer.valueOf(userId));
        if(user==null){
            throw new ServiceException(401,"请登录");
        }
        //通过密码加密后生成一个验证器
        JWTVerifier jwtVerifier=JWT.require(Algorithm.HMAC256(user.getPassword())).build();
        try {
            jwtVerifier.verify(token);
        }catch (JWTVerificationException e){
            throw  new ServiceException(401,"请登录");
        }
        return true;
    }

}
