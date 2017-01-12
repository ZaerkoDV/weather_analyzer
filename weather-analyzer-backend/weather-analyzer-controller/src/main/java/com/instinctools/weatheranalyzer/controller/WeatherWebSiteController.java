package com.instinctools.weatheranalyzer.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.instinctools.weatheranalyzer.model.WeatherData;
import com.instinctools.weatheranalyzer.model.WeatherWebSite;
import com.instinctools.weatheranalyzer.service.WeatherDataService;
import com.instinctools.weatheranalyzer.service.WeatherWebSiteServiñe;

@Controller//WeatherWebSite
@RequestMapping(value="backend/weather")
public class WeatherWebSiteController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(WeatherWebSiteController.class);

    @Autowired
    private WeatherWebSiteServiñe weatherWebSiteServiñe;

    @Autowired
    private WeatherDataService weatherDataService;

    @RequestMapping(value = "/screenParsing/start", method = RequestMethod.POST)
    public ResponseEntity<?> actionPostScreenParsingStart(@RequestParam("site") String site) {
        WeatherWebSite weatherWebSite = weatherWebSiteServiñe.getByName(site);

        return buildValidationResult(weatherDataService.createWeatherData(new WeatherData()
            .setCreatedAtTimestamp(getCurrentTimestamp())
            .setWeatherWebSite(weatherWebSite)
        ), weatherDataTemp -> toMap(
            "id", weatherDataTemp.getId(),
            "site", weatherDataTemp.getWeatherWebSite().getName()
        ));
    }

}
