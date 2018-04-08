package com.example.android.chicagobullsquiz;

/**
 * Created by Adam Poukar on 13.3.18.
 */

public class Question {

//    private final byte mQuestionID;
    private final String mQuestion;
    private final String mCorrectAnswer;
    private final String mOption1;
    private final String mOption2;
    private final String mOption3;

//    public Question(String question, String correctAnswer, String option1, String option2, String option3) {
//        mQuestion = question;
//        mCorrectAnswer = correctAnswer;
//        mOption1 = option1;
//        mOption2 = option2;
//        mOption3 = option3;
//    }

    public Question(String[] info) {
        mQuestion = info[0];
        mCorrectAnswer = info[1];
        mOption1 = info[2];
        mOption2 = info[3];
        mOption3 = info[4];
    }

    public String getQuestion() {
        return mQuestion;
    }

    public String getCorrectAnswer() {
        return mCorrectAnswer;
    }

    public String[] getWrongAnswers() {
        return new String[] {mOption1, mOption2, mOption3};
    }
}
