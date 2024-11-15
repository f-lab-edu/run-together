package com.srltas.runtogether.config;

import static com.srltas.runtogether.adapter.in.web.common.UrlConstants.*;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.srltas.runtogether.adapter.in.web.filter.AuthenticationFilter;
import com.srltas.runtogether.adapter.in.web.filter.RequestLogFilter;
import com.srltas.runtogether.adapter.out.session.SessionStorage;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class WebConfig {

	private final SessionStorage sessionStorage;

	@Bean
	public FilterRegistrationBean<RequestLogFilter> httpRequestLogFilter() {
		FilterRegistrationBean<RequestLogFilter> filterRegistrationBean = new FilterRegistrationBean<>();
		filterRegistrationBean.setFilter(new RequestLogFilter());
		filterRegistrationBean.addUrlPatterns(ALL_URL);
		filterRegistrationBean.setOrder(0);
		return filterRegistrationBean;
	}

	@Bean
	public FilterRegistrationBean<AuthenticationFilter> sessionFilterRegistration() {
		FilterRegistrationBean<AuthenticationFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new AuthenticationFilter(sessionStorage));
		registrationBean.setUrlPatterns(getAuthRequiredUrls());
		registrationBean.setOrder(1);
		return registrationBean;
	}
}
