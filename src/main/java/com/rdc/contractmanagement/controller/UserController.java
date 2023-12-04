package com.rdc.contractmanagement.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.rdc.contractmanagement.bean.CommonResult;
import com.rdc.contractmanagement.entity.User;
import com.rdc.contractmanagement.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Autowired
    private UserService userService;
    @Autowired
    private DefaultKaptcha defaultKaptcha;

    @PostMapping("/login")
    public CommonResult login(HttpServletRequest request, User user, String code){
        System.out.println("登录的session是："+request.getSession().getId());
        //检验验证码
        String text = stringRedisTemplate.opsForValue().get(request.getSession().getId());
        if(!code.equals(text)){
            // 验证码不相同
            return CommonResult.fail("验证码错误");
        }
        //验证码正确
        return userService.login(user);
    }

    @PostMapping("/regist")
    public CommonResult regist(User user){
        return userService.regist(user);
    }

    @GetMapping("/getUsers")
    public CommonResult getUsers(Long page){
        return userService.getUsers(page);
    }

    @PostMapping("/editUser")
    public CommonResult editUser(User user){
        return userService.editUser(user);
    }

    @PostMapping("/del")
    public CommonResult del(Integer id){
        return userService.del(id);
    }

    @PostMapping
    public CommonResult quit(){
        return CommonResult.success();
    }


    @GetMapping(value = "getCode",produces = "image/jpg")
    public void getCode(HttpServletRequest request, HttpServletResponse response){
        // 定义response输出类型为image/jpeg类型
        response.setDateHeader("Expires", 0);
        // Set standard HTTP/1.1 no-cache headers.
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        // Set IE extended HTTP/1.1 no-cache headers (use addHeader).
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        // Set standard HTTP/1.0 no-cache header
        response.setHeader("Pragma", "no-cache");
        // return a jpeg
        response.setContentType("image/jpeg");

        //-------------------生成验证码 begin --------------------------
        //获取验证码文本内容
        String text = defaultKaptcha.createText();
        System.out.println("验证码内容："+text);
        //将验证码文本内容放入redis
        stringRedisTemplate.opsForValue().set(request.getSession().getId(),text,10,TimeUnit.MINUTES);
        response.addCookie(new Cookie("path","/"));
        response.addCookie(new Cookie("SameSite","None"));

        BufferedImage image = defaultKaptcha.createImage(text);
        ServletOutputStream outputStream = null;
        try {
            outputStream = response.getOutputStream();
            //输出流输出图片，格式为jpg
            ImageIO.write(image,"jpg",outputStream);
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (null!=outputStream){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
