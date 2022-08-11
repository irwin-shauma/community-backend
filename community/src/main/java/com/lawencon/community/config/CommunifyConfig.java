package com.lawencon.community.config;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.lawencon.util.SchedulerUtil;

@Configuration
public class CommunifyConfig {
	
	@Autowired
	private SchedulerUtil schedulerUtil;
	
	@Bean
	@Qualifier("premiumChecking")
	public Boolean premiumCheck() {
		Boolean result = true;
		
		schedulerUtil.startSchedulerOnce( () -> {
			System.out.println("You are running " + result);
		}, 3, TimeUnit.SECONDS);
		
		return result;
	}

}
