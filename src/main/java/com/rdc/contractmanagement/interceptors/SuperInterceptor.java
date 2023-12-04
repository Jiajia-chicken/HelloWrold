package com.rdc.contractmanagement.interceptors;

import com.rdc.contractmanagement.Utils.JWTUtil;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SuperInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //获取token中的权限信息
        String token = request.getHeader("token");
        String privilege = JWTUtil.getToken(token).getClaim("privilege").asString();
        if(Integer.parseInt(privilege)>1){
            //不是超级管理员
            response.setStatus(403);
            return false;
        }
        return true;
    }
}
