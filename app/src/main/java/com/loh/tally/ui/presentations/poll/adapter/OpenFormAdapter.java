package com.loh.tally.ui.presentations.poll.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loh.tally.R;
import com.loh.tally.ui.base.dagger.scope.ViewScope;

import java.util.Collections;
import java.util.List;

import javax.inject.Inject;

/**
 * File: OpenFormAdapter.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */
@ViewScope
public class OpenFormAdapter extends RecyclerView.Adapter<OpenFormViewHolder> {

    private List<String> responses = Collections.emptyList();

    @Inject
    public OpenFormAdapter() {

    }

    @Override
    public OpenFormViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View layout = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_open_form, parent, false);
        return new OpenFormViewHolder(layout);
    }

    @Override
    public void onBindViewHolder(OpenFormViewHolder holder, int position) {
        final String choice = responses.get(position);
        holder.bind(choice);
    }

    @Override
    public int getItemCount() {
        return responses.size();
    }

    public void setResponses(List<String> choices) {
        this.responses = choices;
        notifyDataSetChanged();
    }
}
