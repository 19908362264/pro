package com.benwunet.mws.gateway.config;

import io.swagger.models.Swagger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger.web.SwaggerResource;
import springfox.documentation.swagger.web.SwaggerResourcesProvider;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * Swagger2 Zuul 配置
 * zuul将微服务的多个swagger api文档聚合成一个文档
 * @author xiangkaihong
 * @date 2019/7/19 17:15
 */

@ConditionalOnClass(value = {Swagger.class})
@Configuration
@EnableSwagger2
@Primary
public class SwaggerConfig implements SwaggerResourcesProvider {

	/**是否开启swagger，正式环境一般是需要关闭的，可根据springboot的多环境配置进行设置*/
	@Value(value = "${swagger.enabled}")
	Boolean swaggerEnabled;
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.apiInfo(apiInfo())
				/**是否开启*/
				.enable(swaggerEnabled).select()
				/**扫描的路径包*/
				.apis(RequestHandlerSelectors.basePackage("com.benwunet.mws.gateway.controller"))
				/**指定路径处理PathSelectors.any()代表所有的路径*/
				.paths(PathSelectors.any())
				.build()
				.globalOperationParameters(parameters());
	}

	/**设置api信息*/
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				.title("网关中心API接口文档")
				.description("网关中心API接口文档")
				.contact(new Contact("重庆本无网络科技有限公司", "http://www.benwunet.com", "service@benwunet.com"))
				.version("1.0.0")
				.build();
	}

	private List<Parameter> parameters() {
		ParameterBuilder parameterBuilder = new ParameterBuilder();
		List<Parameter> parameters = new ArrayList<>();
		parameterBuilder.name("Authorization")
				.description("Authorization")
				.modelRef(new ModelRef("string"))
				.parameterType("header")
				.required(false).build();
		parameters.add(parameterBuilder.build());
		return parameters;
	}

	/**配置分组微服务名*/
	@Override
	public List<SwaggerResource> get() {
		List resources = new ArrayList<>();
		resources.add(swaggerResource("网关中心", "/v2/api-docs", "1.0.0"));
		resources.add(swaggerResource("用户中心", "/api-u/v2/api-docs", "1.0.0"));
//		resources.add(swaggerResource("认证中心", "/api-o/v2/api-docs", "1.0.0"));
//		resources.add(swaggerResource("文件中心", "/api-f/v2/api-docs", "1.0.0"));
//		resources.add(swaggerResource("日志中心", "/api-l/v2/api-docs", "1.0.0"));
		resources.add(swaggerResource("通知中心", "/api-n/v2/api-docs", "1.0.0"));
		resources.add(swaggerResource("备考生", "/api-bks/v2/api-docs", "1.0.0"));
		resources.add(swaggerResource("备考生b端", "/api-bks-b/v2/api-docs", "1.0.0"));
		resources.add(swaggerResource("备考生后台", "/api-backend/v2/api-docs", "1.0.0"));
		return resources;
	}

	private SwaggerResource swaggerResource(String name, String location, String version) {
		SwaggerResource swaggerResource = new SwaggerResource();
		swaggerResource.setName(name);
		swaggerResource.setLocation(location);
		swaggerResource.setSwaggerVersion(version);
		return swaggerResource;
	}

}