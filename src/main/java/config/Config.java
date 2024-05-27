package config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.view.ThymeleafViewResolver;

import nz.net.ultraq.thymeleaf.layoutdialect.LayoutDialect;



//@Configuration
public class Config implements WebMvcConfigurer{
	
	
	/*@Bean
	public SpringTemplateEngine templateEngine() {
		SpringTemplateEngine templateEngine=new SpringTemplateEngine();
		templateEngine.addDialect(new LayoutDialect());
		return templateEngine;
	}

	 @Bean
	    public ThymeleafViewResolver viewResolver() {
	        ThymeleafViewResolver viewResolver = new ThymeleafViewResolver();
	        viewResolver.setTemplateEngine(templateEngine());
	        return viewResolver;
	    }*/
}
