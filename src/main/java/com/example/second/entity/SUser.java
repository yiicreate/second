package com.example.second.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SUser implements Serializable {

    Integer id;

    String userName;

    String name;

    String openId;

    Integer phone;

    String token;

    public static SUser of(User user) {
        return SUser.builder()
                .id(user.getId())
                .userName(user.getUserName())
                .openId(user.getOpenId())
                .name(user.getName())
                .phone(user.getPhone())
                .token(user.getToken())
                .build();
    }
}
