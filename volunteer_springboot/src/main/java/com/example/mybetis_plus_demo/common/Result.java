package com.example.mybetis_plus_demo.common;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonIgnoreProperties
@JsonFormat

public class Result<D> implements Serializable {

    private Integer code;
    private String message;
    private Integer total;
    private Object data;


    public  static Result suc(Object data){
        return result(200,"成功",null,data);
    }

    public  static  Result suc(){return result(200,"成功",null,null);};
    public  static Result suc(Integer total,Object data){
        return result(200,"成功",total,data);
    }
    public static Result suc(String message,Object data){
        return result(200,message,null,data);
    }

    public static Result fail(Integer code,String message){
        return result(code,message,null,null);
    }


    private static Result result(Integer code,String message , Integer total,Object data){
        Result result =new Result();
        result.setData(data);
        result.setCode(code);
        result.setMessage(message);
        result.setTotal(total);
        return  result;
    }

}
