package com.loh.tally.ui.presentations.list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.loh.tally.R;
import com.loh.tally.domain.model.Presentation;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * File: PresentationListViewHolder.java
 * Date: 11/03/2017
 * Created By: Liam O'Hanlon
 */
public class PresentationListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.container) View container;
    @BindView(R.id.name) TextView name;
    @BindView(R.id.numPolls) TextView numPolls;
    @BindView(R.id.circle) ImageView circle;

    public PresentationListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Presentation model, PresentationListAdapter.OnPresentationItemClickListener listener) {
        String firstLetter = model.getName().substring(0, 1);
        TextDrawable textDrawable = TextDrawable.builder().buildRound(firstLetter, ColorGenerator.MATERIAL.getRandomColor());
        circle.setImageDrawable(textDrawable);

        name.setText(model.getName());
        numPolls.setText(itemView.getContext().getString(R.string.number_of_polls, String.valueOf(model.getNumPolls())));

        container.setOnClickListener(v -> {
            if (listener != null) {
                listener.onPresentationClicked(model);
            }
        });

    }
}