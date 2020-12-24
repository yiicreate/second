package com.example.second.service;

import com.example.second.entity.User;

/**
 * @author: lh
 * @version: 2020/12/23
 * @description:
 */

public interface UserService {
    User findByOpenId(String openId);

    User findByUserName(String userName);

    User loginIn(String code);

    User loginIn(String user,String password);

    User register(String userName,String openId,Integer phone);

    User findById(Integer id);

}
