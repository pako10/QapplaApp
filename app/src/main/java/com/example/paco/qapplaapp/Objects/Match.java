package com.example.paco.qapplaapp.Objects;

/**
 * Created by paco on 13/04/2017.
 */

public class Match {
    String date;
    String hour;
    String adversary;
    Integer bet;
    String game;

    public Match() {
    }

    public Match(String date, String hour, String adversary, Integer bet, String game) {
        this.date = date;
        this.hour = hour;
        this.adversary = adversary;
        this.bet = bet;
        this.game = game;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHour() {
        return hour;
    }

    public void setHour(String hour) {
        this.hour = hour;
    }

    public String getAdversary() {
        return adversary;
    }

    public void setAdversary(String adversary) {
        this.adversary = adversary;
    }

    public Integer getBet() {
        return bet;
    }

    public void setBet(Integer bet) {
        this.bet = bet;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }
}
