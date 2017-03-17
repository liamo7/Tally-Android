package com.loh.tally.ui.chat.detail.adapter;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.loh.tally.R;
import com.loh.tally.domain.model.ChatMessage;
import com.loh.tally.ui.base.dagger.scope.ViewScope;

import javax.inject.Inject;

/**
 * File: ChatDetailAdapter.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
 */
@ViewScope
public class ChatDetailAdapter extends FirebaseRecyclerAdapter<ChatMessage, ChatDetailViewHolder> {

    @Inject
    public ChatDetailAdapter(DatabaseReference ref) {
        this(ChatMessage.class, R.layout.item_chat_message, ChatDetailViewHolder.class, ref);
    }

    /**
     * @param modelClass      Firebase will marshall the data at a location into an instance of a class that you provide
     * @param modelLayout     This is the layout used to represent a single item in the list. You will be responsible for populating an
     *                        instance of the corresponding view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     * @param ref             The Firebase location to watch for data changes. Can also be a slice of a location, using some
     *                        combination of {@code limit()}, {@code startAt()}, and {@code endAt()}.
     */
    private ChatDetailAdapter(Class<ChatMessage> modelClass, int modelLayout, Class<ChatDetailViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    protected void populateViewHolder(ChatDetailViewHolder viewHolder, ChatMessage model, int position) {
        viewHolder.bind(model);
    }
}
