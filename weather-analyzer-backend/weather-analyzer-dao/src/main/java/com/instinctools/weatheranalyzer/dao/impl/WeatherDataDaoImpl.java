package com.instinctools.weatheranalyzer.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.instinctools.weatheranalyzer.dao.WeatherDataDao;
import com.instinctools.weatheranalyzer.model.WeatherData;

@Repository
public class WeatherDataDaoImpl extends BaseDaoImpl<WeatherData, Long> implements WeatherDataDao {
    public WeatherDataDaoImpl() {
        super(WeatherData.class);
    }

    //previous day in miliseconds 86400000
    @Override
    public WeatherData findForecastTempratureOnDay(WeatherData inputWeatherData) {
        long previousDay = inputWeatherData.getCreatedAtTimestamp() - 86400000;
        long currentDay = inputWeatherData.getCreatedAtTimestamp();

        Criteria criteria = createCriteria()
        .createAlias("weatherWebSite", "wws")
        .add(Restrictions.eq("wws.name", inputWeatherData.getWeatherWebSite().getName()))
        .add(Restrictions.ge("createdAtTimestamp", previousDay))
        .add(Restrictions.lt("createdAtTimestamp", currentDay));

        return (WeatherData) criteria.uniqueResult();
    }
}
