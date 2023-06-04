package com.example.farms;


import com.example.farms.models.enums.Constants;
import com.example.farms.services.ConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import java.util.List;

@EnableScheduling
@SpringBootApplication
public class DinaFarmsApplication implements CommandLineRunner {

    @Autowired
    ConfigService service;

    public static void main(String[] args) {
        SpringApplication.run(DinaFarmsApplication.class, args);
    }

    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }

    @Bean
    public RestTemplate restTemplate(List<HttpMessageConverter<?>> messageConverters) {
        return new RestTemplate(messageConverters);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("*").allowedHeaders("*");
                registry.addMapping("/**").allowedOrigins("*");
                registry.addMapping("/**");
            }
        };
    }

    @Override
    public void run(String... args) throws Exception {
        Constants.DELIVERY = Integer.parseInt(service.getValueByName("delivery"));
        Constants.VAT = Integer.parseInt(service.getValueByName("vat"));
    }
}
