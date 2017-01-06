package com.instinctools.weatheranalyzer.dao.impl;

import org.springframework.stereotype.Repository;
import com.instinctools.weatheranalyzer.dao.WeatherForecastDao;
import com.instinctools.weatheranalyzer.model.WeatherForecast;

@Repository
public class WeatherForecastDaoImpl extends BaseDaoImpl<WeatherForecast, Long> implements WeatherForecastDao {
    public WeatherForecastDaoImpl() {
        super(WeatherForecast.class);
    }
}
