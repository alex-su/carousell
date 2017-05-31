package com.alexsukharev.carouselltopics.di.component;

import com.alexsukharev.carouselltopics.di.module.RepositoryModule;
import com.alexsukharev.carouselltopics.viewmodel.AddTopicViewModel;
import com.alexsukharev.carouselltopics.viewmodel.MainViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = RepositoryModule.class)
public interface RepositoryComponent {

    void inject(MainViewModel viewModel);

    void inject(AddTopicViewModel viewModel);

}