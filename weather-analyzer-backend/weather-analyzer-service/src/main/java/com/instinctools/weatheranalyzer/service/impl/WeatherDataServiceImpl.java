package com.instinctools.weatheranalyzer.service.impl;

import java.util.Collection;
import java.util.Collections;
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
    public ValidationResult<WeatherData> createWeatherData(WeatherData inputWeatherData) {
       ValidationResult<WeatherData> result = validate(inputWeatherData, new ValidationResult<WeatherData>());

       if (result.isFaulted()) {
           return result;
       }

       List<Long> middleDayTemprageForWeek;

       if (inputWeatherData.getWeatherWebSite().getName().equals("gismeteo.by")) {
           middleDayTemprageForWeek = gismeteoParser.startParsing(MAP_WEB_SITE_ADDRESES.get(
               inputWeatherData.getWeatherWebSite().getName()
           ));
       } else {
           middleDayTemprageForWeek = Collections.EMPTY_LIST;
       }

//       if (inputWeatherData.getWeatherWebSite().getName() == "tut.by") {
//           tutByParser
//       }
//
//       if (inputWeatherData.getWeatherWebSite().getName() == "pogoda.blr.cc") {
//          pogodablrParser
//       }
       return result.setResult(null);//weatherDataDao.save(inputWeatherData));
    }





    public <T> ValidationResult<T> validate(WeatherData inputWeatherData, ValidationResult<T> result) {
        Boolean webAddressExist = MAP_WEB_SITE_ADDRESES.containsKey(inputWeatherData.getWeatherWebSite().getName());

        if (!webAddressExist) {
            result.addError(ERROR_WEB_ADDRESS);
        }

        return result;
    }
}
