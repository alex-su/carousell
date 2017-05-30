package com.alexsukharev.carouselltopics.ui.activities;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;

import com.alexsukharev.carouselltopics.R;
import com.alexsukharev.carouselltopics.viewmodel.MainViewModel;

public class MainActivity extends LifecycleActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Returns a cached ViewModel or creates a new one
        final MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        // Observe changes of the list of topics
        viewModel.getTopics().observe(this, topics -> {
            // Update UI
        });
    }
}
