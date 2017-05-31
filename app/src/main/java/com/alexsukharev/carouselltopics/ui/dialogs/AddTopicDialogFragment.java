package com.alexsukharev.carouselltopics.ui.dialogs;

import android.app.Dialog;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.Observable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;

import com.alexsukharev.carouselltopics.databinding.DialogAddTopicBinding;
import com.alexsukharev.carouselltopics.viewmodel.AddTopicViewModel;

public class AddTopicDialogFragment extends DialogFragment {

    public static final String TAG = AddTopicDialogFragment.class.getSimpleName();

    private AddTopicViewModel mViewModel;

    /**
     * Dismisses the dialog when the field is set to false.
     */
    private Observable.OnPropertyChangedCallback mOnVisibilityChangedCallback = new Observable.OnPropertyChangedCallback() {
        @Override
        public void onPropertyChanged(final Observable observable, final int i) {
            if (!mViewModel.isVisible.get()) {
                dismiss();
            }
        }
    };

    @Override
    public void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Returns a cached ViewModel or creates a new one
        mViewModel = ViewModelProviders.of(this).get(AddTopicViewModel.class);
        mViewModel.isVisible.addOnPropertyChangedCallback(mOnVisibilityChangedCallback);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        // Since ViewModel survives configuration changes, we need to unsubscribe from updates of the ObservableField to prevent leaking of this Fragment instance
        mViewModel.isVisible.removeOnPropertyChangedCallback(mOnVisibilityChangedCallback);
    }

    @NonNull
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final DialogAddTopicBinding binding = DialogAddTopicBinding.inflate(LayoutInflater.from(getContext()));
        binding.setViewModel(mViewModel);
        return new AlertDialog.Builder(getContext()).setView(binding.getRoot()).create();
    }

}
