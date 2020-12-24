package com.example.second.conf;

import com.example.second.resolver.InterfaceResolver;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

/**
 * @author: lh
 * @version: 2020/12/23
 * @description:
 */

@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    private final InterfaceResolver interfaceResolver;


    public WebConfig(InterfaceResolver interfaceResolver) {
        this.interfaceResolver = interfaceResolver;
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        argumentResolvers.add(interfaceResolver);
    }
}
