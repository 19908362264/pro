package com.benwunet.mws.file.config;

import javax.servlet.http.HttpServletResponse;

import com.benwunet.mws.commons.constants.PermitAllUrl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;


/**
 * 资源服务配置
 * @author xiangkaihong
 * @date 2019/5/4 8:42
 */

@EnableResourceServer
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	/** url前缀*/
	@Value("${file.local.prefix}")
	public String localFilePrefix;

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().exceptionHandling()
				.authenticationEntryPoint(
						(request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
				.and().authorizeRequests()
				/**放开权限的url*/
				.antMatchers(PermitAllUrl.permitAllUrl("/files-anon/**", localFilePrefix + "/**")).permitAll()
				.anyRequest().authenticated().and().httpBasic();
	}
	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		//这里把自定义异常加进去
		resources.authenticationEntryPoint(new AuthExceptionEntryPoint())
				.accessDeniedHandler(new CustomAccessDeniedHandler());
	}

}
