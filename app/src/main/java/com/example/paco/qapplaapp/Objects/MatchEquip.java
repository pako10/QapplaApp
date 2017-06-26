package com.example.paco.qapplaapp.Objects;

import java.util.List;

/**
 * Created by paco on 13/04/2017.
 */

public class MatchEquip {
    private String date;
    private String hour;
    private List<String> equip1;
    private List<String> equip2;
    private Integer bet;
    private String game;
    private String observations;
    private String numMatches;


    public MatchEquip() {
    }

    public MatchEquip(String date, String hour, List<String> equip1, List<String> equip2, Integer bet, String game, String observations, String numMatches) {
        this.date = date;
        this.hour = hour;
        this.equip1 = equip1;
        this.equip2 = equip2;
        this.bet = bet;
        this.game = game;
        this.observations = observations;
        this.numMatches = numMatches;
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

    public List<String> getEquip1() {
        return equip1;
    }

    public void setEquip1(List<String> equip1) {
        this.equip1 = equip1;
    }

    public List<String> getEquip2() {
        return equip2;
    }

    public void setEquip2(List<String> equip2) {
        this.equip2 = equip2;
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

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}
