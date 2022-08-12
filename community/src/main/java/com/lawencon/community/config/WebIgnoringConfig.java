package com.lawencon.community.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;

@Configuration
public class WebIgnoringConfig {

	private List<RequestMatcher> matchers = new ArrayList<>();

	@Bean
	@Qualifier("webIgnoring")
	public List<RequestMatcher> antMatchers() {
		matchers.add(new AntPathRequestMatcher("/users", HttpMethod.POST.name()));
		matchers.add(new AntPathRequestMatcher("/users/**", HttpMethod.GET.name()));
		matchers.add(new AntPathRequestMatcher("/verifications/**", HttpMethod.POST.name()));
//		matchers.add(new AntPathRequestMatcher("/verifications/**", HttpMethod.GET.name()));
		
		matchers.add(new AntPathRequestMatcher("/login/**", HttpMethod.POST.name()));
		matchers.add(new AntPathRequestMatcher("/files/**", HttpMethod.GET.name()));
		matchers.add(new AntPathRequestMatcher("/reports/**", HttpMethod.GET.name()));
		
		
		
		matchers.add(new AntPathRequestMatcher("/thread-headers/non-login", HttpMethod.GET.name()));
		matchers.add(new AntPathRequestMatcher("/thread-header-pollings/", HttpMethod.GET.name()));
		matchers.add(new AntPathRequestMatcher("/event-headers/**", HttpMethod.GET.name()));
		matchers.add(new AntPathRequestMatcher("/article-headers/", HttpMethod.GET.name()));
		matchers.add(new AntPathRequestMatcher("/premium-types/", HttpMethod.GET.name()));
		
		
		matchers.add(new AntPathRequestMatcher("/counts/**", HttpMethod.GET.name()));
		
		matchers.add(new AntPathRequestMatcher("/users/refresh", HttpMethod.POST.name()));
		return matchers;
	}
}
