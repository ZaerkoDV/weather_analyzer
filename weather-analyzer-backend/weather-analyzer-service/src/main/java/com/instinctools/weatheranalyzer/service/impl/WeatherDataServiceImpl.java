package com.instinctools.weatheranalyzer.service.impl;

import javax.transaction.Transactional;
import org.springframework.stereotype.Service;
import com.instinctools.weatheranalyzer.service.WeatherDataService;
import com.instinctools.weatheranalyzer.service.base.BaseService;

@Service
@Transactional
public class WeatherDataServiceImpl extends BaseService implements WeatherDataService {

}
