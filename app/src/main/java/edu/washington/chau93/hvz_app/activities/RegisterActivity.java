package edu.washington.chau93.hvz_app.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import edu.washington.chau93.hvz_app.R;

import com.firebase.client.AuthData;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

public class RegisterActivity extends AppCompatActivity {

    /** The FireBase reference. */
    private Firebase ref;

    private Button myRegisterBtn;
    /** The editText for registration username. */
    private EditText myUsername;
    /** The editText for registration e-mail. */
    private EditText myEmail;
    /** The editText for registration password. */
    private EditText myPassword;
    /** The editText for registration password confirmation. */
    private EditText myPasswordConfirm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Firebase.setAndroidContext(this);
        ref = new Firebase("https://hvzdatabase.firebaseio.com");

        myRegisterBtn = (Button)findViewById(R.id.registerRegisterBtn);
        myRegisterBtn.setOnClickListener(new RegisterClickListener());

        myUsername = (EditText)findViewById(R.id.registerUsername);
        myEmail = (EditText)findViewById(R.id.registerEmail);
        myPassword = (EditText)findViewById(R.id.registerPassword);
        myPasswordConfirm = (EditText)findViewById(R.id.registerPasswordConfirm);

    }

    /**
     * Listens to the click on register button.
     */
    private class RegisterClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            if (myPassword.getText().toString().equals(myPasswordConfirm.getText().toString())) {
                ref.createUser(myEmail.getText().toString(), myPassword.getText().toString(), new RegResultHandler());
            } else {
                Toast.makeText(RegisterActivity.this, "Passwords Do Not Match.", Toast.LENGTH_SHORT).show();
            }
        }
    }

    /**
     * Handles user registration with FireBase.
     */
    private class RegResultHandler implements Firebase.ResultHandler {

        @Override
        public void onSuccess() {
            Toast.makeText(RegisterActivity.this, "User Created!", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            // Pass user login information to login page
            intent.putExtra("username", myEmail.getText().toString());
            intent.putExtra("password", myPassword.getText().toString());
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
            finish();
//            ref.authWithPassword(myEmail.getText().toString(), myPassword.getText().toString(), new loginAuthResultHandler());
        }

        @Override
        public void onError(FirebaseError firebaseError) {
            Toast.makeText(RegisterActivity.this, firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

//    /**
//     * Handles user registration with FireBase.
//     */
//    private class loginAuthResultHandler implements Firebase.AuthResultHandler {
//
//        @Override
//        public void onAuthenticated(AuthData authData) {
//            Toast.makeText(RegisterActivity.this, "Logged in!", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
//            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK  | Intent.FLAG_ACTIVITY_CLEAR_TOP);
//            startActivity(intent);
//            finish();
//        }
//
//        @Override
//        public void onAuthenticationError(FirebaseError firebaseError) {
//            Toast.makeText(RegisterActivity.this, firebaseError.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    }

}
