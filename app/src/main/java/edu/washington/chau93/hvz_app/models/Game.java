package edu.washington.chau93.hvz_app.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * The game class holds information about a game, such as
 * the game uid, end and start time, game settings/mode,
 * the status of the game, and finally a list of users in the
 * game.
 */
@JsonIgnoreProperties({"myHumanList", "myZombieList", "myGameUID"})
public class Game {
    /** The game unique ID */
    private String myGameUID;
    /** The game end time in milliseconds. */
    private long myEndTime;
    /** The game start time in milliseconds. */
    private long myStartTime;
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
    /** A list of the user ids. */
    private List<String> myUserIdList;
    /** A list of the human players. */
    private List<User> myHumanList;
    /** A list of zombie players. */
    private List<User> myZombieList;
    /** A flag if the game is private. */
    private boolean myGameIsPrivate;
    /** The maximun amount of players allowed to play. */
    private int myMaxPlayers;
}
