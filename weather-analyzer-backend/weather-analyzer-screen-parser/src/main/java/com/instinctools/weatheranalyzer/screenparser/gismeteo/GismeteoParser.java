package com.instinctools.weatheranalyzer.screenparser.gismeteo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;
import org.springframework.stereotype.Component;

@Component
public class GismeteoParser {
    public List<Long> startParsing(String url) {//https://www.gismeteo.by/weather-grodno-4243/10-days/
        Document doc = null;
        List<Long> middleDayTemprageForWeek = new ArrayList<>();
        try {
            doc = Jsoup.connect(url).get();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements elements = doc.select("div.weather_item.js_temp_graph");

        for (Element element : elements) {
            long d = correctorDigitFormat(element.select("div.value.maxt.js_meas_container").text());
            long n = correctorDigitFormat(element.select("div.value.mint.js_meas_container").text());

            middleDayTemprageForWeek.add((d+n)/2);
        }

        return middleDayTemprageForWeek;
    }

    //8722 - minus  43- plus
    public long correctorDigitFormat(String inputValue) {
        if (inputValue.charAt(0) == 8722) {
            return -1 * Long.parseLong(inputValue.substring(1, inputValue.length()));
        } else if (inputValue.charAt(0) == 43) {
            return Long.parseLong(inputValue);
        } else {
            return 0;
        }
    }
}