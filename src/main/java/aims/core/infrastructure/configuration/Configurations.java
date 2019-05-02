package aims.core.infrastructure.configuration;

import java.nio.charset.Charset;

import javax.servlet.Filter;
import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;


/**
 * 이 설정은 spring-servlet.xml 설정을 대체하는 자바파일 이다.
 * 
 * @author sh
 */
@Configuration
public class Configurations {
    
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurerAdapter() {
            
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                registry.addRedirectViewController("/developers/v2/api-docs", "/v2/api-docs");
                registry.addRedirectViewController("/developers/configuration/ui", "/configuration/ui");
                registry.addRedirectViewController("/developers/configuration/security", "/configuration/security");
                registry.addRedirectViewController("/developers/swagger-resources", "/swagger-resources");
                registry.addRedirectViewController("/developers", "/developers/swagger-ui.html");
                registry.addRedirectViewController("/developers/", "/developers/swagger-ui.html");
            }
            
            /**
             * <resources mapping="/resources/**" location="/resources/">에 해당됨.
             */
            @Override
            public void addResourceHandlers(ResourceHandlerRegistry registry) {
                registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
                registry.addResourceHandler("/developers/**").addResourceLocations("classpath:/META-INF/resources/");
                registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");
            }
            
        };
    };
    
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        return new StringHttpMessageConverter(Charset.forName("UTF-8"));
    }
    
    @Bean
    public Filter characterEncodingFilter() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);
        
        return characterEncodingFilter;
    }
    
    @Bean
    public MultipartConfigElement multipartConfigElement() {
        MultipartConfigFactory factory = new MultipartConfigFactory();
        factory.setMaxFileSize("250Mb");
        factory.setMaxRequestSize("250Mb");
        
        return factory.createMultipartConfig();
    }
    
}