package com.example.android.chicagobullsquiz;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class QuestionActivity extends AppCompatActivity {

    List<Integer> drawnQuestions;
    String[] answers;
    QuestionManager questionManager;
    TextView questionTextView;
    Button answer0, answer1, answer2, answer3;
    Score score;
    private int questionIndex, numberOfQuestions;
    byte clickCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        drawnQuestions = new ArrayList<>();
        questionManager = new QuestionManager();
        questionTextView = findViewById(R.id.question);
        answer0 = findViewById(R.id.answer_0);
        answer1 = findViewById(R.id.answer_1);
        answer2 = findViewById(R.id.answer_2);
        answer3 = findViewById(R.id.answer_3);
        score = new Score();
        questionIndex = 0;
        numberOfQuestions = 6;
        clickCount = 0;

        answer0.setOnClickListener(onClickListener);
        answer1.setOnClickListener(onClickListener);
        answer2.setOnClickListener(onClickListener);
        answer3.setOnClickListener(onClickListener);

        /*
         * Retrieves Question arrays from strings.xml
         * and adds them to the questionManager class
         * as objects
         */
        for (int i = 0; i < 19; ++i) {
            int id = (getApplicationContext().getResources().getIdentifier("question_" + i, "array", getPackageName()));
            Question questionArray = new Question(getApplicationContext().getResources().getStringArray(id));
            questionManager.addQuestion(questionArray);
        }

        questionManager.createListOfChosenQuestions(numberOfQuestions, drawnQuestions);

        changeQuestion();

        }

    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            String answer = null;
            switch (view.getId()) {
                case R.id.answer_0:
                    answer = answer0.getText().toString();
                    break;
                case R.id.answer_1:
                    answer = answer1.getText().toString();
                    break;
                case R.id.answer_2:
                    answer = answer2.getText().toString();
                    break;
                case R.id.answer_3:
                    answer = answer3.getText().toString();
                    break;
                default:
                    Log.v("Something wrong with: ", "onClickListener");
            }

            checkAnswer(questionIndex, answer);
            clickCount++;
            isItOver();
            changeQuestion();


        }
    };

    private void changeQuestion() {
        questionIndex = drawnQuestions.get(clickCount);
        answers = questionManager.getAnswers(questionIndex);
        questionTextView.setText(questionManager.getQuestion(questionIndex));
        answer0.setText(answers[0]);
        answer1.setText(answers[1]);
        answer2.setText(answers[2]);
        answer3.setText(answers[3]);
    }

    private void checkAnswer(int questionIndex,String answer) {
        if (questionManager.isAnswerCorrect(questionIndex, answer))
            score.incrementScore();
    }

    private void isItOver() {
        Intent intent = new Intent(QuestionActivity.this, ResultActivity.class);
        if (numberOfQuestions == clickCount)
            QuestionActivity.this.startActivity(intent);
    }
}
