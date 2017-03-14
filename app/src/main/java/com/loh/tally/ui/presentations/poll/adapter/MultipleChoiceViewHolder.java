package com.loh.tally.ui.presentations.poll.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
import com.loh.tally.R;
import com.loh.tally.ui.base.dagger.scope.ViewScope;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * File: MultipleChoiceViewHolder.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */
@ViewScope
public class MultipleChoiceViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.multipleChoiceContainer) View container;
    @BindView(R.id.responsePosition) ImageView responsePosition;
    @BindView(R.id.response) TextView response;

    @Inject
    public MultipleChoiceViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(String choice, int position, MultipleChoiceAdapter.OnResponseClickListener listener) {
        char letter = (char) (65 + position);
        int color = ColorGenerator.MATERIAL.getRandomColor();
        TextDrawable textDrawable = TextDrawable.builder().buildRound(String.valueOf(letter), color);
        responsePosition.setImageDrawable(textDrawable);
        response.setText(choice);

        container.setOnClickListener(v -> {
            if (listener != null) {
                listener.onResponseClicked(position);
            }
        });
    }
}
