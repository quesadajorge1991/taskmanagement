package taskmanagement.taskmanagement.config.generic;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@Configuration
public class Config {


    @Bean
    MessageSource messageSource() {
	    ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
	    messageSource.setBasenames("classpath:messages"); // Ajusta esto según la ubicación de tus archivos.properties
	    messageSource.setDefaultEncoding("UTF-8");
	    return messageSource;
	}
    
    

}
