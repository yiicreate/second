package com.example.second.controller;

import com.example.second.annotation.CurrentOperator;
import com.example.second.entity.SUser;
import com.example.second.entity.User;
import com.example.second.service.UserService;
import com.example.second.util.WeChatUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * @author: lh
 * @version: 2020/12/23
 * @description:
 */

@RestController
@Slf4j
public class LoginController {
    @Autowired
    private UserService userService;

    @Autowired
    private WeChatUtil weChatUtil;


    @PostMapping("/login")
    public User doLogin(@RequestParam("code") String code) throws IOException {
        String openid = "oPX645P4TgwSLpwsYEr91yCOWs9Q";//weChatUtil.getOpenId(code);
        if(openid == null){
            return  null;
        }
        return  userService.findByOpenId(openid);
    }

    @PostMapping("/register")
    public User register(@RequestParam("code") String code,
                         @RequestParam("phone") Integer phone,
                         @RequestParam("userName") String userName) throws IOException {
        String openid =  weChatUtil.getOpenId(code);
        if(openid == null){
            return  null;
        }
        return userService.register(userName,openid,phone);
    }

    @GetMapping("/self")
    public SUser self(@CurrentOperator SUser user){
        return  user;
    }
}
