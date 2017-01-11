package com.instinctools.weatheranalyzer.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
import com.instinctools.weatheranalyzer.dao.WeatherWebSiteDao;
import com.instinctools.weatheranalyzer.model.WeatherWebSite;

@Repository
public class WeatherWebSiteDaoImpl extends BaseDaoImpl<WeatherWebSite, Long> implements WeatherWebSiteDao {
    public WeatherWebSiteDaoImpl() {
        super(WeatherWebSite.class);
    }

    @Override
    public WeatherWebSite getWeatherWebSiteByName(String webSiteName) {
        Criteria criteria = createCriteria()
        .add(Restrictions.eq("name", webSiteName))
        .setMaxResults(1);

        return (WeatherWebSite) criteria.uniqueResult();
    }

//    @Override
//    public List<Link> getListLinkByIdUser(Long idUser) {
//        Criteria criteria = createCriteria()
//        .createAlias("user", "u")
//        .add(Restrictions.eq("u.id", idUser))
//        .setMaxResults(20)
//        .setFirstResult(0);
//
//        return criteria.list();
//    }
}
