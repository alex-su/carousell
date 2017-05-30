package com.alexsukharev.carouselltopics.repository;

import android.annotation.SuppressLint;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.alexsukharev.carouselltopics.model.Topic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Implements the repository of topics with in-memory storage.
 */

public class TopicsRepository implements ITopicsRepository {

    private static final int ITEMS_LIMIT = 20;

    /*
      Using HashMap for faster search by id.
      Not using SparseArray, because HashMap is a Java collection whose values can be sorted using Collections.sort().
     */
    @SuppressLint("UseSparseArrays")
    private HashMap<Integer, Topic> mTopics = new HashMap<>();

    /*
      Keeping a reference to MutableLiveData for further updating
     */
    private MutableLiveData<List<Topic>> mCachedLiveData;

    /**
     * Get the most upvoted topics.
     * @return LiveData with a list of max. 20 topics
     */
    @Override
    @NonNull
    public LiveData<List<Topic>> getTopics() {
        if (mCachedLiveData == null) {
            mCachedLiveData = new MutableLiveData<>();
            refreshLiveData(mCachedLiveData);
        }
        return mCachedLiveData;
    }

    private void refreshLiveData(@NonNull final MutableLiveData<List<Topic>> liveData) {
        final List<Topic> topicList = new ArrayList<>(mTopics.values());
        Collections.sort(topicList);
        liveData.setValue(mTopics.isEmpty() ? new ArrayList<>() : topicList.subList(0, Math.min(mTopics.size(), ITEMS_LIMIT)));
    }

    /**
     * Updates the topic in the storage and refreshes previously returned LiveData, if there is any.
     * All observers of mCachedLiveData will be notified of the change.
     * @param topic The object to store
     */
    @Override
    public void storeTopic(@NonNull final Topic topic) {
        mTopics.put(topic.getId(), topic);
        if (mCachedLiveData != null) {
            refreshLiveData(mCachedLiveData);
        }
    }

}
