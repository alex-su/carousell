
package com.alexsukharev.carouselltopics.viewmodel;

import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;

import com.alexsukharev.carouselltopics.di.Components;
import com.alexsukharev.carouselltopics.model.Topic;
import com.alexsukharev.carouselltopics.repository.ITopicsRepository;

import javax.inject.Inject;

/**
 * Stores and manages the list of @{@link Topic} objects.
 * Extends @{@link ViewModel} to be lifecycle-aware and survive configuration changes.
 */

public class AddTopicViewModel extends ViewModel {

    @Inject
    ITopicsRepository mTopicsRepository;

    public ObservableBoolean isVisible = new ObservableBoolean(true);
    public ObservableField<String> newTopicText = new ObservableField<>("");

    public AddTopicViewModel() {
        Components.getRepositoryComponent().inject(this);
    }

    public void onSubmitTopicClicked() {
        if (!newTopicText.get().isEmpty() && newTopicText.get().length() <= 255) {
            mTopicsRepository.createTopic(newTopicText.get());
            isVisible.set(false);
        }
    }

    public void onDiscardTopicClicked() {
        isVisible.set(false);
    }

}
