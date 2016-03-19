package edu.washington.chau93.hvz_app.activities;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Observable;

import edu.washington.chau93.hvz_app.R;

public class CreateGameActivity extends AppCompatActivity {

    private EditText myGameTitleField;

    private EditText myMaxPlayersField;

    private EditText myGameDurationField;

    private Button myStartDateButton;

    private Button myStartTimeButton;

    private Button myMapsButton;

    private TextView myStartDateText;

    private TextView myStartTimeText;

    private TimePickerFragment myTimePicker;

    private DatePickerFragment myDatePicker;

    /** The hour selected by the user. */
    private int myHour;
    /** The minute selected by the user. */
    private int myMinute;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_game);


        // Connecting text fields from layout to this activity.
        // TODO: Grab user input data from these TEXT FIELDS
        myGameTitleField = (EditText) findViewById(R.id.game_title_textfield);
        myGameDurationField = (EditText) findViewById(R.id.game_duration_field);

        // Connecting buttons from layout to this activity.
        // TODO: Attach appropriate onclick listeners to these buttons.
        myStartDateButton = (Button) findViewById(R.id.start_date_button);
        myStartTimeButton = (Button) findViewById(R.id.start_time_button);
        myMapsButton = (Button) findViewById(R.id.map_button);

        // Connecting textviews from layout to this activity.
        // TODO: Update display text with appropriate date/time from user input.
        myStartDateText = (TextView) findViewById(R.id.start_date_text);
        myStartTimeText = (TextView) findViewById(R.id.start_time_text);

        // Updates date and time.
        updateTime();

        // Creates a date and time picker.
        myTimePicker = new TimePickerFragment();
        myTimePicker.setTimeView(myStartTimeText);
        myDatePicker = new DatePickerFragment();
        myDatePicker.setDateView(myStartDateText);

        // Attaching listeners to buttons
        myMapsButton.setOnClickListener(new MapButtonListener());
    }

    /**
     * Updates the display text for time.
     */
    private void updateTime() {
        final Calendar c = Calendar.getInstance();
        final int hour = c.get(Calendar.HOUR_OF_DAY);
        final int minute = c.get(Calendar.MINUTE);
        final int year = c.get(Calendar.YEAR);
        final int month = c.get(Calendar.MONTH);
        final int day = c.get(Calendar.DAY_OF_MONTH);

        myHour = hour % 12;
        myMinute = minute;
        String AMorPM;
        if (hour > 12) {
            AMorPM = " PM";
        } else if (hour == 12) {
            myHour = 12;
            AMorPM = " PM";
        } else if (hour == 0) {
            myHour = 12;
            AMorPM = " AM";
        } else {
            AMorPM = " AM";
        }
        if (myMinute > 9) {
            myStartTimeText.setText(myHour + ":" + myMinute + AMorPM);
        } else {
            myStartTimeText.setText(myHour + ":0" + myMinute + AMorPM);
        }
        myStartDateText.setText(month + "-" + day + "-" + year + "  ");
    }

    /**
     * Prompts user to select desired game area.
     */
    private class MapButtonListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(CreateGameActivity.this, MapsActivity.class);
            intent.putExtra("GAME_TITLE", myGameTitleField.getText().toString());
//            intent.putExtra("MAX_PLAYERS", Integer.parseInt(myMaxPlayersField.getText().toString()));
            intent.putExtra("START_DATE_TIME", myDatePicker.getLongTime() + myTimePicker.getLongTime());
            intent.putExtra("GAME_DURATION", Integer.parseInt(myGameDurationField.getText().toString()));
            startActivity(intent);
            finish();
        }
    }

    /**
     * Prompts for the user to select a time.
     * @param v the view to display on.
     */
    public void showTimePickerDialog(View v) {
        myTimePicker.show(getSupportFragmentManager(), "timePicker");
    }

    /**
     * Prompts for the user to select a date.
     * @param v the view to display on.
     */
    public void showDatePickerDialog(View v) {
        myDatePicker.show(getSupportFragmentManager(), "datePicker");
    }

    /**
     * This TimePickerFragment displays a clock for the user to select a time.
     */
    public static class TimePickerFragment extends DialogFragment
            implements TimePickerDialog.OnTimeSetListener {

        private Calendar c;
        private int myHour;
        private int myMinute;
        private long longTime;
        private TextView myTimeTV;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current time as the default values for the picker
            c = Calendar.getInstance();
            final int hour = c.get(Calendar.HOUR_OF_DAY);
            final int minute = c.get(Calendar.MINUTE);

            // Create a new instance of TimePickerDialog and return it
            return new TimePickerDialog(getActivity(), this, hour, minute,
                    DateFormat.is24HourFormat(getActivity()));
        }

        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            // Do something with the time chosen by the user
            myHour = hourOfDay % 12;
            myMinute = minute;
            String AMorPM;
            if (hourOfDay > 12) {
                AMorPM = " PM";
            } else if (hourOfDay == 12) {
                myHour = 12;
                AMorPM = " PM";
            } else if (hourOfDay == 0) {
                myHour = 12;
                AMorPM = " AM";
            } else {
                AMorPM = " AM";
            }
            if (myMinute > 9) {
                myTimeTV.setText(myHour + ":" + myMinute + AMorPM);
            } else {
                myTimeTV.setText(myHour + ":0" + myMinute + AMorPM);
            }
            c.set(0, 0, 0, hourOfDay, minute);
            longTime = c.getTimeInMillis();
        }

        public long getLongTime() {
            return longTime;
        }

        public void setTimeView(TextView theTimeTV) {
            myTimeTV = theTimeTV;
        }

        public int getHour() {
            return myHour;
        }

        public int getMinute() {
            return myMinute;
        }
    }

    /**
     * This DatePickerFragment allows the user to select a date.
     */
    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        private Calendar c;
        private int myYear;
        private int myMonth;
        private int myDay;
        private long longTime;
        private TextView myDateTV;

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            // Use the current date as the default date in the picker
            c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);
            longTime = c.getTimeInMillis();

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            myYear = year;
            myMonth = month;
            myDay = day;
            myDateTV.setText(month + "-" + day + "-" + year + "  ");
            c.set(year, month, day, 0, 0);
            longTime = c.getTimeInMillis();
        }

        public void setDateView(TextView dateView) {
            myDateTV = dateView;
        }

        public long getLongTime() {
            return longTime;
        }

        public int getYear() {
            return myYear;
        }

        public int getMonth(){
            return myMonth;
        }

        public int getDay() {
            return myDay;
        }
    }
}
