package com.rdc.contractmanagement.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommonResult {
    /**
     *状态码
     */
    private Integer code;

    /**
     * 响应信息
     */
    private String message;

    /**
     * 数据
     */
    private Object data;

    /**
     * 成功
     * @return
     */
    public static CommonResult success(){
        return new CommonResult(200,null,null);
    }

    /**
     * 成功
     * @param message 响应信息
     * @return
     */
    public static CommonResult success(String message){
        return new CommonResult(200,message,null);
    }

    /**
     * 成功
     * @param data 返回数据
     * @return
     */
    public static CommonResult success(Object data){
        return new CommonResult(200,null,data);
    }
    /**
     *
     * @param message 响应信息
     * @param data 数据
     * @return
     */
    public static CommonResult success(String message,Object data){
        return new CommonResult(200,message,data);
    }

    /**
     * 失败
     * @return
     */
    public static CommonResult fail(){
        return new CommonResult(201,null,null);
    }

    /**
     * 失败
     * @param message 报错信息
     * @return
     */
    public static CommonResult fail(String message){
        return new CommonResult(201,message,null);
    }

    /**
     * 权限错误
     * @return
     */
    public static CommonResult permissionError(){
        return new CommonResult(203,null,null);
    }

    /**
     * 参数错误
     * @return
     */
    public static CommonResult parameterError(){
        return new CommonResult(204,null,null);
    }


}
