package com.instinctools.weatheranalyzer.service;

import com.instinctools.weatheranalyzer.model.WeatherData;
import com.instinctools.weatheranalyzer.service.support.ValidationResult;

public interface WeatherDataService {
    public ValidationResult<Boolean> createWeatherData(WeatherData weatherData);
}
