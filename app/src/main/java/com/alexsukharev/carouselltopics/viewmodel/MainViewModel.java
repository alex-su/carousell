package com.alexsukharev.carouselltopics.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
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

    /**
     * The visibility of the Add Topic dialog. Changes of this field will be reflected in the view.
     * Although being a LiveData, this field works like an action trigger. Setting it to "true" triggers the dialog to be displayed.
     */
    private MutableLiveData<Boolean> mAddTopicDialogVisibility = new MutableLiveData<>();

    public MainViewModel() {
        Components.getRepositoryComponent().inject(this);
    }

    public LiveData<List<Topic>> getTopics() {
        return mTopicsRepository.getTopics();
    }

    public LiveData<Boolean> getAddTopicDialogVisibility() {
        return mAddTopicDialogVisibility;
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
        // Show dialog
        mAddTopicDialogVisibility.setValue(true);
        // We don't want the dialog to be displayed again after the configuration change (FragmentManager will do that for us),
        // so the value should be set to back null
        mAddTopicDialogVisibility.setValue(null);
    }

}
