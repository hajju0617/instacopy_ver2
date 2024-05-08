package com.green.greengram.common;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {
    private final String uploadPath;

    public WebMvcConfiguration(@Value("${file.directory}") String uploadPath) {
        this.uploadPath = uploadPath;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) { // 외부파일과 연결
        registry.addResourceHandler("/pic/**").addResourceLocations("file:" + uploadPath);
        // /pic/ 뒤에 어떤 내용이 온다면 addResourceLocations("file:" + uploadPath) 를 붙여줌
    }
}
