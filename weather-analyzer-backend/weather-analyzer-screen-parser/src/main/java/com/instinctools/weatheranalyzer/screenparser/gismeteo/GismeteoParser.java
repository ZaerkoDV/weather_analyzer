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
        //Elements links = doc.getElementsByTag("data-airmax=");
        Elements elements = doc.select("div.weather_item.js_temp_graph");

        for(Element element : elements) {
            long d = minusCorrector(element.select("div.value.maxt.js_meas_container").text());
            long n = minusCorrector(element.select("div.value.mint.js_meas_container").text());
            middleDayTemprageForWeek.add((d+n)/2);
        }

        return middleDayTemprageForWeek;
    }

    public long minusCorrector(String inputValue) {
        System.out.println(inputValue.substring(1, inputValue.length()));

        Character e = inputValue.charAt(0);
        System.out.println(e);
        String s = e.toString();
        System.out.println(s.equals("?") +" "+ s.equals("-"));

       return Long.parseLong(inputValue);
    }
}
