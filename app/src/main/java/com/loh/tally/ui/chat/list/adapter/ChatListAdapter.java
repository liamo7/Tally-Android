package com.loh.tally.ui.chat.list.adapter;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.Query;
import com.loh.tally.R;
import com.loh.tally.domain.model.Module;
import com.loh.tally.ui.base.dagger.scope.ViewScope;

import timber.log.Timber;

/**
 * File: ChatListAdapter.java
 * Date: 11/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */
@ViewScope
public class ChatListAdapter extends FirebaseRecyclerAdapter<Module, ChatListViewHolder> {

    public ChatListAdapter(Query ref) {
        this(Module.class, R.layout.item_chat_list, ChatListViewHolder.class, ref);
    }

    /**
     * @param modelClass      Firebase will marshall the data at a location into an instance of a class that you provide
     * @param modelLayout     This is the layout used to represent a single item in the list. You will be responsible for populating an
     *                        instance of the corresponding view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     * @param ref             The Firebase location to watch for data changes. Can also be a slice of a location, using some
     *                        combination of {@code limit()}, {@code startAt()}, and {@code endAt()}.
     */
    public ChatListAdapter(Class<Module> modelClass, int modelLayout, Class<ChatListViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    protected void populateViewHolder(ChatListViewHolder viewHolder, Module model, int position) {
        String userKey = this.getRef(position).getKey();
        Timber.d(userKey);
        viewHolder.bind(model);
    }
}
