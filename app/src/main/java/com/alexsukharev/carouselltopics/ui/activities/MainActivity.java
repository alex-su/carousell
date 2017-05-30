package com.alexsukharev.carouselltopics.ui.activities;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;

import com.alexsukharev.carouselltopics.R;
import com.alexsukharev.carouselltopics.databinding.ActivityMainBinding;
import com.alexsukharev.carouselltopics.ui.adapters.TopicsAdapter;
import com.alexsukharev.carouselltopics.viewmodel.MainViewModel;

public class MainActivity extends LifecycleActivity {

    private ActivityMainBinding mBinding;

    private TopicsAdapter mTopicsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Returns a cached ViewModel or creates a new one
        final MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        initTopicList(viewModel);
    }

    private void initTopicList(@NonNull final MainViewModel viewModel) {
        mTopicsAdapter = new TopicsAdapter(viewModel);
        mBinding.recyclerView.setAdapter(mTopicsAdapter);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));

        // Observe changes of the list of topics
        viewModel.getTopics().observe(this, topics -> {
            // Update UI
            if (topics != null) {
                mTopicsAdapter.setData(topics);
            }
        });
    }
}
