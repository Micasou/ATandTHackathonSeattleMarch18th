package edu.washington.chau93.hvz_app.activities;

import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;

import java.util.List;

import edu.washington.chau93.hvz_app.R;
import edu.washington.chau93.hvz_app.models.Game;

public class LobbyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lobby);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
    }

    private class GameListAdapter extends BaseAdapter {
        private Activity myActivity;
        private List<Game> myGames;

        public GameListAdapter(Activity theActivity, List<Game> theGames) {
            myActivity = theActivity;
            myGames = theGames;
        }

        @Override
        public int getCount() {
            return myGames.size();
        }

        @Override
        public Object getItem(int position) {
            return myGames.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            final LayoutInflater layoutInflater = myActivity.getLayoutInflater();
            final Game game = myGames.get(position);

            return null;
        }
    }

}
