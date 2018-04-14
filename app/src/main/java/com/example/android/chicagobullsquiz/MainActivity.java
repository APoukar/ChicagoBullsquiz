package com.example.android.chicagobullsquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //Button that starts the game
        Button questionActivity = findViewById(R.id.question_activity);
        questionActivity.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent questionActivityIntent = new Intent(MainActivity.this, QuestionActivity.class);

                startActivity(questionActivityIntent);
            }
        });
    }
}
