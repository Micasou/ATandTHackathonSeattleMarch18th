package edu.washington.chau93.hvz_app.models;

import android.app.Application;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.firebase.client.Firebase;

import edu.washington.chau93.hvz_app.LoginActivity;
import edu.washington.chau93.hvz_app.MainActivity;

/**
 * Checks the firebase user authentication and
 * move the user to the correct activity depending if the
 * user is currently logged in or not.
 */
public class FirebaseAuthCheck extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Create a new firebase helper
        final FirebaseHelper fbHelper = new FirebaseHelper(this);

        // Find out which intent we need to go to by checking if user
        // auth data currently exist
        final Intent nextIntent;
        if (fbHelper.getAuth() == null) {
            // If auth data does not exist, go to login.
            nextIntent = new Intent(this, LoginActivity.class);
        } else {
            // If auth data exist go to main activity.
            nextIntent = new Intent(this, MainActivity.class);
        }
        // Set flag to not go back to this page.
        nextIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK  | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(nextIntent);
        finish();
    }
}
