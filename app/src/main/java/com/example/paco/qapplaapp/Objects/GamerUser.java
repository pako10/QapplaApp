package com.example.paco.qapplaapp.Objects;

import java.util.List;

/**
 * Created by paco on 07/04/2017.
 */

public class GamerUser {
    String id;
    String UserName;
    String Email;
    int    Credits;
    String City;
    String Country;
    List<String> Equips;
    String Rank;
    int Experience;
    List<String> Games; //Cada que se agrega un juego en la base de juegos hacemos un barrido y por cada juego tomamos el valor de Users activos y le sumamos 1
   // List<String> Matches; //UNA BD NUEVA CON EL INT DE EL MATCH CON TODOS LOS DETALLES O PONEMOS UN CHILD ADENTRO DE OTRO CHILD CON TODOS LOS MATCHES
    List<String> Tournaments;
    List<String> Friends;
    private Match match;
    boolean status ;
    private FriendRequest friendRequest;
    int Wins;
    int Losses;
    String Bio; /**BIOGRAFIA O COMENTARIOS DE EL DUEÑO DE LA CUENTA*/

    public GamerUser(){}

    public GamerUser(String id, String userName, String email, int credits, String city, String country, List<String> equips, String rank,
                     int experience, List<String> games, List<String> tournaments, List<String> friends, Match match,
                     boolean status, FriendRequest friendRequest, int wins, int losses, String bio) {
        this.id = id;
        UserName = userName;
        Email = email;
        Credits = credits;
        City = city;
        Country = country;
        Equips = equips;
        Rank = rank;
        Experience = experience;
        Games = games;
        Tournaments = tournaments;
        Friends = friends;
        this.match = match;
        this.status = status;
        this.friendRequest = friendRequest;
        Wins = wins;
        Losses = losses;
        Bio = bio;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserName() {
        return UserName;
    }

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public int getCredits() {
        return Credits;
    }

    public void setCredits(int credits) {
        Credits = credits;
    }

    public String getCity() {
        return City;
    }

    public void setCity(String city) {
        City = city;
    }

    public String getCountry() {
        return Country;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public List<String> getEquips() {
        return Equips;
    }

    public void setEquips(List<String> equips) {
        Equips = equips;
    }

    public String getRank() {
        return Rank;
    }

    public void setRank(String rank) {
        this.Rank = rank;
    }

    public int getExperience() {
        return Experience;
    }

    public void setExperience(int experience) {
        Experience = experience;
    }

    public List<String> getGames() {
        return Games;
    }

    public void setGames(List<String> games) {
        Games = games;
    }

    public List<String> getTournaments() {
        return Tournaments;
    }

    public void setTournaments(List<String> tournaments) {
        Tournaments = tournaments;
    }

    public List<String> getFriends() {
        return Friends;
    }

    public void setFriends(List<String> friends) {
        Friends = friends;
    }

    public Match getMatch() {
        return match;
    }

    public void setMatch(Match match) {
        this.match = match;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public FriendRequest getFriendRequest() {
        return friendRequest;
    }

    public void setFriendRequest(FriendRequest friendRequest) {
        this.friendRequest = friendRequest;
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

    public String getBio() {
        return Bio;
    }

    public void setBio(String bio) {
        Bio = bio;
    }
}
