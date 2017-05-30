package com.alexsukharev.carouselltopics.di.module;

import com.alexsukharev.carouselltopics.repository.ITopicsRepository;
import com.alexsukharev.carouselltopics.repository.TopicsRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class RepositoryModule {

    @Provides
    @Singleton
    ITopicsRepository providesTopicsRepository() {
        return new TopicsRepository();
    }
}