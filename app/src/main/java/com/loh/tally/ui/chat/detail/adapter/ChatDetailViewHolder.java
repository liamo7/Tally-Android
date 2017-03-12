package com.loh.tally.ui.chat.detail.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.loh.tally.R;
import com.loh.tally.domain.model.ChatMessage;
import com.loh.tally.util.PrettyDateUtil;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * File: ChatDetailViewHolder.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */
public class ChatDetailViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.message) TextView message;
    @BindView(R.id.time) TextView time;

    public ChatDetailViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(ChatMessage chatMessage) {
        message.setText(chatMessage.getMessage());
        time.setText(PrettyDateUtil.getPretty(chatMessage.getDate()));
    }
}
