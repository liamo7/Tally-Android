package com.loh.tally.ui.presentations.poll.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loh.tally.R;
import com.loh.tally.ui.base.dagger.scope.ViewScope;

import java.util.Collections;
import java.util.List;

/**
 * File: MultipleChoiceAdapter.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */
@ViewScope
public class MultipleChoiceAdapter extends RecyclerView.Adapter<MultipleChoiceViewHolder> {

    private List<String> responses = Collections.emptyList();
    private OnResponseClickListener listener;

    @ViewScope
    public MultipleChoiceAdapter() {

    }

    @Override
    public MultipleChoiceViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_multiple_choice, parent, false);
        return new MultipleChoiceViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(MultipleChoiceViewHolder holder, int position) {
        final String choice = responses.get(position);
        holder.bind(choice, position, listener);
    }

    @Override
    public int getItemCount() {
        return responses.size();
    }

    public void setOnResponseClickListener(OnResponseClickListener listener) {
        this.listener = listener;
    }

    public void setResponses(List<String> choices) {
        this.responses = choices;
        notifyDataSetChanged();
    }

    public interface OnResponseClickListener {
        void onResponseClicked(int position);
    }
}
