package edu.washington.chau93.hvz_app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import edu.washington.chau93.hvz_app.models.AboutActivity;
import edu.washington.chau93.hvz_app.models.FirebaseHelper;
import edu.washington.chau93.hvz_app.models.RulesActivity;

public class MainActivity extends AppCompatActivity {
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
        myGamesButton = (Button) findViewById(R.id.games_button);
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
        //myGamesButton.setOnClickListener(new MenuButtonListener(GameDetailsActivity.class));
        myRulesButton.setOnClickListener(new MenuButtonListener(RulesActivity.class));
        myAboutButton.setOnClickListener(new MenuButtonListener(AboutActivity.class));
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
}
