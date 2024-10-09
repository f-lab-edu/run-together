package com.srltas.runtogether.config;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.srltas.runtogether.adapter.in.web.filter.AuthenticationFilter;
import com.srltas.runtogether.adapter.out.session.SessionStorage;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig {

	private final SessionStorage sessionStorage;

	@Bean
	public FilterRegistrationBean<AuthenticationFilter> sessionFilterRegistration() {
		FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new AuthenticationFilter(sessionStorage));
		registrationBean.addUrlPatterns("/neighborhood/verification");
		registrationBean.setOrder(1);
		return registrationBean;
	}
}
