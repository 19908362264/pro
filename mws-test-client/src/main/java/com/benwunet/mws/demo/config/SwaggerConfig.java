package com.benwunet.mws.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * swagger文档
 * @author xiangkaihong
 * @data 2019-04-28 14:29
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {

	@Bean
	public Docket docket() {
		return new Docket(DocumentationType.SWAGGER_2).groupName("用户中心swagger接口文档")
				.apiInfo(new ApiInfoBuilder().title("用户中心swagger接口文档")
						.contact(new Contact("benwunet", "mws.benwunet.com", "services@benwunet.com")).version("1.0.0").build())
				.select().paths(PathSelectors.any()).build();
	}
}
