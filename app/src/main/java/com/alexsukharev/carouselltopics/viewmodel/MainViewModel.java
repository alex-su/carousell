package com.alexsukharev.carouselltopics.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.alexsukharev.carouselltopics.di.Components;
import com.alexsukharev.carouselltopics.model.Topic;
import com.alexsukharev.carouselltopics.repository.ITopicsRepository;

import java.util.List;

import javax.inject.Inject;

/**
 * Stores and manages the list of @{@link Topic} objects.
 * Extends @{@link ViewModel} to be lifecycle-aware and survive configuration changes.
 */

public class MainViewModel extends ViewModel {

    @Inject
    ITopicsRepository mTopicsRepository;

    public MainViewModel() {
        Components.getRepositoryComponent().inject(this);
    }

    public LiveData<List<Topic>> getTopics() {
        return mTopicsRepository.getTopics();
    }

    public void onUpvoteClicked(@NonNull final Topic topic) {
        topic.setVotes(topic.getVotes() + 1);
        mTopicsRepository.storeTopic(topic);
    }

    public void onDownvoteClicked(@NonNull final Topic topic) {
        topic.setVotes(topic.getVotes() - 1);
        mTopicsRepository.storeTopic(topic);
    }

    public void onAddTopicClicked() {

    }

}
