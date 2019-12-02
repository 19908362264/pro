package com.benwunet.mws.notification.config;

import javax.servlet.http.HttpServletResponse;

import com.benwunet.mws.commons.constants.PermitAllUrl;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;


/**
 * 资源服务配置
 * @author xiangkaihong
 * @date 2019/5/12 10:20
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
				/**放开权限的url*/
				.and().authorizeRequests().antMatchers(PermitAllUrl.permitAllUrl("/notification-anon/**")).permitAll()
				.anyRequest().authenticated().and().httpBasic();
	}

}
