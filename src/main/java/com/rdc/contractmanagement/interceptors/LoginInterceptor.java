package com.rdc.contractmanagement.interceptors;

import com.rdc.contractmanagement.Utils.JWTUtil;
import org.springframework.web.servlet.HandlerInterceptor;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler){
        //判断是不是第一次登录
        String token = request.getHeader("token");
        if(token!=null){
            //说明有登录，检验过期情况
            if(!JWTUtil.verifierToken(token)){
                //token过期
                response.setStatus(401);
                return false;
            }
            return true;
        }
        //未登录
        response.setStatus(403);
        return false;
    }
}
