package com.instinctools.weatheranalyzer.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.instinctools.weatheranalyzer.dao.WeatherDataDao;
import com.instinctools.weatheranalyzer.model.WeatherData;
import com.instinctools.weatheranalyzer.screenparser.gismeteo.GismeteoParser;
import com.instinctools.weatheranalyzer.screenparser.pogodablr.PogodablrParser;
import com.instinctools.weatheranalyzer.screenparser.tutby.TutByParser;
import com.instinctools.weatheranalyzer.service.WeatherDataService;
import com.instinctools.weatheranalyzer.service.base.BaseService;
import com.instinctools.weatheranalyzer.service.support.ValidationResult;

@Service
@Transactional
public class WeatherDataServiceImpl extends BaseService implements WeatherDataService {
    private static final Map<String, String> MAP_WEB_SITE_ADDRESES;
    private static final String ERROR_WEB_ADDRESS = "birthRequired";

    @Autowired
    public WeatherDataDao weatherDataDao;

    @Autowired
    public GismeteoParser gismeteoParser;

    @Autowired
    public TutByParser tutByParser;

    @Autowired
    public PogodablrParser pogodablrParser;

    static {
        MAP_WEB_SITE_ADDRESES = new HashMap<String, String>();

        MAP_WEB_SITE_ADDRESES.put("gismeteo.by", "https://www.gismeteo.by/weather-grodno-4243/weekly/");
        MAP_WEB_SITE_ADDRESES.put("tut.by", "http://pogoda.tut.by/");
        MAP_WEB_SITE_ADDRESES.put("pogoda.blr.cc", "http://pogoda.blr.cc/belarus/soligorsk/7-dney/");
    }

    @Override
    public ValidationResult<Boolean> createWeatherData(WeatherData inputWeatherData) {
       ValidationResult<Boolean> result = validate(inputWeatherData, new ValidationResult<Boolean>());

       if (result.isFaulted()) {
           return result;
       }

       if (inputWeatherData.getWeatherWebSite().getName().equals("gismeteo.by")) {
           return result.setResult(
               startGismeteoParsing(inputWeatherData)
           );
       }

       if (inputWeatherData.getWeatherWebSite().getName().equals("tut.by")) {
       }

       if (inputWeatherData.getWeatherWebSite().getName().equals("pogoda.blr.cc")) {
       }

       return result.setResult(Boolean.FALSE);
    }

    //middleDayTemprageForWeek.get(0) = today
    public Boolean startGismeteoParsing(WeatherData inputWeatherData) {
        List<Long> middleDayTemprageForWeek = gismeteoParser.startParsing(MAP_WEB_SITE_ADDRESES.get(
            inputWeatherData.getWeatherWebSite().getName()
        ));

        //save real temperature on next current day
        WeatherData prevoriusWeatherData =  weatherDataDao.findForecastTempratureOnDay(inputWeatherData);//1484309568324

        prevoriusWeatherData.setWebSiteRealTemperature(
            middleDayTemprageForWeek.get(0)
        ).setErrorTemperature(countDistance(
            prevoriusWeatherData.getWebSiteForecastTemperature(),
            middleDayTemprageForWeek.get(0)
        )).setErrorSign(countErrorSing(
            prevoriusWeatherData.getWebSiteForecastTemperature(),
            middleDayTemprageForWeek.get(0)
        ));
        weatherDataDao.save(prevoriusWeatherData);

        // save forecast temperature on next 6 day
        for (int i=1; i < middleDayTemprageForWeek.size(); i++) {
            weatherDataDao.save(new WeatherData()
                .setCreatedAtTimestamp(inputWeatherData.getCreatedAtTimestamp() + (i*(long)86400000))
                .setWebSiteForecastTemperature(middleDayTemprageForWeek.get(i))
                .setWeatherWebSite(inputWeatherData.getWeatherWebSite())
            );
            weatherDataDao.flush();
        }

        return Boolean.TRUE;
    }

    public Boolean startTuyByParsing(WeatherData inputWeatherData) {
        return Boolean.TRUE;
    }

    public Boolean pogodaBlrParsing(WeatherData inputWeatherData) {
        return Boolean.TRUE;
    }


    public Long countDistance(Long forecastTempratureCourrentDay, Long realTempratureCurrentDay) {
       return Math.abs(Math.abs(forecastTempratureCourrentDay)-Math.abs(realTempratureCurrentDay));
    }

    public Long countErrorSing(Long forecastTempratureCourrentDay, Long realTempratureCurrentDay) {
        if (realTempratureCurrentDay > forecastTempratureCourrentDay){
            return -1L;
        } else if (realTempratureCurrentDay < forecastTempratureCourrentDay) {
            return 1L;
        } else {
            return 0L;
        }
    }

    public <T> ValidationResult<T> validate(WeatherData inputWeatherData, ValidationResult<T> result) {
        Boolean webAddressExist = MAP_WEB_SITE_ADDRESES.containsKey(
            inputWeatherData.getWeatherWebSite().getName()
        );

        if (!webAddressExist) {
            result.addError(ERROR_WEB_ADDRESS);
        }

        return result;
    }
}
