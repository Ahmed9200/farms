package com.example.authentication;

import com.example.authentication.services.ConfigService;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.messaging.FirebaseMessaging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

@EnableScheduling
@SpringBootApplication
public class DamhaApplication implements CommandLineRunner {

    public static HashMap<String,String> CONSTANTS_MAP;
    @Autowired
    ConfigService configService;

    public static void main(String[] args) {
        SpringApplication.run(DamhaApplication.class, args);
    }


    @Bean
    FirebaseMessaging firebaseMessaging() throws IOException {
        GoogleCredentials googleCredentials = GoogleCredentials
                .fromStream(new ClassPathResource("joaanme-cbd9b-firebase-adminsdk-eiekq-b972f2c187.json").getInputStream());
        FirebaseOptions firebaseOptions = FirebaseOptions
                .builder()
                .setCredentials(googleCredentials)
                .build();
        FirebaseApp app = FirebaseApp.initializeApp(firebaseOptions, "joaanme");
        return FirebaseMessaging.getInstance(app);
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

//                registry.addMapping("/getToken").allowedOrigins("*");
//                registry.addMapping("/isTokenValid/{token}").allowedOrigins("*");

                registry.addMapping("/**").allowedMethods("GET", "POST", "PUT", "DELETE").allowedOrigins("*").allowedHeaders("*");
                registry.addMapping("/**").allowedOrigins("*");
                registry.addMapping("/**");
            }
        };
    }

    @Override
    public void run(String... args) throws Exception {
        CONSTANTS_MAP = configService.loadConfigurations();
    }
}
