package edu.washington.chau93.hvz_app.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import edu.washington.chau93.hvz_app.R;

public class CreateGameActivity extends AppCompatActivity {

    private EditText myGameTitleField;

    private EditText myMaxPlayersField;

    private EditText myGameDurationField;

    private Button myStartDateButton;

    private Button myStartTimeButton;

    private Button myCreateGameButton;

    private TextView myStartDateText;

    private TextView myStartTimeText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);

        // Connecting text fields from layout to this activity.
        // TODO: Grab user input data from these TEXT FIELDS
        myGameTitleField = (EditText) findViewById(R.id.game_title_textfield);
        myMaxPlayersField = (EditText) findViewById(R.id.max_players_textfield);
        myGameDurationField = (EditText) findViewById(R.id.game_duration_field);

        // Connecting buttons from layout to this activity.
        // TODO: Attach appropriate onclick listeners to these buttons.
        myStartDateButton = (Button) findViewById(R.id.start_date_button);
        myStartTimeButton = (Button) findViewById(R.id.start_time_button);
        myCreateGameButton = (Button) findViewById(R.id.create_game_button);

        // Connecting textviews from layout to this activity.
        // TODO: Update display text with appropriate date/time from user input.
        myStartDateText = (TextView) findViewById(R.id.start_date_text);
        myStartTimeText = (TextView) findViewById(R.id.start_time_text);
    }
}
