package com.example.weather;
import org.springframework.boot.SpringApplication;  // Import SpringApplication class
import org.springframework.boot.autoconfigure.SpringBootApplication;  // Import SpringBootApplication annotation

@SpringBootApplication  // Annotation to mark this class as a Spring Boot application
public class WeatherApp {  // Main class definition
    public static void main(String[] args) {  // Main method
        SpringApplication.run(WeatherApp.class, args);  // Run the Spring Boot application
    }
}