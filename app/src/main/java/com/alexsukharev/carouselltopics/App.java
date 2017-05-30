package com.alexsukharev.carouselltopics;

import android.app.Application;

import com.alexsukharev.carouselltopics.di.component.DaggerRepositoryComponent;
import com.alexsukharev.carouselltopics.di.component.RepositoryComponent;

public class App extends Application {

    private RepositoryComponent mRepositoryComponent;

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public RepositoryComponent getRepositoryComponent() {
        if (mRepositoryComponent == null) {
            mRepositoryComponent = DaggerRepositoryComponent.builder().build();
        }
        return mRepositoryComponent;
    }
}
