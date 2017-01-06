package com.instinctools.weatheranalyzer.controller;

import java.io.IOException;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.instinctools.weatheranalyzer.model.DataScreen;
import com.instinctools.weatheranalyzer.model.WeatherData;
import com.instinctools.weatheranalyzer.model.WeatherWebSite;
import com.instinctools.weatheranalyzer.service.DataScreenService;
import com.instinctools.weatheranalyzer.service.WeatherWebSiteServiñe;

@Controller//WeatherWebSite
@RequestMapping(value="backend/weather")
public class WeatherWebSiteController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(WeatherWebSiteController.class);

    @Autowired
    private WeatherWebSiteServiñe weatherWebSiteServiñe;

    @Autowired
    private DataScreenService dataScreenService;

    @RequestMapping(value = "/screenParsing/start", method = RequestMethod.POST)
    public ResponseEntity<?> actionPostUserPhotoCreate(@RequestParam("site") String webSiteName, @RequestParam("file") MultipartFile file) {
        byte[] imageFile;

        try {
            if (file.isEmpty()) {
                return buildError(ERROR_UPLOAD);
            }

            imageFile = file.getBytes();

        } catch (IOException e) {
            return buildError(ERROR_UPLOAD);
        }

        WeatherWebSite weatherWebSite = weatherWebSiteServiñe.getByName(webSiteName);

        DataScreen dataScreen = dataScreenService.createDataScreen(
            new DataScreen()
            .setCreatedAtTimestamp(getCurrentTimestamp())
            .setScreenData(imageFile)
            .setWeatherData(new WeatherData()
                .setCreatedAtTimestamp(getCurrentTimestamp())
                .setWeatherWebSite(weatherWebSite)
            )
        );

        return buildOk(toMap(
            "id", dataScreen.getId(),
            "site", dataScreen.getWeatherData().getWeatherWebSite().getName()
        ));
    }

}
