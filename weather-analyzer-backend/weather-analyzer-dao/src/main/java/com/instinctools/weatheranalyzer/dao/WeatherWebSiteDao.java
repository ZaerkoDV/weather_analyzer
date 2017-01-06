package com.instinctools.weatheranalyzer.dao;

import com.instinctools.weatheranalyzer.model.WeatherWebSite;

public interface WeatherWebSiteDao extends BaseDao<WeatherWebSite, Long> {

    public WeatherWebSite getWeatherWebSiteByName(String webSiteName);
}
