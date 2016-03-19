package edu.washington.chau93.hvz_app;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    /** The login button. */
    private Button myLoginButton;
    /** The text view for register text. */
    private TextView myRegisterText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        myLoginButton = (Button) findViewById(R.id.loginLoginBtn);
        myRegisterText = (TextView) findViewById(R.id.loginRegisterText);

        myLoginButton.setOnClickListener(new LoginClickListener());
        myRegisterText.setOnClickListener(new RegisterClickListener());

    }

    /**
     * Listens to the click on login button.
     */
    private class LoginClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Toast.makeText(LoginActivity.this, "Login Clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
//            Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
//            startActivity(intent);
//            finish();
        }
    }

    /**
     * Listens to the click on register button.
     */
    private class RegisterClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            // Toast.makeText(LoginActivity.this, "Register Clicked", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        }
    }

}

