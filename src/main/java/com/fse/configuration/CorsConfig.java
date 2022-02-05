//package com.fse.configuration;
//
//import java.util.stream.Collectors;
//import java.util.stream.Stream;
//
//import javax.servlet.Filter;
//
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.servlet.FilterRegistrationBean;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.Ordered;
//import org.springframework.web.cors.CorsConfiguration;
//import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
//import org.springframework.web.filter.CorsFilter;
//
//@Configuration
//public class CorsConfig {
//
//	@Value("${allowed.cors}")
//	private String allowedCors;
//
//	@Bean
//	public FilterRegistrationBean<Filter> customCorsFilter() {
//		CorsConfiguration corsConfiguration = new CorsConfiguration();
//		corsConfiguration.setAllowCredentials(true);
//		corsConfiguration.setAllowedOrigins(*);
//
//		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		source.registerCorsConfiguration("/**", corsConfiguration);
//
//		FilterRegistrationBean<Filter> bean = new FilterRegistrationBean<>(new CorsFilter(source));
//		bean.setName("customCors");
//		bean.setOrder(Ordered.HIGHEST_PRECEDENCE);
//		return bean;
//	}
//
//}
