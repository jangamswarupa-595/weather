package com.example.weather;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class Region {

    private final String API_KEY = "7f8cd0e5f37e7c84ebda7806217724fe";  // Replace with your actual API key

    @Autowired
    private WeatherRepository weatherRepository;

    @GetMapping("/weather")
    public List<WeatherResponse> getWeather(@RequestParam String cities) {
        String[] cityArray = cities.split(",");
        List<WeatherResponse> allWeatherData = new ArrayList<>();

        for (String city : cityArray) {
            String url = String.format("http://api.openweathermap.org/data/2.5/weather?q=%s&appid=%s&units=metric", city.trim(), API_KEY);
            RestTemplate restTemplate = new RestTemplate();
            @SuppressWarnings("unchecked")
            Map<String, Object> response = restTemplate.getForObject(url, HashMap.class);

            // Log the API response for debugging
            System.out.println("API Response for " + city + ": " + response);

            if (response != null && response.containsKey("main")) {
                @SuppressWarnings("unchecked")
                Map<String, Object> mainData = (Map<String, Object>) response.get("main");
                @SuppressWarnings("unchecked")
                List<Map<String, Object>> weatherList = (List<Map<String, Object>>) response.get("weather");
                String description = weatherList.get(0).get("description").toString();
                double temperature = Double.parseDouble(mainData.get("temp").toString());

                // Save the weather data to the database
                WeatherData weatherData = new WeatherData(city.trim(), temperature, description);
                WeatherData savedData = weatherRepository.save(weatherData);

                // Log saved data
                System.out.println("Saved Weather Data for " + city + ": " + savedData);
                WeatherResponse weatherResponse = new WeatherResponse(savedData.getId(), savedData.getCity(), savedData.getTemperature(), savedData.getDescription());
                allWeatherData.add(weatherResponse);
            }
        }

        return allWeatherData; // Return only the id, city, temperature, and description fields
    }
}
