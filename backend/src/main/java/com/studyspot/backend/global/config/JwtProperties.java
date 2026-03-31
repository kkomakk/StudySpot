package com.studyspot.backend.global.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * application.yml의 jwt 설정값 바인딩 클래스
 */
@Getter
@Setter
@Component
@ConfigurationProperties(prefix = "jwt")
public class JwtProperties {

    private String secret;
    private long accessTokenExpiration;
}