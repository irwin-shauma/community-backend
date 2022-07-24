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
		matchers.add(new AntPathRequestMatcher("/swagger-ui.html", HttpMethod.GET.name()));
		matchers.add(new AntPathRequestMatcher("/students", HttpMethod.POST.name()));
		
		
		matchers.add(new AntPathRequestMatcher("/users/**", HttpMethod.POST.name()));
		matchers.add(new AntPathRequestMatcher("/users/**", HttpMethod.GET.name()));
		
		matchers.add(new AntPathRequestMatcher("/roles/**", HttpMethod.GET.name()));
		matchers.add(new AntPathRequestMatcher("/roles/**", HttpMethod.POST.name()));
		
		return matchers;
	}
}
