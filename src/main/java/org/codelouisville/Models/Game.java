package org.codelouisville.Models;

import javax.persistence.*;

@Entity
public class Game {
    public String getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(String homeTeam) {
        this.homeTeam = homeTeam;
    }

    public String getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(String awayTeam) {
        this.awayTeam = awayTeam;
    }

    public String getStadiumName() {
        return stadiumName;
    }

    public void setStadiumName(String stadiumName) {
        this.stadiumName = stadiumName;
    }

    public Boolean getDomeStadium() {
        return isDomeStadium;
    }

    public void setDomeStadium(Boolean domeStadium) {
        isDomeStadium = domeStadium;
    }

    public int getHomeScore() {
        return homeScore;
    }

    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    public int getAwayScore() {
        return awayScore;
    }

    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    //private long gameDateTime;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String homeTeam;
    private String awayTeam;
    private String stadiumName;
    private Boolean isDomeStadium;
    private int homeScore;
    private int awayScore;
    private Double temperature;
    private String weatherCondition;

    @Override
    public String toString() {
        return "Game{" +
                "homeTeam='" + homeTeam + '\'' +
                ", awayTeam='" + awayTeam + '\'' +
                ", stadiumName='" + stadiumName + '\'' +
                ", isDomeStadium=" + isDomeStadium +
                ", homeScore=" + homeScore +
                ", awayScore=" + awayScore +
                ", temperature=" + temperature +
                ", weatherCondition='" + weatherCondition + '\'' +
                '}';
    }
}
