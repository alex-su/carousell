package com.alexsukharev.carouselltopics.ui.dialogs;

import android.app.Dialog;
import android.arch.lifecycle.LifecycleRegistry;
import android.arch.lifecycle.LifecycleRegistryOwner;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import com.alexsukharev.carouselltopics.databinding.DialogAddTopicBinding;
import com.alexsukharev.carouselltopics.viewmodel.AddTopicViewModel;

public class AddTopicDialogFragment extends DialogFragment implements LifecycleRegistryOwner {

    public static final String TAG = AddTopicDialogFragment.class.getSimpleName();

    private AddTopicViewModel mViewModel;

    /*
      There's no standard LifecycleDialogFragment, so we have to keep it here until the Architecture Components library is updated
     */
    private LifecycleRegistry mLifecycleRegistry = new LifecycleRegistry(this);

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Returns a cached ViewModel or creates a new one
        mViewModel = ViewModelProviders.of(this).get(AddTopicViewModel.class);
        mViewModel.getVisibility().observe(this, isVisible -> {
            if (isVisible != null && !isVisible) {
                dismiss();
            }
        });
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final DialogAddTopicBinding binding = DialogAddTopicBinding.inflate(LayoutInflater.from(getContext()));
        binding.setViewModel(mViewModel);
        return new AlertDialog.Builder(getContext()).setView(binding.getRoot()).create();
    }

    @Override
    public LifecycleRegistry getLifecycle() {
        return mLifecycleRegistry;
    }
}
