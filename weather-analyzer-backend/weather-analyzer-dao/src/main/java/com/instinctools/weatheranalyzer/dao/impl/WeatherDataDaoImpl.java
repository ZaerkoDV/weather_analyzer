package com.instinctools.weatheranalyzer.dao.impl;

import org.springframework.stereotype.Repository;
import com.instinctools.weatheranalyzer.dao.WeatherDataDao;
import com.instinctools.weatheranalyzer.model.WeatherData;

@Repository
public class WeatherDataDaoImpl extends BaseDaoImpl<WeatherData, Long> implements WeatherDataDao {
    public WeatherDataDaoImpl() {
        super(WeatherData.class);
    }

    @Override
    public WeatherData findForecastTempratureOnDay(WeatherData weatherData) {
      return null;
    }
}
