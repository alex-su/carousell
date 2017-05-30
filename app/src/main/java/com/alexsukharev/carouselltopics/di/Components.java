package com.alexsukharev.carouselltopics.di;

import android.support.annotation.NonNull;

import com.alexsukharev.carouselltopics.di.component.DaggerRepositoryComponent;
import com.alexsukharev.carouselltopics.di.component.RepositoryComponent;

public class Components {

    private static RepositoryComponent mRepositoryComponent;

    public static RepositoryComponent getRepositoryComponent() {
        if (mRepositoryComponent == null) {
            mRepositoryComponent = DaggerRepositoryComponent.builder().build();
        }
        return mRepositoryComponent;
    }

    public static void setRepositoryComponent(@NonNull final RepositoryComponent repositoryComponent) {
        mRepositoryComponent = repositoryComponent;
    }

}
