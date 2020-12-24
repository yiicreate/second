package com.example.second.resolver;

import com.example.second.annotation.CurrentOperator;
import com.example.second.entity.SUser;
import com.example.second.entity.User;
import com.example.second.exception.ComException;
import com.example.second.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import static com.example.second.format.DefaultLang.NOT_LOGIN;
import static com.example.second.format.DefaultLang.NOT_TOKEN;

/**
 * @author: lh
 * @version: 2020/12/23
 * @description:
 */

@Component
public class InterfaceResolver implements HandlerMethodArgumentResolver {
    private final RedisTemplate redisTemplate;

    @Autowired
    public InterfaceResolver(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.hasParameterAnnotation(CurrentOperator.class);//parameter.getParameterType().isAssignableFrom(User.class)
    }

    @Override
    public Object resolveArgument(MethodParameter parameter,
                                  ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest,
                                  WebDataBinderFactory binderFactory) throws Exception {
        String token = webRequest.getHeader("token");

        if (StringUtils.isBlank(token)) throw new ComException(NOT_TOKEN);

        if (!redisTemplate.hasKey(token)) throw new ComException(NOT_LOGIN);
        ValueOperations<String, User> operations = redisTemplate.opsForValue();
        User user = operations.get(token);
        return SUser.of(user);
    }
}
