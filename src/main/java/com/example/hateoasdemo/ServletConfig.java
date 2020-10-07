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
@ImportAutoConfiguration(ServletWebServerFactoryAutoConfiguration.class)
public class ServletConfig implements WebMvcConfigurer {

    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> {
            AnnotationConfigWebApplicationContext restCtx = new AnnotationConfigWebApplicationContext();
            restCtx.register(RestConfig.class);
            ServletRegistration.Dynamic restServletReg = servletContext.addServlet("rest", new DispatcherServlet(restCtx));
            restServletReg.setLoadOnStartup(1);
            restServletReg.addMapping("/rest/v1/*");
        };
    }

}
