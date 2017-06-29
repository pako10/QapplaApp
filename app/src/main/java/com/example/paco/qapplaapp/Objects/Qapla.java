package com.example.paco.qapplaapp.Objects;

/**
 * Created by mertsimsek on 14/09/15.
 */
public class Qapla {

    String userName;

    String userWins;
    String userLoses;
    String userLvl;
    String userBio;



    public String getUserName() {
        return userName;
    }

    public Qapla(String userName, String userWins, String userLoses, String userLvl, String userBio) {
        this.userName = userName;
        this.userWins = userWins;
        this.userLoses = userLoses;
        this.userLvl = userLvl;
        this.userBio = userBio;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRadioDial() {
        return userWins;
    }

    public void setRadioDial(String radioDial) {
        this.userWins = radioDial;
    }

    public String getUserWins() {
        return userWins;
    }

    public void setUserWins(String userWins) {
        this.userWins = userWins;
    }

    public String getUserLoses() {
        return userLoses;
    }

    public void setUserLoses(String userLoses) {
        this.userLoses = userLoses;
    }

    public String getUserLvl() {
        return userLvl;
    }

    public void setUserLvl(String userLvl) {
        this.userLvl = userLvl;
    }

    public String getUserBio() {
        return userBio;
    }

    public void setUserBio(String userBio) {
        this.userBio = userBio;
    }
}
