package org.codelouisville.data.Models;

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

    public int getHomeYards() {
        return homeYards;
    }

    public void setHomeYards(int homeYards) {
        this.homeYards = homeYards;
    }

    public int getAwayYards() {
        return awayYards;
    }

    public void setAwayYards(int awayYards) {
        this.awayYards = awayYards;
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
    private String homeTeam;
    private String awayTeam;
    private String stadiumName;
    private Boolean isDomeStadium;
    private int homeScore;
    private int awayScore;
    private int homeYards;
    private int awayYards;
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
                ", homeYards=" + homeYards +
                ", awayYards=" + awayYards +
                ", temperature=" + temperature +
                ", weatherCondition='" + weatherCondition + '\'' +
                '}';
    }
}
