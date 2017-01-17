package com.instinctools.weatheranalyzer.screenparser.tutby;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class TutByParser {//http://pogoda.tut.by/city/grodno/

    public List<Long> startParsing(String url) {
        Document doc = null;
        List<Long> middleDayTemprageForWeek = new ArrayList<>();
        try {
            doc = Jsoup.connect(url).get();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Element firstElenent = doc.select("span.temp-i").get(0);
        middleDayTemprageForWeek.add(
            minusCorrector(firstElenent.text())
        );

        Elements elements = doc.select("div.bs-temp");

        for (Element element : elements) {
            middleDayTemprageForWeek.add(
                minusCorrector(element.select("span.bs-max").text())
            );
        }

        return middleDayTemprageForWeek;
    }

    //8722 - minus  43- plus
    public long minusCorrector(String inputValue) {
        if (inputValue.charAt(0) == '-') {
            return -1 * Long.parseLong(inputValue.substring(1, inputValue.length()-1));
        } else if (inputValue.charAt(0) == '+') {
            return Long.parseLong(inputValue.substring(1, inputValue.length()-1));
        } else {
            return Long.parseLong(inputValue.substring(0, inputValue.length()-1));
        }
    }
}
