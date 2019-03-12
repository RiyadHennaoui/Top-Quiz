package com.example.topquiz;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final int GAME_ACTIVITY_REQUEST_CODE = 1;
    private TextView mGreetingText;
    private EditText mNameInput;
    private Button mPlayButton;
    private Button mHistoryButton;
    public static final String SHARED_PREF_MAIN = "SHARED_PREF_MAIN";
    private User mUser;
    private SharedPreferences mPreferences;
    public static final String PREF_KEY_SCORE_1 = "PREF_KEY_SCORE_1";
    public static final String PREF_KEY_SCORE_2 = "PREF_KEY_SCORE_2";
    public static final String PREF_KEY_SCORE_3 = "PREF_KEY_SCORE_3";
    public static final String PREF_KEY_SCORE_4 = "PREF_KEY_SCORE_4";
    public static final String PREF_KEY_SCORE_5 = "PREF_KEY_SCORE_5";
    public static final String PREF_KEY_FIRSTNAME_1 = "PREF_KEY_FIRSTNAME_1";
    public static final String PREF_KEY_FIRSTNAME_2 = "PREF_KEY_FIRSTNAME_2";
    public static final String PREF_KEY_FIRSTNAME_3 = "PREF_KEY_FIRSTNAME_3";
    public static final String PREF_KEY_FIRSTNAME_4 = "PREF_KEY_FIRSTNAME_4";
    public static final String PREF_KEY_FIRSTNAME_5 = "PREF_KEY_FIRSTNAME_5";

    // add history best score method with List of SharedPreferences.
    public void addBestScore(String firstName, int score) {

        List<Integer> bestScore = new ArrayList<>(5);
        List<String> bestPlayer = new ArrayList<>(5);

        bestScore.add(mPreferences.getInt(PREF_KEY_SCORE_1, -1));
        bestScore.add(mPreferences.getInt(PREF_KEY_SCORE_2, -1));
        bestScore.add(mPreferences.getInt(PREF_KEY_SCORE_3, -1));
        bestScore.add(mPreferences.getInt(PREF_KEY_SCORE_4, -1));
        bestScore.add(mPreferences.getInt(PREF_KEY_SCORE_5, -1));

        bestPlayer.add(mPreferences.getString(PREF_KEY_FIRSTNAME_1, null));
        bestPlayer.add(mPreferences.getString(PREF_KEY_FIRSTNAME_2, null));
        bestPlayer.add(mPreferences.getString(PREF_KEY_FIRSTNAME_3, null));
        bestPlayer.add(mPreferences.getString(PREF_KEY_FIRSTNAME_4, null));
        bestPlayer.add(mPreferences.getString(PREF_KEY_FIRSTNAME_5, null));


        for (int i = 0; i < bestScore.size(); i++) {


            if (bestScore.get(i) < score) {

                bestScore.add(i, score);
                bestPlayer.add(i, firstName);


                break;
            }

        }
        mPreferences.edit().putString(PREF_KEY_FIRSTNAME_1, bestPlayer.get(0))
                .putString(PREF_KEY_FIRSTNAME_2, bestPlayer.get(1))
                .putString(PREF_KEY_FIRSTNAME_3, bestPlayer.get(2))
                .putString(PREF_KEY_FIRSTNAME_4, bestPlayer.get(3))
                .putString(PREF_KEY_FIRSTNAME_5, bestPlayer.get(4))
                .putInt(PREF_KEY_SCORE_1, bestScore.get(0))
                .putInt(PREF_KEY_SCORE_2, bestScore.get(1))
                .putInt(PREF_KEY_SCORE_3, bestScore.get(2))
                .putInt(PREF_KEY_SCORE_4, bestScore.get(3))
                .putInt(PREF_KEY_SCORE_5, bestScore.get(4))
                .apply();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (GAME_ACTIVITY_REQUEST_CODE == requestCode && RESULT_OK == resultCode) {

            int score = data.getIntExtra(GameActivity.BUNDLE_EXTRA_SCORE, 0);

            // mPreferences.edit().putInt(PREF_KEY_SCORE, score).apply();
            addBestScore(mUser.getFirstname(), score);

            greetUser();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // initialize variables with find View by id

        mGreetingText = findViewById(R.id.activity_main_greeting_txt);
        mNameInput = findViewById(R.id.activity_main_name_input);
        mPlayButton = findViewById(R.id.activity_main_play_btn);
        mHistoryButton = findViewById(R.id.activity_main_history);

        // disable the button to wait for the input of name input.

        mPlayButton.setEnabled(false);

        // add Listner for name input.

        mNameInput.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Play Button Enable if name input has 1 character.
                mPlayButton.setEnabled(s.toString().length() != 0);

            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        // add Listner when use the Button.

        mPlayButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Save the user name.
                String firstName = mNameInput.getText().toString();

                // go in new activity


                Intent gameActivityIntent = new Intent(MainActivity.this, GameActivity.class);
                startActivityForResult(gameActivityIntent, GAME_ACTIVITY_REQUEST_CODE);


            }
        });

    }
}
