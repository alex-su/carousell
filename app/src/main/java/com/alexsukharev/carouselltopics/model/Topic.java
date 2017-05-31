package com.alexsukharev.carouselltopics.model;

import android.support.annotation.NonNull;

public class Topic implements Comparable<Topic> {

    private int id;
    private String name;
    private int score;

    public int getId() {
        return id;
    }

    public void setId(final int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(final int score) {
        this.score = score;
    }

    @Override
    public int compareTo(@NonNull final Topic topic) {
        if (getScore() < topic.getScore()) {
            return 1;
        } else if (getScore() > topic.getScore()) {
            return -1;
        } else {
            if (getId() > topic.getId()) {
                return 1;
            } else if (getId() < topic.getId()) {
                return -1;
            } else {
                return 0;
            }
        }
    }

}
