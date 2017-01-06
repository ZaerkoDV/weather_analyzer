package com.instinctools.weatheranalyzer.service.impl;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.instinctools.weatheranalyzer.dao.DataScreenDao;
import com.instinctools.weatheranalyzer.model.DataScreen;
import com.instinctools.weatheranalyzer.service.DataScreenService;
import com.instinctools.weatheranalyzer.service.base.BaseService;

@Service
@Transactional
public class DataScreenServiceImpl extends BaseService implements DataScreenService {

    @Autowired
    private DataScreenDao dataScreenDao;

    //в зависимости от сайта вызывается соответствующий парсинг
    @Override
    public DataScreen createDataScreen(DataScreen dataScreen) {
        return null;
    }
}
