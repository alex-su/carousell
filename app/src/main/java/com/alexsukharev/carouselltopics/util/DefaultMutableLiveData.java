package com.alexsukharev.carouselltopics.util;

import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.Nullable;

/**
 * A subclass of MutableLiveData that accepts a default value in the constructor
 */
public class DefaultMutableLiveData<T> extends MutableLiveData<T> {

    public DefaultMutableLiveData(@Nullable final T defaultValue) {
        super();
        setValue(defaultValue);
    }

}
