package com.example.hateoasdemo;

import com.example.hateoasdemo.rest.RestConfig;
import org.springframework.boot.autoconfigure.ImportAutoConfiguration;
import org.springframework.boot.autoconfigure.web.servlet.ServletWebServerFactoryAutoConfiguration;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.servlet.ServletRegistration;

@Configuration
@EnableWebMvc
@ImportAutoConfiguration({
        ServletWebServerFactoryAutoConfiguration.class,
})
public class ServletConfig implements WebMvcConfigurer {

    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> {
            // REST v1 servlet
            AnnotationConfigWebApplicationContext restContextV2 = new AnnotationConfigWebApplicationContext();
            restContextV2.register(RestConfig.class);
            ServletRegistration.Dynamic restServletV2 = servletContext.addServlet("rest", new DispatcherServlet(restContextV2));
            restServletV2.setLoadOnStartup(1);
            restServletV2.addMapping("/rest/v1/*");
        };
    }

}
