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
public class WeatherForecast extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_genn")
    @SequenceGenerator(name = "seq_genn", sequenceName = "weather_forecast_id_seq", initialValue=1, allocationSize=1)
    @Column(name="id", columnDefinition="integer", nullable = false)
    private Long id;

    @Column
    private Long createdAtTimestamp;

    @Column
    private Long forecastTemperature;

    @Column
    private Long realTemperature;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private WeatherWebSite weatherWebSite;

    @Override
    public Long getId() {
        return id;
    }

    public WeatherForecast setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCreatedAtTimestamp() {
        return createdAtTimestamp;
    }

    public WeatherForecast setCreatedAtTimestamp(Long createdAtTimestamp) {
        this.createdAtTimestamp = createdAtTimestamp;
        return this;
    }

    public Long getForecastTemperature() {
        return forecastTemperature;
    }

    public WeatherForecast setForecastTemperature(Long forecastTemperature) {
        this.forecastTemperature = forecastTemperature;
        return this;
    }

    public Long getRealTemperature() {
        return realTemperature;
    }

    public WeatherForecast setRealTemperature(Long realTemperature) {
        this.realTemperature = realTemperature;
        return this;
    }

    public WeatherWebSite getWeatherWebSite() {
         return weatherWebSite;
     }

     public WeatherForecast setWeatherWebSite(WeatherWebSite weatherWebSite) {
         this.weatherWebSite = weatherWebSite;
         return this;
     }
}
