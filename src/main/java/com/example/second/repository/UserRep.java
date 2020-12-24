package com.example.second.repository;

import com.example.second.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @author: lh
 * @version: 2020/12/23
 * @description:
 */

@Repository
public interface UserRep extends JpaRepository<User,Integer> {
    User findByOpenId(String openid);
}
