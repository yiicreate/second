package com.example.second.service.Imp;

import com.example.second.entity.User;
import com.example.second.repository.UserRep;
import com.example.second.service.UserService;
import com.example.second.util.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
 * @author: lh
 * @version: 2020/12/23
 * @description:
 */


@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserRep userRep;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public User findByOpenId(String openId) {
        return  userRep.findByOpenId(openId);
    }

    @Override
    public User findByUserName(String userName) {
        return null;
    }

    @Override
    public User loginIn(String code) {
        return null;
    }

    @Override
    public User loginIn(String user, String password) {
        return null;
    }

    @Override
    public User register(String userName, String openId, Integer phone) {
        User user = new User();
        user.setUserName(userName);
        user.setOpenId(openId);
        user.setPhone(phone);
        String token = TokenUtil.generate();
        user.setToken(token);
        userRep.save(user);
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        operations.set(token,user);
       return user;
    }

    @Override
    public User findById(Integer id) {
        return userRep.getOne(id);
    }

}
