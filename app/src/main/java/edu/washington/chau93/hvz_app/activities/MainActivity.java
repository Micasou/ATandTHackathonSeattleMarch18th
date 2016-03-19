package edu.washington.chau93.hvz_app.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.firebase.client.AuthData;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import edu.washington.chau93.hvz_app.MapsActivity;
import edu.washington.chau93.hvz_app.R;
import edu.washington.chau93.hvz_app.models.CustomLatLng;
import edu.washington.chau93.hvz_app.models.FirebaseHelper;
import edu.washington.chau93.hvz_app.models.Game;
import edu.washington.chau93.hvz_app.models.GameMode;
import edu.washington.chau93.hvz_app.models.GameStatus;

public class MainActivity extends AppCompatActivity implements Observer {
    private static final String TAG = MainActivity.class.getName();
    protected static FirebaseHelper myFirebaseHelper;

    /** The button used to search for nearby games. */
    private Button myGamesButton;
    /** The button used to show the rules of HvZ. */
    private Button myRulesButton;
    /** The button used to about page. */
    private Button myAboutButton;
    /** The button used to log out. */
    private Button myLogoutButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        // Connect the layout button elements to this activity
        myGamesButton = (Button) findViewById(R.id.create_button);
        myRulesButton = (Button) findViewById(R.id.rules_button);
        myAboutButton = (Button) findViewById(R.id.about_button);
        myLogoutButton = (Button) findViewById(R.id.logout_button);
        // Sets up the menu buttons.
        setupMenuButtons();

        // Make a firebase helper reference.
        myFirebaseHelper = new FirebaseHelper(this);
    }

    // Returns our firebase helper reference
    // Make sure to check if firebase helper is null. (Although you really shouldn't be calling
    // this method if you haven't gotten to main yet.)
    public static FirebaseHelper getMyFirebaseHelper() {
        return myFirebaseHelper;
    }

    /**
     * This method sets up all the menu activities used.
     */
    private void setupMenuButtons() {
//        myGamesButton.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
                // Adding a new game
//                final Calendar startCal = Calendar.getInstance();
//                final Calendar endCal = Calendar.getInstance();
//                endCal.add(Calendar.DATE, 14);
//                final List<String> humanIdList = new ArrayList<String>();
//                AuthData myAuth = myFirebaseHelper.getAuth();
//                humanIdList.add(myAuth.getUid());
//                final List<Double> latlong = new ArrayList<Double>();
//                latlong.add(47.612843);
//                latlong.add(-122.333678);
//                latlong.add(47.612259);
//                latlong.add(-122.335223);
//
//                Game game = new Game(startCal.getTimeInMillis(),
//                        endCal.getTimeInMillis(),
//                        new GameMode(),
//                        GameStatus.WAITING,
//                        humanIdList,
//                        new ArrayList<String>(),
//                        false,
//                        100,
//                        myAuth.getUid(),
//                        latlong);

//                myFirebaseHelper.createGame(game);
//                myFirebaseHelper.addObserver(MainActivity.this);
//                myFirebaseHelper.getGame("-KDFFLNLkcz9gKcSr_BB");
//                Log.d(TAG, "Clicked");
//            }
//        });
//        myGamesButton.setOnClickListener(new MenuButtonListener(GameDetailsActivity.class));
        myGamesButton.setOnClickListener(new MenuButtonListener(CreateGameActivity.class));
        myRulesButton.setOnClickListener(new MenuButtonListener(RulesActivity.class));
        myAboutButton.setOnClickListener(new MenuButtonListener(AboutActivity.class));
        myLogoutButton.setOnClickListener(new LogoutClickListener());
    }


    private Game testGame;
    @Override
    public void update(Observable observable, Object data) {
        Log.d(TAG, "Update happened." + data);
        if (data instanceof Game) {
            testGame = (Game) data;
            Log.d(TAG, "Game info: " + testGame.toString());
        }
        if (data instanceof FirebaseHelper.FirebaseHelperStatus) {
            FirebaseHelper.FirebaseHelperStatus fbhs = (FirebaseHelper.FirebaseHelperStatus) data;
            if (fbhs == FirebaseHelper.FirebaseHelperStatus.USER_ADDED_CHANGED) {
                Log.d(TAG, testGame.getMyHumanMap().toString());
            }
        }
    }

    /**
     * This listener is for menu buttons.
     */
    private class MenuButtonListener implements View.OnClickListener {

        /** The activity class to start upon button press. */
        private Class myClass;

        /**
         * Constructor for menu button listener.
         * @param theClass the activity class to start on button press.
         */
        public MenuButtonListener(Class theClass) {
            myClass = theClass;
        }

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(MainActivity.this, myClass);
            startActivity(intent);
        }
    }

    /**
     * Listens to logout click.
     */
    private class LogoutClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            myFirebaseHelper.logout();
            Intent intent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
