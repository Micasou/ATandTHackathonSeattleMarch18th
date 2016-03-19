package edu.washington.chau93.hvz_app.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

/**
 * The game class holds information about a game, such as
 * the game uid, end and start time, game settings/mode,
 * the status of the game, and finally a list of users in the
 * game.
 */
@JsonIgnoreProperties({"myHumanList", "myZombieList"})
public class Game {
    private String myGameUID;
    private long myEndTime;
    private long myStartTime;
    private GameMode myGameGameMode;
    /**
     * GameStatus is a enum with the following:
     * GameStatus.WAITING
     * GameStatus.PAUSED
     * GameStatus.IN_PROGRESS
     * GameStatus.FINISHED
     */
    private GameStatus gameStatus;
    private List<String> myUserIdList;
    private List<User> myHumanList;
    private List<User> myZombieList;
    private boolean myGameIsPrivate;
}
