
package com.alexsukharev.carouselltopics.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.ObservableField;

import com.alexsukharev.carouselltopics.di.Components;
import com.alexsukharev.carouselltopics.repository.ITopicsRepository;
import com.alexsukharev.carouselltopics.util.DefaultMutableLiveData;

import javax.inject.Inject;

/**
 * Manages the state of Add Topic view.
 * Extends @{@link ViewModel} to be lifecycle-aware and survive configuration changes.
 */

public class AddTopicViewModel extends ViewModel {

    @Inject
    ITopicsRepository mTopicsRepository;

    /**
     * Visibility of the view
     */
    private MutableLiveData<Boolean> mVisibility = new DefaultMutableLiveData<>(true);

    /**
     * Corresponds to the text entered in the input field.
     * Changes in the view will update this field, and changes of this field will update the view.
     */
    public ObservableField<String> newTopicText = new ObservableField<>("");

    public AddTopicViewModel() {
        Components.getRepositoryComponent().inject(this);
    }

    public LiveData<Boolean> getVisibility() {
        return mVisibility;
    }

    public void onSubmitTopicClicked() {
        if (!newTopicText.get().isEmpty() && newTopicText.get().length() <= 255) {
            // After creating a topic, all observers will be notified of it
            mTopicsRepository.createTopic(newTopicText.get());
            mVisibility.setValue(false);
        }
    }

    public void onDiscardTopicClicked() {
        mVisibility.setValue(false);
    }

}
