package org.example.exception;

import org.example.common.R;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice //全局异常处理器
public class GlobalExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    public R error(Exception e){
//        e.printStackTrace();
//        return R.error(500, "执行错误");
//    }
//

}
