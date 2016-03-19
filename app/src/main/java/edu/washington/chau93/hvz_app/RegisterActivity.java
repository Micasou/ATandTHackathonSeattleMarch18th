package edu.washington.chau93.hvz_app;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        Button registerBtn = (Button)findViewById(R.id.registerRegisterBtn);

    }

    /**
     * Listens to the click on register button.
     */
    private class RegisterClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            Toast.makeText(RegisterActivity.this, "Register Clicked", Toast.LENGTH_SHORT).show();
//            Intent intent = new Intent(LoginActivity.this, MapsActivity.class);
//            startActivity(intent);
//            finish();
        }
    }
}
