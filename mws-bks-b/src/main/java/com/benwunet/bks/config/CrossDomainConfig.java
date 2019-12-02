package com.benwunet.bks.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * 跨域配置
 * 页面访问域名和后端接口地址的域名不一致时，会先发起一个OPTIONS的试探请求<br>
 * 如果不设置跨域的话，js将无法正确访问接口，域名一致的话，不存在这个问题
 * @author xiangkaihong
 * @date 2019/5/4 10:18
 */
@Configuration
public class CrossDomainConfig {

    /**
     * 跨域支持
     * @author xiangkaihong
     * @date 2019/5/4 10:19
     * @return
     */
    @Bean
    public CorsFilter corsFilter() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        final CorsConfiguration config = new CorsConfiguration();
        /**允许cookies跨域*/
        config.setAllowCredentials(true);
        /**允许向该服务器提交请求的URI，*表示全部允许*/
        config.addAllowedOrigin("*");
        /**允许访问的头信息,*表示全部*/
        config.addAllowedHeader("*");
        /**预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了*/
        config.setMaxAge(18000L);
        /**允许提交请求的方法，*表示全部允许*/
        config.addAllowedMethod("*");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

//    /**两种方式任选其一即可*/
//    @Bean
//    public WebMvcConfigurer corsConfigurer() {
//        return new WebMvcConfigurer() {
//            @Override
//            public void addCorsMappings(CorsRegistry registry) {
//                /**拦截所有权请求*/
//                registry.addMapping("/**")
//                        /**允许提交请求的方法，*表示全部允许*/
//                        .allowedMethods("*")
//                        /**允许向该服务器提交请求的URI，*表示全部允许**/
//                        .allowedOrigins("*")
//                        /**允许cookies跨域*/
//                        .allowCredentials(true)
//                        /**允许访问的头信息,*表示全部*/
//                        .allowedHeaders("*")
//                        /**预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检*/
//                        .maxAge(18000L);
//            }
//        };
//    }

}
