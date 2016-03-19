package edu.washington.chau93.hvz_app.activities;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import edu.washington.chau93.hvz_app.R;

/**
 * Created by Henry on 3/18/2016.
 */
public class GameDetailsActivity extends AppCompatActivity {

    /** The TextView displaying title of the game. */
    private TextView myGameTitle;
    /** This TextView displays the player count. */
    private TextView myPlayerCount;
    /** The join game button. */
    private Button myJoinGameButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Connects the game title text field to this activity.
        myGameTitle = (TextView) findViewById(R.id.game_title);
        // Connects the player count text field to this activity.
        myPlayerCount = (TextView) findViewById(R.id.player_count);
        // Connects the join game button to this activity.
        myJoinGameButton = (Button) findViewById(R.id.join_game_button);
        myJoinGameButton.setOnClickListener(new JoinGameButtonListener());
    }

    /**
     * This listener class is for the join game button.
     * TODO: Join the user to the HvZ game
     */
    private final class JoinGameButtonListener implements View.OnClickListener {

        @Override
        public void onClick(final View theButton) {
            // Join the user to the game and move to next activity (if the join is successful).
        }
    }
}

