package com.instinctools.weatheranalyzer.service.impl;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.instinctools.weatheranalyzer.service.WeatherForecastService;
import com.instinctools.weatheranalyzer.service.base.BaseService;

@Service
@Transactional
public class WeatherForecastServiceImpl extends BaseService implements WeatherForecastService {
}
