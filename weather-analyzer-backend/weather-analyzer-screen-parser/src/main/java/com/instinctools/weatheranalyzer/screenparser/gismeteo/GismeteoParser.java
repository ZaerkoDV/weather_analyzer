package com.instinctools.weatheranalyzer.screenparser.gismeteo;

import java.io.IOException;
import org.jsoup.*;
import org.jsoup.nodes.*;
import org.jsoup.select.*;

public class GismeteoParser {

	public void startParsing() {
		Document doc= null;
		try {
			doc = Jsoup.connect("https://www.gismeteo.by/weather-grodno-4243/10-days/").get();
		} catch (IOException e) {
			e.printStackTrace();
		}

		Elements links = doc.getElementsByTag("data-airmax=");
		Elements elements = doc.select("div.weather_item.js_temp_graph");

		for(Element element : elements) {
			System.out.println("max= "+element.select("div.value.maxt.js_meas_container").text());
			System.out.println("min= "+element.select("div.value.mint.js_meas_container").text());
		}
	}
}
