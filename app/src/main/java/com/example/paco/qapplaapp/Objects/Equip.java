package com.example.paco.qapplaapp.Objects;

import java.util.List;

/**
 * Created by paco on 17/04/2017.
 */

public class Equip {
    private String equipId;
    private List<String> Members;
    private String Captain;
    private Match Match;
    private List<String> Tournaments;
    private List<String> Games;
    private int equipSize; /** maximo de integrantes de equipos **/
    private String Level;
    private int Experience;
    private int Wins;
    private int Losses;
    private String BioEquip;
    private String Searching;

    public Equip() {
    }

    public Equip(String equipId, List<String> members, String captain, com.example.paco.qapplaapp.Objects.Match match, List<String> tournaments,
                 List<String> games, int equipSize, String level, int experience, int wins, int losses, String bioEquip, String searching) {
        this.equipId = equipId;
        Members = members;
        Captain = captain;
        Match = match;
        Tournaments = tournaments;
        Games = games;
        this.equipSize = equipSize;
        Level = level;
        Experience = experience;
        Wins = wins;
        Losses = losses;
        BioEquip = bioEquip;
        Searching = searching;
    }

    public String getEquipId() {
        return equipId;
    }

    public void setEquipId(String equipId) {
        this.equipId = equipId;
    }

    public List<String> getMembers() {
        return Members;
    }

    public void setMembers(List<String> members) {
        Members = members;
    }

    public String getCaptain() {
        return Captain;
    }

    public void setCaptain(String captain) {
        Captain = captain;
    }

    public com.example.paco.qapplaapp.Objects.Match getMatch() {
        return Match;
    }

    public void setMatch(com.example.paco.qapplaapp.Objects.Match match) {
        Match = match;
    }

    public List<String> getTournaments() {
        return Tournaments;
    }

    public void setTournaments(List<String> tournaments) {
        Tournaments = tournaments;
    }

    public List<String> getGames() {
        return Games;
    }

    public void setGames(List<String> games) {
        Games = games;
    }

    public int getEquipSize() {
        return equipSize;
    }

    public void setEquipSize(int equipSize) {
        this.equipSize = equipSize;
    }

    public String getLevel() {
        return Level;
    }

    public void setLevel(String level) {
        Level = level;
    }

    public int getExperience() {
        return Experience;
    }

    public void setExperience(int experience) {
        Experience = experience;
    }

    public int getWins() {
        return Wins;
    }

    public void setWins(int wins) {
        Wins = wins;
    }

    public int getLosses() {
        return Losses;
    }

    public void setLosses(int losses) {
        Losses = losses;
    }

    public String getBioEquip() {
        return BioEquip;
    }

    public void setBioEquip(String bioEquip) {
        BioEquip = bioEquip;
    }

    public String getSearching() {
        return Searching;
    }

    public void setSearching(String searching) {
        Searching = searching;
    }
}
