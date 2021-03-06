package com.example.android.chicagobullsquiz;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import io.github.krtkush.lineartimer.LinearTimer;
import io.github.krtkush.lineartimer.LinearTimerView;

public class QuestionActivity extends AppCompatActivity {

    List<Integer> drawnQuestions;
    String[] answers;
    QuestionManager questionManager;
    CountDownTimer countDown;
    TextView questionTextView, timerTextView, questionCounterTextView;
    Button answer0, answer1, answer2, answer3;
    LinearTimerView timerProgressBar;
    LinearTimer linearTimer;
    byte clickCount;
    private int questionIndex, numberOfQuestions, score;
    View.OnClickListener onClickListener = new View.OnClickListener() {
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
            moveOn();

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        drawnQuestions = new ArrayList<>();
        questionManager = new QuestionManager();
        countDown = setCountDownTimer();
        questionTextView = findViewById(R.id.question);
        timerTextView = findViewById(R.id.question_timer);
        questionCounterTextView = findViewById(R.id.question_counter_view);
        answer0 = findViewById(R.id.answer_0);
        answer1 = findViewById(R.id.answer_1);
        answer2 = findViewById(R.id.answer_2);
        answer3 = findViewById(R.id.answer_3);
        timerProgressBar = findViewById(R.id.timer_progress_bar);
        linearTimer = setUpLinearTimer();
        questionIndex = 0;
        numberOfQuestions = 6;
        clickCount = 0;
        score = 0;

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
        setQuestionCounterTextView();
        countDown.start();
        linearTimer.startTimer();

    }

    //Changes shown question
    private void changeQuestion() {
        questionIndex = drawnQuestions.get(clickCount);
        answers = questionManager.getAnswers(questionIndex);
        questionTextView.setText(questionManager.getQuestion(questionIndex));
        answer0.setText(answers[0]);
        answer1.setText(answers[1]);
        answer2.setText(answers[2]);
        answer3.setText(answers[3]);
    }

    //Checks if the given answer is correct
    private void checkAnswer(int questionIndex, String answer) {
        if (questionManager.isAnswerCorrect(questionIndex, answer))
            score++;
    }


    //Checks if it was last question
    private void isItOver() {
        Intent intent = new Intent(QuestionActivity.this, ResultActivity.class)
                .putExtra("score", score);
        if (numberOfQuestions == clickCount)
            startActivity(intent);
    }

    //Sets CountDownTimer
    private CountDownTimer setCountDownTimer() {
        return new CountDownTimer(15000, 1000) {

            public void onTick(long millisUntilFinished) {
                timerTextView.setText(String.valueOf(millisUntilFinished / 1000));
            }

            public void onFinish() {
                moveOn();
            }
        };
    }

    //Restarts Circle progress view
    private void restartCountDownTimer(CountDownTimer countDownTimer) {
        countDownTimer.cancel();
        countDownTimer.start();
    }

    //Group of steps which should be done after every answer
    private void moveOn() {
        clickCount++;
        isItOver();
        changeQuestion();
        setQuestionCounterTextView();
        restartCountDownTimer(countDown);
        linearTimer.restartTimer();
    }

    //Creates Circle progress view
    private LinearTimer setUpLinearTimer() {
        return new LinearTimer.Builder()
                .linearTimerView(timerProgressBar)
                .duration(15000)
                .build();
    }


    // Shows how many questions has been answered
    private void setQuestionCounterTextView() {
        StringBuilder questionCounter = new StringBuilder()
                .append(clickCount + 1)
                .append("/")
                .append(numberOfQuestions);
        questionCounterTextView.setText(questionCounter);
    }
}
