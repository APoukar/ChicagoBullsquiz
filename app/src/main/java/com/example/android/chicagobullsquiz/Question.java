package com.example.android.chicagobullsquiz;

/**
 * Created by Adam Poukar on 13.3.18.
 */

public class Question {

    private final String mQuestion;
    private final String mCorrectAnswer;
    private final String mOption1;
    private final String mOption2;
    private final String mOption3;

    Question(String[] info) {
        mQuestion = info[0];
        mCorrectAnswer = info[1];
        mOption1 = info[2];
        mOption2 = info[3];
        mOption3 = info[4];
    }

    //Retrieves the question
    String getQuestion() {
        return mQuestion;
    }

    //Retrieves the correct answer
    String getCorrectAnswer() {
        return mCorrectAnswer;
    }

    //Retrieves three wrong answers
    String[] getWrongAnswers() {
        return new String[] {mOption1, mOption2, mOption3};
    }
}
