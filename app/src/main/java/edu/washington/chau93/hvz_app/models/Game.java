package edu.washington.chau93.hvz_app.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

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
    /** The game end time in milliseconds. */
    private long myEndTime;
    /** The game start time in milliseconds. */
    private long myStartTime;
    /** The game mode id. */
    private String myGameModeId;
    /** The game mode/settings. */
    private GameMode myGameGameMode;
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
    /** The maximun amount of players allowed to play. */
    private int myMaxPlayers;

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

    public String getMyGameModeId() {
        return myGameModeId;
    }

    public GameMode getMyGameGameMode() {
        return myGameGameMode;
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
        return myHumanMap;
    }

    public Map<String, User> getMyZombieMap() {
        return myZombieMap;
    }

    public boolean isMyGameIsPrivate() {
        return myGameIsPrivate;
    }

    public int getMyMaxPlayers() {
        return myMaxPlayers;
    }
}
