package com.instinctools.weatheranalyzer.screenparser.pogodablr;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

@Component
public class PogodablrParser {
    public List<Long> startParsing(String url) {//http://pogoda.blr.cc/belarus/soligorsk/7-dney/
        Document doc = null;
        List<Long> middleDayTemprageForWeek = new ArrayList<>();
        try {
            doc = Jsoup.connect(url).get();

        } catch (IOException e) {
            e.printStackTrace();
        }

        Elements elementsMax = doc.select("span.tmax");
        Elements elementsMin = doc.select("span.tmin");

        for (int i=0; i < elementsMax.size(); i++) {
            long d = correctorDigitFormat(elementsMax.get(i).text());
            long n = correctorDigitFormat(elementsMin.get(i).text());

            middleDayTemprageForWeek.add((d+n)/2);
        }

        return middleDayTemprageForWeek;
    }

    //8722 - minus  43- plus
    public long correctorDigitFormat(String inputValue) {
        if (inputValue.charAt(0) == '-') {
            return -1 * Long.parseLong(inputValue.substring(1, inputValue.length()-1));
        } else if (inputValue.charAt(0) == '+') {
            return Long.parseLong(inputValue.substring(1, inputValue.length()-1));
        } else {
            return Long.parseLong(inputValue.substring(0, inputValue.length()-1));
        }
    }
}
