package com.instinctools.weatheranalyzer;

import java.util.Locale;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.converter.BufferedImageHttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.i18n.FixedLocaleResolver;

@SpringBootApplication
@ComponentScan("com.instinctools.weatheranalyzer")
@EntityScan("com.instinctools.weatheranalyzer")
public class WeatherAnalyzerApplication extends SpringBootServletInitializer {
    public static void main(String[] args) {
        SpringApplication.run(WeatherAnalyzerApplication.class, args);
    }

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WeatherAnalyzerApplication.class);
    }

    @Bean
    public LocaleResolver getLocaleResolver() {
        return new FixedLocaleResolver(new Locale("en", "US"));
    }

    @Bean
    public BufferedImageHttpMessageConverter getBufferedImageHttpMessageConverter() {
        return new BufferedImageHttpMessageConverter();
    }

    @Bean
    public WebMvcConfigurer getWebMvcConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")
                    .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                    .allowedHeaders("Content-Type", "Content-Range", "Content-Disposition", "Content-Description");
            }
        };
    }
}