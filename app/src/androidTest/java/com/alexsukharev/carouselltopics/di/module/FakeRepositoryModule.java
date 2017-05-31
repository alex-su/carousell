

package com.alexsukharev.carouselltopics.di.module;

import com.alexsukharev.carouselltopics.di.fake.FakeTopicsRepository;
import com.alexsukharev.carouselltopics.repository.ITopicsRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

@Module
public class FakeRepositoryModule extends RepositoryModule {

    @Provides
    @Singleton
    @Override
    ITopicsRepository providesTopicsRepository() {
        return new FakeTopicsRepository();
    }
}