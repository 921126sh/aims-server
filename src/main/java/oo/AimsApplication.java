package oo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EntityScan("oo.*.domain")
@ComponentScan("oo.**")
public class AimsApplication extends SpringBootServletInitializer {
    
    @Override
	protected SpringApplicationBuilder configure (SpringApplicationBuilder application) {
		return application.sources(AimsApplication.class);
	}

    public static void main(String[] args) {
    	System.setProperty("spring.devtools.restart.enabled", "false");
    	SpringApplication.run(AimsApplication.class, args);
    }
}