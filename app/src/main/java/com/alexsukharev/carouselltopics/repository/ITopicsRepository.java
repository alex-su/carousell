package com.alexsukharev.carouselltopics.repository;

import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import com.alexsukharev.carouselltopics.model.Topic;

import java.util.List;

/**
 * Provides methods to work with collections of Topics.
 * An implementation is normally injected as a singleton.
 */

public interface ITopicsRepository {

    @NonNull
    LiveData<List<Topic>> getTopics();

    void updateTopic(@NonNull Topic topic);

    void createTopic(@NonNull String text);

}
