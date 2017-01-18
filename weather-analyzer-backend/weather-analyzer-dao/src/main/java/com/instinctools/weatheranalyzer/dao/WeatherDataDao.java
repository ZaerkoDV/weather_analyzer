package com.instinctools.weatheranalyzer.dao;

import com.instinctools.weatheranalyzer.model.WeatherData;

public interface WeatherDataDao extends BaseDao<WeatherData, Long> {
    public WeatherData findForecastTempratureOnDay(long time, String name);
}
