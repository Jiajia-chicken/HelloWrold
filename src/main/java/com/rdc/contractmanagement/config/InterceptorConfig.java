package com.rdc.contractmanagement.config;

import com.rdc.contractmanagement.interceptors.LoginInterceptor;
import com.rdc.contractmanagement.interceptors.ContractInterceptor;
import com.rdc.contractmanagement.interceptors.SuperInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //所有路径都被拦截
        //添加不拦截路径
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/user/login","/user/regist","/user/getCode");
//        //合同更新、增加合同、删除合同
        registry.addInterceptor(new ContractInterceptor()).addPathPatterns("/contract/update","/contract/input","/contract/delete");
//        //管理员过滤
        registry.addInterceptor(new SuperInterceptor()).addPathPatterns("/user/getUsers","/user/editUser","/user/del");
    }
}
