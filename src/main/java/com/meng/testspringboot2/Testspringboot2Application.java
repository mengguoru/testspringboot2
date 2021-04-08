package com.meng.testspringboot2;

import com.meng.testspringboot2.config.ResponseWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.codec.ServerCodecConfigurer;
import org.springframework.web.reactive.accept.RequestedContentTypeResolver;

@SpringBootApplication
public class Testspringboot2Application {

    @Autowired
    private ServerCodecConfigurer serverCodecConfigurer;

    @Autowired
    private RequestedContentTypeResolver requestedContentTypeResolver;

    @Bean
    ResponseWrapper responseWrapper() {
        return new ResponseWrapper(serverCodecConfigurer
                                       .getWriters(), requestedContentTypeResolver);
    }

    public static void main(String[] args) {
        SpringApplication.run(Testspringboot2Application.class, args);
    }

}
