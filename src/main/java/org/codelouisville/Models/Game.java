package org.codelouisville.Models;

import javax.persistence.*;

@Entity
public class Game {
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
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

    public Game()
    {}

    public Game(String homeTeam, String awayTeam, String stadiumName, Boolean isDomeStadium, int homeScore, int awayScore, Double temperature, String weatherCondition) {
        this.homeTeam = homeTeam;
        this.awayTeam = awayTeam;
        this.stadiumName = stadiumName;
        this.isDomeStadium = isDomeStadium;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.temperature = temperature;
        this.weatherCondition = weatherCondition;
    }


}
