package edu.washington.chau93.hvz_app.activities;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
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
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import edu.washington.chau93.hvz_app.R;
import edu.washington.chau93.hvz_app.models.Game;

public class LobbyActivity extends AppCompatActivity implements Observer {
    private GameListAdapter myGameListAdapter;

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

        MainActivity.getMyFirebaseHelper().addObserver(this);

        final ListView listView = (ListView) findViewById(R.id.lobby_game_list_view);
        myGameListAdapter = new GameListAdapter(this,
                new ArrayList<>(MainActivity.getMyGameMap().values()));
        listView.setAdapter(myGameListAdapter);
    }

    @Override
    public void update(Observable observable, Object data) {
        myGameListAdapter.notifyDataSetChanged();
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
            if (convertView == null) {
                convertView = layoutInflater.inflate(R.layout.game_list_item, parent, false);
            }

            final TextView gameIdTextView = (TextView) convertView.findViewById(R.id.game_list_item_game_uid);
            gameIdTextView.setText(myGames.get(position).getMyGameUID());
            convertView.setOnClickListener(new ItemOnClickListener());

            return convertView;
        }

        private class ItemOnClickListener implements View.OnClickListener {
            @Override
            public void onClick(View v) {
                DialogInterface.OnClickListener dialogClickListener = new DialogListener();

                AlertDialog.Builder ab = new AlertDialog.Builder(LobbyActivity.this);
                ab.setMessage("Would you like to join this game?").setPositiveButton("Join!", dialogClickListener)
                        .setNegativeButton("Cancel", dialogClickListener).show();
            }
        }

        private class DialogListener implements DialogInterface.OnClickListener {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        // Move them to the map again.
                        break;

                    case DialogInterface.BUTTON_NEGATIVE:
                        //Do your No progress
                        break;
                }
            }
        }
    }

}
