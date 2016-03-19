package edu.washington.chau93.hvz_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class LoginActivity extends AppCompatActivity {

    /** The FireBase reference. */
    private Firebase ref;

    /** The login button. */
    private Button myLoginButton;
    /** The text view for register text. */
    private TextView myRegisterText;
    /** The editText for login username. */
    private EditText myUserName;
    /** The editText for login password. */
    private EditText myPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        Firebase.setAndroidContext(this);
        ref = new Firebase("https://hvzdatabase.firebaseio.com");

        myLoginButton = (Button) findViewById(R.id.loginLoginBtn);
        myRegisterText = (TextView) findViewById(R.id.loginRegisterText);

        myLoginButton.setOnClickListener(new LoginClickListener());
        myRegisterText.setOnClickListener(new RegisterClickListener());

        myUserName = (EditText)findViewById(R.id.loginUsername);
        myPassword = (EditText)findViewById(R.id.loginPass);

    }

    /**
     * Listens to the click on login button.
     */
    private class LoginClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            ref.authWithPassword(myUserName.getText().toString(), myPassword.getText().toString(), new loginAuthResultHandler());
        }
    }

    /**
     * Listens to the click on register button.
     */
    private class RegisterClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
    }

    /**
     * Handles user registration with FireBase.
     */
    private class loginAuthResultHandler implements Firebase.AuthResultHandler {

        @Override
        public void onAuthenticated(AuthData authData) {
            Toast.makeText(LoginActivity.this, "Logged in!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        @Override
        public void onAuthenticationError(FirebaseError firebaseError) {
            Toast.makeText(LoginActivity.this, firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}

