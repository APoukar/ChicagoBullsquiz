package com.example.android.chicagobullsquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import static com.example.android.chicagobullsquiz.QuestionActivity.*;

public class ResultActivity extends AppCompatActivity {

    TextView scoreTextView;
    private int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);


        scoreTextView = findViewById(R.id.score_count);

        scoreTextView.setText(String.valueOf(setScore()));
    }

    private Integer setScore() {
        return getIntent().getIntExtra("score", score);
    }

    public void restartGame(View view) {
        Intent restartGame = new Intent(ResultActivity.this, QuestionActivity.class);
        startActivity(restartGame);
    }
}
