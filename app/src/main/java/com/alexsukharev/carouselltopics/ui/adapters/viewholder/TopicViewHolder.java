package com.alexsukharev.carouselltopics.ui.adapters.viewholder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.alexsukharev.carouselltopics.databinding.TopicItemBinding;
import com.alexsukharev.carouselltopics.model.Topic;

public class TopicViewHolder extends RecyclerView.ViewHolder {

    private TopicItemBinding mBinding;

    public TopicViewHolder(@NonNull final TopicItemBinding binding) {
        super(binding.getRoot());
        mBinding = binding;
    }

    public void bind(@NonNull final Topic topic) {
        mBinding.setTopic(topic);
    }
}
