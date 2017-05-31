package com.alexsukharev.carouselltopics.ui.activities;

import android.arch.lifecycle.LifecycleActivity;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.alexsukharev.carouselltopics.R;
import com.alexsukharev.carouselltopics.databinding.ActivityMainBinding;
import com.alexsukharev.carouselltopics.ui.adapters.TopicsAdapter;
import com.alexsukharev.carouselltopics.ui.dialogs.AddTopicDialogFragment;
import com.alexsukharev.carouselltopics.viewmodel.MainViewModel;

public class MainActivity extends LifecycleActivity {

    private ActivityMainBinding mBinding;

    private TopicsAdapter mTopicsAdapter;

    private MainViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);

        // Returns a cached ViewModel or creates a new one
        mViewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        mBinding.setViewModel(mViewModel);

        initTopicList();
        observeAddTopicDialogVisibility();
    }

    private void initTopicList() {
        mTopicsAdapter = new TopicsAdapter(mViewModel);
        mBinding.recyclerView.setAdapter(mTopicsAdapter);
        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mBinding.recyclerView.setHasFixedSize(true);

        // Observe changes of the list of topics
        mViewModel.getTopics().observe(this, topics -> {
            // Update UI
            if (topics != null) {
                mTopicsAdapter.setData(topics);
            }
        });
    }

    private void observeAddTopicDialogVisibility() {
        mViewModel.addTopicDialogVisible.observe(this, isVisible -> {
            if (isVisible == null) {
                return;
            }
            if (isVisible && getSupportFragmentManager().findFragmentByTag(AddTopicDialogFragment.TAG) == null) {
                new AddTopicDialogFragment().show(getSupportFragmentManager(), AddTopicDialogFragment.TAG);
            } else if (!isVisible && getSupportFragmentManager().findFragmentByTag(AddTopicDialogFragment.TAG) != null) {
                ((AddTopicDialogFragment) getSupportFragmentManager().findFragmentByTag(AddTopicDialogFragment.TAG)).dismiss();
            }
        });
    }
}
