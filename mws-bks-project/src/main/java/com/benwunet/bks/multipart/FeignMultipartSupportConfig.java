package com.benwunet.bks.multipart;

import feign.codec.Encoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Scope;

@Configuration
public class FeignMultipartSupportConfig {

  @Bean
  @Primary
  @Scope("prototype")
  public Encoder multipartFormEncoder() {
    return new FeignSpringFormEncoder();
  }

  @Bean
  public feign.Logger.Level multipartLoggerLevel() {
    return feign.Logger.Level.FULL;
  }

}
