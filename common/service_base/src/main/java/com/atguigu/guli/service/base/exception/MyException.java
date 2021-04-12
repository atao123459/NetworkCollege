package com.atguigu.guli.service.base.exception;

import com.atguigu.guli.common.base.result.ResultCodeEnum;
import lombok.Data;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 继承runtime可以在抛出后进行异常捕获
 */
@Data
public class MyException extends RuntimeException {
    private Integer code;

//    public MyException(String message, Integer code) {
//        super(message);
//        this.code = code;
//    }

    public MyException(ResultCodeEnum resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    @Override
    public  String toString(){
        return "MyException{"+"code="+ code + "message:" + this.getMessage() + "}";
    }
}
