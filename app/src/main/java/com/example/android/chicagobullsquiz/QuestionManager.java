package com.example.android.chicagobullsquiz;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.IntStream;

/**
 * Created by Adam Poukar on 13.3.18.
 */

public class QuestionManager {

    private Question[] mQuestion;
    private int questionCount;

    public QuestionManager() {
        mQuestion = new Question[20];
        questionCount = 0;
    }

    /*
     * Randomizes the order of answers
     * Uses Fisher-Yates algorithm
     */
    private static void shuffleArray(String[] array) {
        int arrayLength = array.length;
        Random random = new Random();
        for (int i = 0; i < arrayLength; i++) {
            int randomIndex = i + random.nextInt(arrayLength - i);
            String randomElement = array[randomIndex];
            array[randomIndex] = array[i];
            array[i] = randomElement;
        }
    }

    /*
     * Adds question to the question pool
     */
    public void addQuestion(Question question) {
        mQuestion[questionCount] = question;
        questionCount++;
    }

    /*
     * Returns number of questions in the pool
     */
    public int getQuestionCount() {
        return questionCount;
    }

    /*
     * Returns question according to question index
     * (number of the question array object)
     */
    public String getQuestion(int questionIndex) {
        return mQuestion[questionIndex].getQuestion();
    }

    /*
     * Returns answers of the particular question object.
     * Answers are in the randomized order.
     */
    public String[] getAnswers(int questionIndex) {
        String[] answers;
        answers = new String[]{mQuestion[questionIndex].getCorrectAnswer(),
                mQuestion[questionIndex].getWrongAnswers()[0],
                mQuestion[questionIndex].getWrongAnswers()[1],
                mQuestion[questionIndex].getWrongAnswers()[2]};
        shuffleArray(answers);
        return answers;
    }

    /*
     * Checks if the given answer is correct
     */
    public boolean isAnswerCorrect(int questionIndex, String answer) {
        return mQuestion[questionIndex].getCorrectAnswer().equals(answer);
    }

    /*
     * Generates random numbers from 0 to number of questions
     * in the question pool
     */
    private int drawRandomQuestion() {
        return ((int) Math.floor(Math.random() * questionCount));
    }

    /*
     * Creates a list of the question indexes,
     * which are the questions that are later gonna be asked.
     * Parameter numberOfQuestions determines how many questions
     * are gonna be drawn.
     */
    public void createListOfChosenQuestions(int numberOfQuestions, List<Integer> nameOfTheList) {
        int i = 0;
        do {
            int randomQuestionNumber = drawRandomQuestion();
            if (!nameOfTheList.contains(randomQuestionNumber)) {
                nameOfTheList.add(randomQuestionNumber);
                i++; }
        }while (i < numberOfQuestions);
    }
}
