package com.instinctools.weatheranalyzer.dao.impl;

import org.springframework.stereotype.Repository;
import com.instinctools.weatheranalyzer.dao.DataScreenDao;
import com.instinctools.weatheranalyzer.model.DataScreen;

@Repository
public class DataScreenDaoImpl extends BaseDaoImpl<DataScreen, Long> implements DataScreenDao {
    public DataScreenDaoImpl() {
        super(DataScreen.class);
    }
}
