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
        final FirebaseHelper fbHelper = new FirebaseHelper(this);
    }

    /**
     * This method sets up all the menu activities used.
     */
    private void setupMenuButtons() {
        //myGamesButton.setOnClickListener(new MenuButtonListener(GameDetailsActivity.class));
        myRulesButton.setOnClickListener(new MenuButtonListener(RulesActivity.class));
        myAboutButton.setOnClickListener(new MenuButtonListener(AboutActivity.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
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
