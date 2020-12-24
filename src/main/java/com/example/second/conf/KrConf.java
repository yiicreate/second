package com.example.second.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author: lh
 * @version: 2020/12/23
 * @description:
 */

@Data
@Configuration
@ConfigurationProperties(prefix = "we-chat")
@Component
public class KrConf {
    String appId;

    String secret;
}
