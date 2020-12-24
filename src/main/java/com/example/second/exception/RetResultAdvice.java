package com.example.second.exception;

import com.example.second.conf.R;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class RetResultAdvice implements ResponseBodyAdvice<Object> {
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if(body instanceof R){
            return  body;
        }

        if(body instanceof String){
            return body;
        }

        if (body == null){
            return R.err();
        }

        return R.of(body);
    }

    @ExceptionHandler(ComException.class)
    public R handler(ComException e){
        return R.of(e.getLang());
    }

    @ExceptionHandler
    public R handler1(Exception e){
        System.out.println("ccc");
        R res = R.err();
        res.setInfo(e.getMessage());
        res.setCode(500);
        if(e instanceof HttpRequestMethodNotSupportedException){
            res.setCode(404);
        }
        return  res;
    }
}
