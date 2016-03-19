package edu.washington.chau93.hvz_app.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import edu.washington.chau93.hvz_app.R;
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

    @Override
    protected void onStart() {
        super.onStart();
        // TODO: auto login

        // Get login information passed from intent.
        Bundle extras = getIntent().getExtras();

        // If there there is login info, login.
        if (extras != null) {
//            String user = extras.getString("username");
//            String pass = extras.getString("password");
//            ref.unauth();
//            ref.authWithPassword(user, pass, new loginAuthResultHandler());
            myUserName.setText(extras.getString("username"));
            myPassword.setText(extras.getString("password"));
        }
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
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK  | Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
            finish();
        }

        @Override
        public void onAuthenticationError(FirebaseError firebaseError) {
            Toast.makeText(LoginActivity.this, firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}

