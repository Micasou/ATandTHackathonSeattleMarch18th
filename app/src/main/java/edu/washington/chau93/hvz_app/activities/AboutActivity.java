package edu.washington.chau93.hvz_app.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import edu.washington.chau93.hvz_app.R;

public class AboutActivity extends AppCompatActivity {

    private Random r = new Random();
    List<String> creditList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        creditList = new ArrayList<>();
        creditList.add("Aaron Chau");
        creditList.add("Alex Orozco");
        creditList.add("Can Huynh");
        creditList.add("Henry Lai");
        TextView name1 = (TextView) findViewById(R.id.name1);
        TextView name2 = (TextView) findViewById(R.id.name2);
        TextView name3 = (TextView) findViewById(R.id.name3);
        TextView name4 = (TextView) findViewById(R.id.name4);
        int randomIndex = r.nextInt(creditList.size());
        name1.setText(creditList.get(randomIndex));
        creditList.remove(randomIndex);
        randomIndex = r.nextInt(creditList.size());
        name2.setText(creditList.get(randomIndex));
        creditList.remove(randomIndex);
        randomIndex = r.nextInt(creditList.size());
        name3.setText(creditList.get(randomIndex));
        creditList.remove(randomIndex);
        randomIndex = r.nextInt(creditList.size());
        name4.setText(creditList.get(randomIndex));
        creditList.remove(randomIndex);

    }
}
