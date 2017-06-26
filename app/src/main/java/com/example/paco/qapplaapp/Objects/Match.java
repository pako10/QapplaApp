package com.example.paco.qapplaapp.Objects;

/**
 * Created by paco on 13/04/2017.
 */

public class Match {
    String date;
    String hour;
    String adversary1;
    String adversary2;
    Integer bet;
    String game;
    String numMatches;
    String observations;
    String privado; /** SE PONE EN LA BASE DE DATOS SI ES PRIVADO O NO **/

    public Match() {
    }

    public Match(String date, String hour, String adversary1, String adversary2, Integer bet, String game, String numMatches, String observations) {
        this.date = date;
        this.hour = hour;
        this.adversary1 = adversary1;
        this.adversary2 = adversary2;
        this.bet = bet;
        this.game = game;
        this.numMatches = numMatches;
        this.observations = observations;
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

    public String getAdversary1() {
        return adversary1;
    }

    public void setAdversary1(String adversary1) {
        this.adversary1 = adversary1;
    }

    public String getAdversary2() {
        return adversary2;
    }

    public void setAdversary2(String adversary2) {
        this.adversary2 = adversary2;
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

    public String getNumMatches() {
        return numMatches;
    }

    public void setNumMatches(String numMatches) {
        this.numMatches = numMatches;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }
}
