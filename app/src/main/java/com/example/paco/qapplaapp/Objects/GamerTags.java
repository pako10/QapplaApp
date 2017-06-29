package com.example.paco.qapplaapp.Objects;

/**
 * Created by paco on 28/06/2017.
 */

public class GamerTags {

    String gamePlatform;
    String gamerTag;

    public GamerTags() {
    }

    public GamerTags(String gamePlatform, String gamerTag) {
        this.gamePlatform = gamePlatform;
        this.gamerTag = gamerTag;
    }

    public String getGamePlatform() {
        return gamePlatform;
    }

    public void setGamePlatform(String gamePlatform) {
        this.gamePlatform = gamePlatform;
    }

    public String getGamerTag() {
        return gamerTag;
    }

    public void setGamerTag(String gamerTag) {
        this.gamerTag = gamerTag;
    }
}
