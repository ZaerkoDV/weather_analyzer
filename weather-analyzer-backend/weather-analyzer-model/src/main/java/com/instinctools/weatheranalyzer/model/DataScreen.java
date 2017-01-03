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
public class DataScreen extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_genn")
    @SequenceGenerator(name = "seq_genn", sequenceName = "data_screen_id_seq", initialValue=1, allocationSize=1)
    @Column(name="id", columnDefinition="integer", nullable = false)
    private Long id;

    @Column
    private Long createdAtTimestamp;

    @Column
    private byte[] screenData;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private WeatherData weatherData;

    @Override
    public Long getId() {
        return id;
    }

    public DataScreen setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getCreatedAtTimestamp() {
        return createdAtTimestamp;
    }

    public DataScreen setCreatedAtTimestamp(Long createdAtTimestamp) {
        this.createdAtTimestamp = createdAtTimestamp;
        return this;
    }

    public byte[] getScreenData() {
        return screenData;
    }

    public DataScreen setScreenData(byte[] screenData) {
        this.screenData = screenData;
        return this;
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public DataScreen setWeatherData(WeatherData weatherData) {
        this.weatherData = weatherData;
        return this;
    }
}
