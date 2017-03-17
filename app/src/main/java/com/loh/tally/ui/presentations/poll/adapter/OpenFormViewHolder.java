package com.loh.tally.ui.presentations.poll.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.loh.tally.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * File: OpenFormViewHolder.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
 */
public class OpenFormViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.formMessage) TextView formMessage;

    public OpenFormViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(String message) {
        formMessage.setText(message);
    }
}
