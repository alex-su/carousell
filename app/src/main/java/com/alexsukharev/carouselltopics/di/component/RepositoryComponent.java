package com.alexsukharev.carouselltopics.di.component;

import com.alexsukharev.carouselltopics.viewmodel.MainViewModel;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component
public interface RepositoryComponent {

    void inject(MainViewModel viewModel);

}