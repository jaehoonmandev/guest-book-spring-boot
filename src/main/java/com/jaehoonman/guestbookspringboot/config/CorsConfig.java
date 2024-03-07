package com.jaehoonman.guestbookspringboot.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Cross-Origin Resource Sharing 설정을 통해 출처가 일치하지 않아도 데이터를 공유할 수 있게 설정한다.
 * @apiNote CORS Configuration
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*"
                        ,"https://guestbook.jaehoonman.site/"
                        ,"https://jaehoonman.site")
                .allowedMethods("*",
                        "GET","POST","DELETE")
                .allowedHeaders("*")
                .allowCredentials(false).maxAge(6000);

    }
}
