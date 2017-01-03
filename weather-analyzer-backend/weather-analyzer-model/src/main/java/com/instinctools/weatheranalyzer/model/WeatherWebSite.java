package com.instinctools.weatheranalyzer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class WeatherWebSite extends BaseEntity<Long> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "seq_genn")
    @SequenceGenerator(name = "seq_genn", sequenceName = "weather_web_site_id_seq", initialValue=1, allocationSize=1)
    @Column(name="id", columnDefinition="integer", nullable = false)
    private Long id;

    @Column
    private String name;

    @Column
    private String note;

    @Column
    private Long lastSuccessConnected;

    @Column
    private Long reatingPosition;

    @Override
    public Long getId() {
        return id;
    }

    public WeatherWebSite setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public WeatherWebSite setName(String name) {
        this.name = name;
        return this;
    }

    public String getNote() {
        return note;
    }

    public WeatherWebSite setNote(String note) {
        this.note = note;
        return this;
    }

    public Long getLastSuccessConnected() {
        return lastSuccessConnected;
    }

    public WeatherWebSite setLastSuccessConnected(Long lastSuccessConnected) {
        this.lastSuccessConnected = lastSuccessConnected;
        return this;
    }

    public Long getReatingPosition() {
        return lastSuccessConnected;
    }

    public WeatherWebSite setReatingPosition(Long reatingPosition) {
        this.reatingPosition = reatingPosition;
        return this;
    }

}
