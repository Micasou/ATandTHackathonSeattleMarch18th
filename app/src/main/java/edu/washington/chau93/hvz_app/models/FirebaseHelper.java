package edu.washington.chau93.hvz_app.models;

import android.content.Context;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;

import java.util.Observable;

/**
 * Created by Aaron on 3/18/2016.
 */
public class FirebaseHelper extends Observable {
    // The base firebase reference
    private Firebase myBaseRef;
    // The user reference
    private Firebase myUsersRef;
    // The Games reference
    private Firebase myGamesRef;
    // The GameMode reference
    private Firebase myModeRef;

    public FirebaseHelper(Context theContext) {
        Firebase.setAndroidContext(theContext);
        // Firebase.getDefaultConfig().setPersistenceEnabled(true);
        myBaseRef = new Firebase("https://hvzdatabase.firebaseio.com/");
        myUsersRef = myBaseRef.child("users");
        myGamesRef = myBaseRef.child("games");
        myModeRef = myBaseRef.child("modes");
    }

    public AuthData getAuth() {
        return myBaseRef.getAuth();
    }

    public void logout() { myBaseRef.unauth(); }

    public void createNewUser(User theUser) {

    }

    // Create a game given an game object
    public void createGame() {

    }

    // Get a user given an id
    public void getUser() {}

    // Get a game given an id
    public void getGame() {}

    // Get a game mode given an id
    public void getMode() {}

    // Get all the users in a game given a game id
    public void getUsersInGame() {}

    // Get all the available games
    public void getAllGames() {}


}
