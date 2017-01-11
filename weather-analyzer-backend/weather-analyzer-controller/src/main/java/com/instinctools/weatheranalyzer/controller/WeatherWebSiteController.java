package com.instinctools.weatheranalyzer.controller;

import java.io.IOException;
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
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

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

        WeatherData weatherData = weatherDataService.createWeatherData(
            new WeatherData()
                .setCreatedAtTimestamp(getCurrentTimestamp())
                .setWeatherWebSite(weatherWebSite)
        );

        return buildOk(toMap(
            "id", weatherData.getId(),
            "site", weatherData.getWeatherWebSite().getName()
        ));

//        Document doc= null;
//        try {
//            doc = Jsoup.connect("https://www.gismeteo.by/weather-grodno-4243/10-days/").get();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//
//        Elements links = doc.getElementsByTag("data-airmax=");
//        Elements elements = doc.select("div.weather_item.js_temp_graph");
//
//        for(Element element : elements) {
//            System.out.println("max= "+element.select("div.value.maxt.js_meas_container").text());
//            System.out.println("min= "+element.select("div.value.mint.js_meas_container").text());
//        }
//
//        return null;
    }

}
