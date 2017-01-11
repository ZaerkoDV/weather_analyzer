package com.instinctools.weatheranalyzer.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instinctools.weatheranalyzer.dao.WeatherDataDao;
import com.instinctools.weatheranalyzer.model.WeatherData;
import com.instinctools.weatheranalyzer.service.WeatherDataService;
import com.instinctools.weatheranalyzer.service.base.BaseService;

@Service
@Transactional
public class WeatherDataServiceImpl extends BaseService implements WeatherDataService {

    @Autowired
    public WeatherDataDao weatherDataDao;

    public WeatherData createWeatherData(WeatherData weatherData) {
        return weatherDataDao.save(weatherData);
    }
}
