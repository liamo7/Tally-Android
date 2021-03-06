package com.loh.tally.ui.chat.list.adapter;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.loh.tally.R;
import com.loh.tally.domain.model.ChatMessage;
import com.loh.tally.domain.model.Module;
import com.loh.tally.ui.base.EmptyListStateListener;
import com.loh.tally.ui.base.dagger.scope.ViewScope;

import javax.inject.Inject;

/**
 * File: ChatListAdapter.java
 * Date: 11/03/2017
 * Created By: Liam O'Hanlon
 */
@ViewScope
public class ChatListAdapter extends FirebaseRecyclerAdapter<Module, ChatListViewHolder> {

    private OnChatListItemClickedListener listener;
    private EmptyListStateListener emptyListStateListener;

    private DatabaseReference createdRef;
    private DatabaseReference chatRef;

    @Inject
    public ChatListAdapter(Query ref, DatabaseReference createdRef, DatabaseReference chatRef) {
        this(Module.class, R.layout.item_chat_list, ChatListViewHolder.class, ref);
        this.createdRef = createdRef;
        this.chatRef = chatRef;
    }

    /**
     * @param modelClass      Firebase will marshall the data at a location into an instance of a class that you provide
     * @param modelLayout     This is the layout used to represent a single item in the list. You will be responsible for populating an
     *                        instance of the corresponding view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     * @param ref             The Firebase location to watch for data changes. Can also be a slice of a location, using some
     *                        combination of {@code limit()}, {@code startAt()}, and {@code endAt()}.
     */
    private ChatListAdapter(Class<Module> modelClass, int modelLayout, Class<ChatListViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    protected void populateViewHolder(ChatListViewHolder viewHolder, Module model, int position) {
        chatRef.child(this.getRef(position).getKey()).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    ChatMessage message = ds.getValue(ChatMessage.class);
                    viewHolder.bind(message);
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        viewHolder.bind(model, listener);
    }

    @Override
    protected void onDataChanged() {
        super.onDataChanged();
        emptyListStateListener.onEmpty();
    }

    public void setOnChatListItemClickedListener(OnChatListItemClickedListener listener) {
        this.listener = listener;
    }

    public void setEmptyListStateListener(EmptyListStateListener emptyListStateListener) {
        this.emptyListStateListener = emptyListStateListener;
    }

    public interface OnChatListItemClickedListener {
        void onChatItemClicked(Module module);
    }
}
