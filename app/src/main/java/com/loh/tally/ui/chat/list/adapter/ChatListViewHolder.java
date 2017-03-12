package com.loh.tally.ui.chat.list.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.amulyakhare.textdrawable.util.ColorGenerator;
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
    @BindView(R.id.circle) ImageView circle;

    private String lastMessageStr;

    public ChatListViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(Module module, ChatListAdapter.OnChatListItemClickedListener listener) {
        String firstLetter = module.getName().substring(0, 1);
        TextDrawable textDrawable = TextDrawable.builder().buildRound(firstLetter, ColorGenerator.MATERIAL.getRandomColor());
        circle.setImageDrawable(textDrawable);
        name.setText(module.getName());

        container.setOnClickListener(v -> {
            if (listener != null) {
                listener.onChatItemClicked(module);
            }
        });
    }

    public void bind(ChatMessage message) {
        String msg = message.getMessage();
        String timeMsg = PrettyDateUtil.getPretty(message.getDate());

        // TODO: 11/03/2017 Fix container.getContext() leaking
        lastMessage.setText(container.getContext().getString(R.string.chat_list_last_message, msg));
        time.setText(timeMsg);
    }
}
