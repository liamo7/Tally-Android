package com.loh.tally.ui.chat.list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.loh.tally.R;
import com.loh.tally.domain.model.Module;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * File: ChatListViewHolder.java
 * Date: 11/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */

public class ChatListViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.container) View container;
    @BindView(R.id.name) TextView name;
    @BindView(R.id.lastMessage) TextView lastMessage;
    @BindView(R.id.time) TextView time;

    public ChatListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Module module) {
        String msg = "Hey guys, last message sent";
        String timeMsg = "2 mins ago";

        name.setText(module.getName());
        lastMessage.setText(container.getContext().getString(R.string.chat_list_last_message, msg));
        time.setText(timeMsg);
    }
}
