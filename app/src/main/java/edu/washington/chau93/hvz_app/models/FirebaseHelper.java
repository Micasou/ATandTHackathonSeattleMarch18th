package edu.washington.chau93.hvz_app.models;

import android.content.Context;
import android.util.Log;

import com.firebase.client.AuthData;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;
import java.util.Observable;

import edu.washington.chau93.hvz_app.activities.MainActivity;

/**
 * Created by Aaron on 3/18/2016.
 */
public class FirebaseHelper extends Observable {
    // Tag for debugging
    private static final String TAG = FirebaseHelper.class.getName();
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
    public void createGame(final Game theGame) {
        final Firebase newGameRef = myGamesRef.push();
        theGame.setMyGameUID(newGameRef.getKey());
        newGameRef.setValue(theGame, new Firebase.CompletionListener() {
            @Override
            public void onComplete(FirebaseError firebaseError, Firebase firebase) {
                if (firebaseError != null) {
                    Log.e(TAG, "Error: " + firebaseError.getMessage());
                }
            }
        });
    }

    // Get a user given an id. If a list is given, add the user to the list
    // but if not then notify observers.
    public void getUser(final String theUserId, final Map<String, User> theMap) {}

    // Get a game given an id
    public void getGame(String theGameId) {
        final Firebase game = myGamesRef.child(theGameId);
        game.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
                Log.d(TAG, dataSnapshot.toString());
                // Get the game
                final Game game = dataSnapshot.getValue(Game.class);
                if (game != null) {
                    getUsersInGame(game);

                    // Put the game in our map
                    MainActivity.getMyGameMap().put(game.getMyGameUID(), game);
                    // Notify the observers with the game.
                    setChanged();
                    notifyObservers(game);
                }
            }

            @Override
            public void onCancelled(final FirebaseError firebaseError) {
                // Show the error message
            }
        });
    }

    // Get a game mode given an id
    public void getMode() {}

    // Get all the users in a game given a game id
    public void getUsersInGame(final Game theGame) {
        myGamesRef.child(theGame.getMyGameUID()).child("myHumanIdList")
                .addChildEventListener(new GetUserChildEventListener(theGame.getMyHumanMap()));
        myGamesRef.child(theGame.getMyGameUID()).child("myZombieIdList")
                .addChildEventListener(new GetUserChildEventListener(theGame.getMyZombieMap()));
    }

    // Child event listener for get users in a game.
    private class GetUserChildEventListener implements ChildEventListener {
        private Map<String, User> myMap;

        private GetUserChildEventListener(Map<String, User> theMap) {
            myMap = theMap;
        }

        private class AddChangeValueEventListener implements ValueEventListener {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                final User user = dataSnapshot.getValue(User.class);
                if (user != null) {
                    myMap.put(user.getMyUID(), user);

                    setChanged();
                    notifyObservers(FirebaseHelperStatus.USER_ADDED_CHANGED);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        }

        @Override
        public void onChildAdded(DataSnapshot dataSnapshot, String s) {
            final String userId = (String) dataSnapshot.getValue();
            myUsersRef.child(userId).addListenerForSingleValueEvent(new AddChangeValueEventListener());
        }

        @Override
        public void onChildChanged(DataSnapshot dataSnapshot, String s) {
            final String userId = (String) dataSnapshot.getValue();
            myUsersRef.child(userId).addListenerForSingleValueEvent(new AddChangeValueEventListener());
        }

        @Override
        public void onChildRemoved(DataSnapshot dataSnapshot) {
            final String userId = (String) dataSnapshot.getValue();
            // Remove the user from the player map
            myMap.remove(userId);
            // remove the player from the database
            dataSnapshot.getRef().removeValue();
        }

        @Override
        public void onChildMoved(DataSnapshot dataSnapshot, String s) {

        }

        @Override
        public void onCancelled(FirebaseError firebaseError) {
            // Show the error
        }
    }

    // Get all the available games
    public void getAllGames() {
        myGamesRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                getGame(dataSnapshot.getKey());
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                getGame(dataSnapshot.getKey());
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });
    }


    public enum FirebaseHelperStatus {
        USER_ADDED_CHANGED,
        USER_REMOVED,
        GAME_RECEIVED
    }
}
