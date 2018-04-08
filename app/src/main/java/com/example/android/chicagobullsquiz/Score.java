package com.example.android.chicagobullsquiz;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Adam Poukar on 7.4.18.
 */

public class Score {

    private AtomicInteger score;

    public Score() {
        score = new AtomicInteger();
    }

    public void incrementScore() {
        score.getAndIncrement();
    }

    public int getScore() {
        return score.get();
    }
}
