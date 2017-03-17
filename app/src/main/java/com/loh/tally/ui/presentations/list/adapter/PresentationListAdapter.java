package com.loh.tally.ui.presentations.list.adapter;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.loh.tally.R;
import com.loh.tally.domain.model.Presentation;
import com.loh.tally.ui.base.EmptyListStateListener;
import com.loh.tally.ui.base.dagger.scope.ViewScope;

import javax.inject.Inject;

/**
 * File: PresentationListAdapter.java
 * Date: 11/03/2017
 * Created By: Liam O'Hanlon
 */
@ViewScope
public class PresentationListAdapter extends FirebaseRecyclerAdapter<Presentation, PresentationListViewHolder> {

    private OnPresentationItemClickListener listener;
    private EmptyListStateListener emptyStateListener;

    @Inject
    public PresentationListAdapter(DatabaseReference ref) {
        this(Presentation.class, R.layout.item_presentation_list, PresentationListViewHolder.class, ref);
    }

    /**
     * @param modelClass      Firebase will marshall the data at a location into an instance of a class that you provide
     * @param modelLayout     This is the layout used to represent a single item in the list. You will be responsible for populating an
     *                        instance of the corresponding view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     * @param ref             The Firebase location to watch for data changes. Can also be a slice of a location, using some
     *                        combination of {@code limit()}, {@code startAt()}, and {@code endAt()}.
     */
    private PresentationListAdapter(Class<Presentation> modelClass, int modelLayout, Class<PresentationListViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    protected void populateViewHolder(PresentationListViewHolder viewHolder, Presentation model, int position) {
        model.setId(this.getRef(position).getKey());
        viewHolder.bind(model, listener);
    }

    @Override
    protected void onDataChanged() {
        super.onDataChanged();
        emptyStateListener.onEmpty();
    }

    public void setOnPresentationClickListener(OnPresentationItemClickListener listener) {
        this.listener = listener;
    }

    public void setOnEmptyStateListener(EmptyListStateListener emptyStateListener) {
        this.emptyStateListener = emptyStateListener;
    }

    public interface OnPresentationItemClickListener {
        void onPresentationClicked(Presentation presentation);
    }
}
