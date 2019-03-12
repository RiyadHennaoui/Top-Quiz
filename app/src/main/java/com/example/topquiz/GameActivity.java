package com.example.topquiz;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView mTextViewQuestion;
    private Button mButtonAnswer1;
    private Button mButtonAnswer2;
    private Button mButtonAnswer3;
    private Button mButtonAnswer4;
    private int mScore;
    private QuestionBank mQuestionBank;
    private Question mCurrentQuestion;
    private int mNumberOfQuestions;
    private boolean mEnableTouchEvents;
    public static final String BUNDLE_EXTRA_SCORE = "BUNDLE_EXTRA_SCORE";
    public static final String BUNDLE_STATE_SCORE = "currentScore";
    public static final String BUNDLE_STATE_QUESTION = "currentQuestion";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        mTextViewQuestion = findViewById(R.id.game_activity_question);
        mButtonAnswer1 = findViewById(R.id.game_activity_btn1);
        mButtonAnswer2 = findViewById(R.id.game_activity_btn2);
        mButtonAnswer3 = findViewById(R.id.game_activity_btn3);
        mButtonAnswer4 = findViewById(R.id.game_activity_btn4);

        if (savedInstanceState != null) {
            mScore = savedInstanceState.getInt(BUNDLE_STATE_SCORE);
            mNumberOfQuestions = savedInstanceState.getInt(BUNDLE_STATE_QUESTION);
        } else {

            mScore = 0;
            mNumberOfQuestions = 4;

        }
        mEnableTouchEvents = true;

        mQuestionBank = createQuestionBank();
        mCurrentQuestion = mQuestionBank.getQuestion();
        displayQuestion(mCurrentQuestion);

    }


    // Created Question bank !

    private QuestionBank createQuestionBank() {

        Question question1 = new Question("Le nom du président",
                Arrays.asList("Macron",
                        "Hollande",
                        "Hulot",
                        "Chirac"),
                0);
        Question question2 = new Question("La capitale française",
                Arrays.asList("Madrid",
                        "Rome",
                        "Paris",
                        "Londre"),
                2);
        Question question3 = new Question("Quel acteur a joué james bond",
                Arrays.asList("Kit Harington",
                        "Sean Connery",
                        "Jonny Depp",
                        "Clint Eastwood"),
                1);
        Question question4 = new Question("En quelle année est tombé le mur de berlin",
                Arrays.asList("1983",
                        "1979",
                        "1969",
                        "1989"),
                3);
        Question question5 = new Question("la début de la première guerre mondiale",
                Arrays.asList("1914",
                        "1915",
                        "1916",
                        "1918"),
                0);
        Question question6 = new Question("Qui a réalisé Alien",
                Arrays.asList("Martin Scorsese",
                        "Steven Spielberg",
                        "Ridley Scott",
                        "Luc Besson"),
                2);
        Question question7 = new Question("En quelle année Steven Spielberg a fini ses études",
                Arrays.asList("1962",
                        "1982",
                        "1992",
                        "2002"),
                3);
        Question question8 = new Question("en quelle année a débuté l'os Android",
                Arrays.asList("2006",
                        "2007",
                        "2008",
                        "2009"),
                2);

        return new QuestionBank(Arrays.asList(question1, question2, question3, question4,
                question5, question6, question7, question8));
    }


    // methode to listen and show question in button and TextView.
    private void displayQuestion(Question question) {

        mTextViewQuestion.setText(question.getQuestion());
        mButtonAnswer1.setText(question.getChoise().get(0));
        mButtonAnswer1.setOnClickListener(this);
        mButtonAnswer2.setText(question.getChoise().get(1));
        mButtonAnswer2.setOnClickListener(this);
        mButtonAnswer3.setText(question.getChoise().get(2));
        mButtonAnswer3.setOnClickListener(this);
        mButtonAnswer4.setText(question.getChoise().get(3));
        mButtonAnswer4.setOnClickListener(this);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        return mEnableTouchEvents && super.dispatchTouchEvent(ev);
    }


    // save score and current question.
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_STATE_SCORE, mScore);
        outState.putInt(BUNDLE_STATE_QUESTION, mNumberOfQuestions);

        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        int index;

        switch (v.getId()) {

            case R.id.game_activity_btn1: {
                index = 0;
                break;
            }

            case R.id.game_activity_btn2: {
                index = 1;
                break;
            }

            case R.id.game_activity_btn3: {
                index = 2;
                break;
            }

            case R.id.game_activity_btn4: {
                index = 3;
                break;
            }

            default:
                index = -1;
                break;
        }
        if (index == mCurrentQuestion.getAnswerIndex()) {
            Toast.makeText(this, "Correct", Toast.LENGTH_SHORT).show();
            mScore++;
        } else {
            Toast.makeText(this, "Incorrect", Toast.LENGTH_SHORT).show();
        }
        mEnableTouchEvents = false;


        // Passe à la question suivante quand même
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mEnableTouchEvents = true;
                if (--mNumberOfQuestions == 0) {
                    endGame();
                } else {
                    mCurrentQuestion = mQuestionBank.getQuestion();
                    displayQuestion(mCurrentQuestion);
                }
            }
        }, 2000);


    }

    // Dialog box created when game is end with current score
    private void endGame() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Well done !")
                .setMessage("Your score is : " + mScore)
                .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent();
                        intent.putExtra(BUNDLE_EXTRA_SCORE, mScore);
                        setResult(RESULT_OK, intent);
                        finish();
                    }
                })
                .create()
                .show();
    }
}
