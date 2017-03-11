package com.loh.tally.ui.chat.list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.loh.tally.R;
import com.loh.tally.domain.model.ChatMessage;
import com.loh.tally.domain.model.Module;
import com.loh.tally.util.PrettyDateUtil;

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

    private String lastMessageStr;

    public ChatListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Module module) {
        name.setText(module.getName());
    }

    public void bind(ChatMessage message) {
        String msg = message.getMessage();
        String timeMsg = PrettyDateUtil.getPretty(message.getDate());

        // TODO: 11/03/2017 Fix container.getContext() leaking
        lastMessage.setText(container.getContext().getString(R.string.chat_list_last_message, msg));
        time.setText(timeMsg);
    }
}
