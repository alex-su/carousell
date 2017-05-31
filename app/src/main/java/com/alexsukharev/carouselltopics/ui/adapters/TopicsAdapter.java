package com.alexsukharev.carouselltopics.ui.adapters;

import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.alexsukharev.carouselltopics.R;
import com.alexsukharev.carouselltopics.databinding.TopicItemBinding;
import com.alexsukharev.carouselltopics.model.Topic;
import com.alexsukharev.carouselltopics.ui.adapters.viewholder.TopicViewHolder;
import com.alexsukharev.carouselltopics.viewmodel.MainViewModel;

import java.util.ArrayList;
import java.util.List;

public class TopicsAdapter extends RecyclerView.Adapter<TopicViewHolder> {

    private List<Topic> mTopicList = new ArrayList<>();
    private MainViewModel mViewModel;

    public TopicsAdapter(@NonNull final MainViewModel viewModel) {
        super();
        setHasStableIds(true);
        mViewModel = viewModel;
    }

    @Override
    public TopicViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        final TopicItemBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.topic_item, parent, false);
        binding.setViewModel(mViewModel);
        return new TopicViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(final TopicViewHolder holder, final int position) {
        holder.bind(mTopicList.get(position));
    }

    @Override
    public int getItemCount() {
        return mTopicList.size();
    }

    @Override
    public long getItemId(final int position) {
        return mTopicList.get(position).getId();
    }

    public void setData(@NonNull final List<Topic> topicList) {
        mTopicList.clear();
        mTopicList.addAll(topicList);
        notifyDataSetChanged();
    }
}
