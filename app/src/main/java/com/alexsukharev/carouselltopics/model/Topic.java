package com.alexsukharev.carouselltopics.model;

import android.support.annotation.NonNull;

/**
 * Created by alexandersukharev on 30/05/2017.
 */

public class Topic implements Comparable<Topic> {

    private int id;
    private String name;
    private int votes;

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

    public int getVotes() {
        return votes;
    }

    public void setVotes(final int votes) {
        this.votes = votes;
    }

    @Override
    public int compareTo(@NonNull final Topic topic) {
        if (getVotes() > topic.getVotes()) {
            return 1;
        } else if (getVotes() < topic.getVotes()) {
            return -1;
        } else {
            return 0;
        }
    }

}
