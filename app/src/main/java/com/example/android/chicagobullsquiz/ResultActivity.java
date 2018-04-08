package com.example.android.chicagobullsquiz;

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
    Score score;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        scoreTextView = (TextView)findViewById(R.id.score);
        score = new Score();

        scoreTextView.setText(String.valueOf(score.getScore()));
    }

//    private String setScore() {
//        return numberOfPoints.toString();
//    }
}
