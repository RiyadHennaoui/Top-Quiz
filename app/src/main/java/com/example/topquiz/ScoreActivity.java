package com.example.topquiz;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.example.topquiz.MainActivity.PREF_KEY_FIRSTNAME_1;
import static com.example.topquiz.MainActivity.PREF_KEY_FIRSTNAME_2;
import static com.example.topquiz.MainActivity.PREF_KEY_FIRSTNAME_3;
import static com.example.topquiz.MainActivity.PREF_KEY_FIRSTNAME_4;
import static com.example.topquiz.MainActivity.PREF_KEY_FIRSTNAME_5;
import static com.example.topquiz.MainActivity.PREF_KEY_SCORE_1;
import static com.example.topquiz.MainActivity.PREF_KEY_SCORE_2;
import static com.example.topquiz.MainActivity.PREF_KEY_SCORE_3;
import static com.example.topquiz.MainActivity.PREF_KEY_SCORE_4;
import static com.example.topquiz.MainActivity.PREF_KEY_SCORE_5;
import static com.example.topquiz.MainActivity.SHARED_PREF_MAIN;

public class ScoreActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mFirstname_1;
    private TextView mFirstname_2;
    private TextView mFirstname_3;
    private TextView mFirstname_4;
    private TextView mFirstname_5;
    private TextView mBestScore_1;
    private TextView mBestScore_2;
    private TextView mBestScore_3;
    private TextView mBestScore_4;
    private TextView mBestScore_5;
    private List<Player> mPlayers;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        mFirstname_1 = findViewById(R.id.firstname_1);
        mFirstname_2 = findViewById(R.id.firstname_2);
        mFirstname_3 = findViewById(R.id.firstname_3);
        mFirstname_4 = findViewById(R.id.firstname_4);
        mFirstname_5 = findViewById(R.id.firstname_5);
        mBestScore_1 = findViewById(R.id.best_score_1);
        mBestScore_2 = findViewById(R.id.best_score_2);
        mBestScore_3 = findViewById(R.id.best_score_3);
        mBestScore_4 = findViewById(R.id.best_score_4);
        mBestScore_5 = findViewById(R.id.best_score_5);

        fillPlayersNameAndScore();

        displayPlayers();


        Button btnSortHighScore = findViewById(R.id.btn_high_score);

        btnSortHighScore.setOnClickListener(this);

        Collections.sort(mPlayers, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o2.getScore() - o1.getScore();
            }
        });

        Button btnSortAlpha = findViewById(R.id.btn_alpha_score);

        btnSortAlpha.setOnClickListener(this);

        Collections.sort(mPlayers, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return  o2.getName().compareTo(o1.getName());
            }
        });


    }


    private void fillPlayersNameAndScore() {

        SharedPreferences sharedPreferencesScore = getSharedPreferences(SHARED_PREF_MAIN,
                MODE_PRIVATE);

        String playername1 = sharedPreferencesScore.getString(PREF_KEY_FIRSTNAME_1, null);
        String playername2 = sharedPreferencesScore.getString(PREF_KEY_FIRSTNAME_2, null);
        String playername3 = sharedPreferencesScore.getString(PREF_KEY_FIRSTNAME_3, null);
        String playername4 = sharedPreferencesScore.getString(PREF_KEY_FIRSTNAME_4, null);
        String playername5 = sharedPreferencesScore.getString(PREF_KEY_FIRSTNAME_5, null);

        int score1 = sharedPreferencesScore.getInt(PREF_KEY_SCORE_1, 0);
        int score2 = sharedPreferencesScore.getInt(PREF_KEY_SCORE_2, 0);
        int score3 = sharedPreferencesScore.getInt(PREF_KEY_SCORE_3, 0);
        int score4 = sharedPreferencesScore.getInt(PREF_KEY_SCORE_4, 0);
        int score5 = sharedPreferencesScore.getInt(PREF_KEY_SCORE_5, 0);

        Player player1 = new Player(playername1, score1);
        Player player2 = new Player(playername2, score2);
        Player player3 = new Player(playername3, score3);
        Player player4 = new Player(playername4, score4);
        Player player5 = new Player(playername5, score5);
        mPlayers = new ArrayList<>();
        mPlayers.add(player1);
        mPlayers.add(player2);
        mPlayers.add(player3);
        mPlayers.add(player4);
        mPlayers.add(player5);
    }

    private void displayPlayers() {

        if (mPlayers.size() > 0) {

            mFirstname_1.setText(mPlayers.get(0).getName());
            mBestScore_1.setText(String.valueOf(mPlayers.get(0).getScore()));


        }
        if (mPlayers.size() > 1) {

            mFirstname_2.setText(mPlayers.get(1).getName());
            mBestScore_2.setText(String.valueOf(mPlayers.get(1).getScore()));


        }
        if (mPlayers.size() > 2) {

            mFirstname_3.setText(mPlayers.get(2).getName());
            mBestScore_3.setText(String.valueOf(mPlayers.get(2).getScore()));


        }
        if (mPlayers.size() > 3) {

            mFirstname_4.setText(mPlayers.get(3).getName());
            mBestScore_4.setText(String.valueOf(mPlayers.get(3).getScore()));


        }
        if (mPlayers.size() > 4) {

            mFirstname_5.setText(mPlayers.get(4).getName());
            mBestScore_5.setText(String.valueOf(mPlayers.get(4).getScore()));


        }
    }


    @Override
    public void onClick(View v) {
        Collections.sort(mPlayers, new Comparator<Player>() {
            @Override
            public int compare(Player o1, Player o2) {
                return o2.getScore() - o1.getScore();
            }
        });

        displayPlayers();
    }


    public static class Player {

        private final String mName;
        private final int mScore;

        public String getName() {
            return mName;
        }

        public int getScore() {
            return mScore;
        }

        Player(String name, int score) {


            mName = name;
            mScore = score;
        }

    }
}



