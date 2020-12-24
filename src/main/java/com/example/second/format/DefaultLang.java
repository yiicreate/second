package com.example.second.format;

import lombok.Getter;


@Getter
public enum DefaultLang implements Lang {
    SCC(0,"成功"),
    ERR(99,"失败"),
    NOT_LOGIN(100,"未登录"),
    NOT_TOKEN(200,"没有token");

    private Integer code;
    private String info;

    DefaultLang(Integer code, String info){
        this.code = code;
        this.info = info;
    }
}
