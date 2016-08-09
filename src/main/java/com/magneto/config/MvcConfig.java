package com.magneto.config;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

@Configuration
@EnableAutoConfiguration

public class MvcConfig extends WebMvcConfigurerAdapter {

	@Override

	public void addViewControllers(ViewControllerRegistry registry) {

		registry.addViewController("/home").setViewName("home");
		registry.addViewController("/guest/*").setViewName("home");

		registry.addViewController("/").setViewName("home");

	}

	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
	
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
		
	}
	@Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		
        configurer.enable();
    }
	@Bean

	public InternalResourceViewResolver viewResolver() {

		InternalResourceViewResolver resolver = new InternalResourceViewResolver();

		resolver.setPrefix("/WEB-INF/jsp/");

		resolver.setSuffix(".jsp");

		return resolver;

	}

}