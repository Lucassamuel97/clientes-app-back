package com.github.Lucassamuel97.clientes.config;

import java.util.Arrays;
import java.util.List;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class WebConfig {
	
	@Bean
	public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean(){
		List<String> all = Arrays.asList("*");
		
		CorsConfiguration corsconfiguration = new CorsConfiguration();
		corsconfiguration.addAllowedOrigin("*");
		corsconfiguration.addAllowedOriginPattern("*");
		
		corsconfiguration.setAllowedOrigins(all);
		corsconfiguration.setAllowedHeaders(all);
		corsconfiguration.setAllowedMethods(all);
		corsconfiguration.setAllowCredentials(false);
		
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", corsconfiguration);
		CorsFilter corsfilter = new CorsFilter(source);
		
		FilterRegistrationBean<CorsFilter> filter = new FilterRegistrationBean<>(corsfilter);
		filter.setOrder(Ordered.HIGHEST_PRECEDENCE);
		
		return filter;
	}
}
