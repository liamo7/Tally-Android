package com.loh.tally.ui.presentations.poll.adapter;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.loh.tally.R;

/**
 * File: OpenFormListAdapter.java
 * Date: 13/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */

public class OpenFormListAdapter extends FirebaseRecyclerAdapter<String, OpenFormViewHolder> {

    public OpenFormListAdapter(DatabaseReference ref) {
        this(String.class, R.layout.item_open_form, OpenFormViewHolder.class, ref);
    }
    /**
     * @param modelClass      Firebase will marshall the data at a location into an instance of a class that you provide
     * @param modelLayout     This is the layout used to represent a single item in the list. You will be responsible for populating an
     *                        instance of the corresponding view with the data from an instance of modelClass.
     * @param viewHolderClass The class that hold references to all sub-views in an instance modelLayout.
     * @param ref             The Firebase location to watch for data changes. Can also be a slice of a location, using some
     *                        combination of {@code limit()}, {@code startAt()}, and {@code endAt()}.
     */
    public OpenFormListAdapter(Class<String> modelClass, int modelLayout, Class<OpenFormViewHolder> viewHolderClass, Query ref) {
        super(modelClass, modelLayout, viewHolderClass, ref);
    }

    @Override
    protected void populateViewHolder(OpenFormViewHolder viewHolder, String model, int position) {
        viewHolder.bind(model);
    }
}
