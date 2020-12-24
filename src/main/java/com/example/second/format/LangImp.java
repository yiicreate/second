package com.example.second.format;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LangImp implements Lang {
    private Integer code;
    private String info;

    public LangImp(Integer code,String info){
        this.code = code;
        this.info = info;
    }
}
