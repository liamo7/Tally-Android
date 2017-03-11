package com.loh.tally.ui.presentations.list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.loh.tally.R;
import com.loh.tally.domain.model.Presentation;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * File: PresentationListViewHolder.java
 * Date: 11/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */
public class PresentationListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.container) View container;
    @BindView(R.id.name) TextView name;
    @BindView(R.id.numPolls) TextView numPolls;

    public PresentationListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Presentation model, PresentationListAdapter.OnPresentationItemClickListener listener) {
        name.setText(model.getName());
        numPolls.setText(itemView.getContext().getString(R.string.number_of_polls, String.valueOf(model.getNumPolls())));

        container.setOnClickListener(v -> {
            if (listener != null) {
                listener.onPresentationClicked(model);
            }
        });

    }
}