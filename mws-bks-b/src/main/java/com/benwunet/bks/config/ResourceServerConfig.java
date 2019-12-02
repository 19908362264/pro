package com.benwunet.bks.config;

import com.benwunet.mws.commons.constants.PermitAllUrl;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

import javax.servlet.http.HttpServletResponse;

/**
 * 资源服务配置
 * @author xiangkaihong
 * @data 2019-04-28 13:29
 */

@EnableResourceServer
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

	@Override
	public void configure(HttpSecurity http) throws Exception {
		http.csrf().disable().exceptionHandling()
				.authenticationEntryPoint(
						(request, response, authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
				.and().authorizeRequests()
				/** 放开权限的url */
				.antMatchers(PermitAllUrl.permitAllUrl("/bks-b-anon/**", "/wechat/**","/users/*")).permitAll()
				.anyRequest().authenticated().and().httpBasic();
	}
    /** 密码加密 */
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}

	@Override
	public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
		//这里把自定义异常加进去
		resources.authenticationEntryPoint(new AuthExceptionEntryPoint())
				.accessDeniedHandler(new CustomAccessDeniedHandler());
	}
}
