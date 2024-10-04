package com.example.weather; // Ensure this package matches with your other classes

public class WeatherResponse {
    private Long id;
    private String city;
    private double temperature;
    private String description;

    // Constructor
    public WeatherResponse(Long id, String city, double temperature, String description) {
        this.id = id;
        this.city = city;
        this.temperature = temperature;
        this.description = description;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
