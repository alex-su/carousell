package com.alexsukharev.carouselltopics.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.support.annotation.NonNull;

import com.alexsukharev.carouselltopics.di.Components;
import com.alexsukharev.carouselltopics.model.Topic;
import com.alexsukharev.carouselltopics.repository.ITopicsRepository;
import com.alexsukharev.carouselltopics.util.DefaultMutableLiveData;

import java.util.List;

import javax.inject.Inject;

/**
 * Stores and manages the list of @{@link Topic} objects.
 * Extends @{@link ViewModel} to be lifecycle-aware and survive configuration changes.
 */

public class MainViewModel extends ViewModel {

    @Inject
    ITopicsRepository mTopicsRepository;

    /**
     * The visibility of the Add Topic dialog. Changes of this field will be reflected in the view.
     */
    public MutableLiveData<Boolean> addTopicDialogVisible = new DefaultMutableLiveData<>(false);

    public MainViewModel() {
        Components.getRepositoryComponent().inject(this);
    }

    public LiveData<List<Topic>> getTopics() {
        return mTopicsRepository.getTopics();
    }

    public void onUpvoteClicked(@NonNull final Topic topic) {
        topic.setScore(topic.getScore() + 1);
        // After this, LiveData will be updated and we will receive a new list of topics
        mTopicsRepository.updateTopic(topic);
    }

    public void onDownvoteClicked(@NonNull final Topic topic) {
        topic.setScore(topic.getScore() - 1);
        // After this, LiveData will be updated and we will receive a new list of topics
        mTopicsRepository.updateTopic(topic);
    }

    public void onAddTopicClicked() {
        // Reset the state
        addTopicDialogVisible.setValue(false);
        // Show dialog
        addTopicDialogVisible.setValue(true);
    }

}
