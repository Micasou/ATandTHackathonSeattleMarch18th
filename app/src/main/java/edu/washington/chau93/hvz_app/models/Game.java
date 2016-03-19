package edu.washington.chau93.hvz_app.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.android.gms.maps.model.LatLng;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The game class holds information about a game, such as
 * the game uid, end and start time, game settings/mode,
 * the status of the game, and finally a list of users in the
 * game.
 */
@JsonIgnoreProperties({"myHumanMap", "myZombieMap", "myGameMode"})
public class Game {
    /** The game unique ID */
    private String myGameUID;
    /** The game start time in milliseconds. */
    private long myStartTime;
    /** The game end time in milliseconds. */
    private long myEndTime;
    /** The game settings. */
    private GameMode myGameMode;
    /**
     * GameStatus is a enum with the following:
     * GameStatus.WAITING
     * GameStatus.PAUSED
     * GameStatus.IN_PROGRESS
     * GameStatus.FINISHED
     */
    private GameStatus gameStatus;
    /** A list of the human ids. */
    private List<String> myHumanIdList;
    /** A list of the zombie ids. */
    private List<String> myZombieIdList;
    /** A list of the human players. */
    private Map<String, User> myHumanMap;
    /** A list of zombie players. */
    private Map<String, User> myZombieMap;
    /** A flag if the game is private. */
    private boolean myGameIsPrivate;
    /** The owner id */
    private String myOwnerUID;
    /** The list of points */
    private List<Double> myBoundries;

    public Game() {}

    public Game(long myStartTime, long myEndTime, GameMode myGameMode, GameStatus gameStatus
            , List<String> myHumanIdList, List<String> myZombieIdList, boolean myGameIsPrivate
            , int myMaxPlayers, String myOwnerUID, List<Double> myBoundries) {
        this.myEndTime = myEndTime;
        this.myStartTime = myStartTime;
        this.myGameMode = myGameMode;
        this.gameStatus = gameStatus;
        this.myHumanIdList = myHumanIdList;
        this.myZombieIdList = myZombieIdList;
        this.myHumanMap = new HashMap<>();
        this.myZombieMap = new HashMap<>();
        this.myGameIsPrivate = myGameIsPrivate;
        this.myOwnerUID = myOwnerUID;
        this.myBoundries = myBoundries;
    }

    public String getMyGameUID() {
        return myGameUID;
    }

    public void setMyGameUID(String theGameUID) {
        myGameUID = theGameUID;
    }

    public long getMyEndTime() {
        return myEndTime;
    }

    public long getMyStartTime() {
        return myStartTime;
    }

    public GameMode getMyGameMode() {
        return myGameMode;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public List<String> getMyHumanIdList() {
        return myHumanIdList;
    }

    public List<String> getMyZombieIdList() {
        return myZombieIdList;
    }

    public Map<String, User> getMyHumanMap() {
        if (myHumanMap == null) myHumanMap = new HashMap<>();
        return myHumanMap;
    }

    public Map<String, User> getMyZombieMap() {
        if (myZombieMap == null) myZombieMap = new HashMap<>();
        return myZombieMap;
    }

    public boolean isMyGameIsPrivate() {
        return myGameIsPrivate;
    }

    public String getMyOwnerUID() {
        return myOwnerUID;
    }

    public List<Double> getMyBoundries() {
        return myBoundries;
    }

    @Override
    public String toString() {
        return "Game{" +
                "myGameUID='" + myGameUID + '\'' +
                ", myStartTime=" + myStartTime +
                ", myEndTime=" + myEndTime +
                ", myGameMode=" + myGameMode +
                ", gameStatus=" + gameStatus +
                ", myHumanIdList=" + myHumanIdList +
                ", myZombieIdList=" + myZombieIdList +
                ", myHumanMap=" + myHumanMap +
                ", myZombieMap=" + myZombieMap +
                ", myGameIsPrivate=" + myGameIsPrivate +
                ", myOwnerUID='" + myOwnerUID + '\'' +
                ", myBoundries=" + myBoundries +
                '}';
    }
}
