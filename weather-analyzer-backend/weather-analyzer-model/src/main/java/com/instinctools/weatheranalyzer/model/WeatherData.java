package com.instinctools.weatheranalyzer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

@Entity
public class WeatherData extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_genn")
    @SequenceGenerator(name = "seq_genn", sequenceName = "weather_data_id_seq", initialValue=1, allocationSize=1)
    @Column(name="id", columnDefinition="integer", nullable = false)
    private Long id;

    @Column
    private Long createdAtTimestamp;

    @Column
    private Long webSiteForecastTemperature;

    @Column
    private Long webSiteRealTemperature;

    @Column
    private Long errorTemperature;

    @Column
    private Long errorSign;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private WeatherWebSite weatherWebSite;

    @Override
    public Long getId() {
        return id;
    }

    public WeatherData setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCreatedAtTimestamp() {
        return createdAtTimestamp;
    }

    public WeatherData setCreatedAtTimestamp(Long createdAtTimestamp) {
        this.createdAtTimestamp = createdAtTimestamp;
        return this;
    }

    public Long getWebSiteForecastTemperature() {
        return webSiteForecastTemperature;
    }

    public WeatherData setWebSiteForecastTemperature(Long webSiteForecastTemperature) {
        this.webSiteForecastTemperature = webSiteForecastTemperature;
        return this;
    }

    public Long getWebSiteRealTemperature() {
        return webSiteRealTemperature;
    }

    public WeatherData setWebSiteRealTemperature(Long webSiteRealTemperature) {
        this.webSiteRealTemperature = webSiteRealTemperature;
        return this;
    }

    public Long getErrorTemperature() {
        return errorTemperature;
    }

    public WeatherData setErrorTemperature(Long errorTemperature) {
        this.errorTemperature = errorTemperature;
        return this;
    }

    public Long getErrorSign() {
        return errorSign;
    }

    public WeatherData setErrorSign(Long errorSign) {
        this.errorSign = errorSign;
        return this;
    }

    public WeatherWebSite getWeatherWebSite() {
        return weatherWebSite;
    }

    public WeatherData setWeatherWebSite(WeatherWebSite weatherWebSite) {
        this.weatherWebSite = weatherWebSite;
        return this;
    }
}
