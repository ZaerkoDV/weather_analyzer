package com.instinctools.weatheranalyzer.service.impl;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.instinctools.weatheranalyzer.dao.WeatherWebSiteDao;
import com.instinctools.weatheranalyzer.model.WeatherWebSite;
import com.instinctools.weatheranalyzer.service.WeatherWebSiteServi�e;
import com.instinctools.weatheranalyzer.service.base.BaseService;

@Service
@Transactional
public class WeatherWebSiteServi�eImpl extends BaseService implements WeatherWebSiteServi�e {

    @Autowired
    private WeatherWebSiteDao weatherWebSiteDao;

    @Override
    public WeatherWebSite getByName(String webSiteName) {
        return ensureFound(weatherWebSiteDao.getWeatherWebSiteByName(webSiteName));
    }
}
