package com.loh.tally.ui.presentations.poll.presenter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.loh.tally.domain.database.presentation.PresentationService;
import com.loh.tally.domain.model.Poll;
import com.loh.tally.ui.base.AsyncCallback;
import com.loh.tally.ui.base.dagger.scope.ViewScope;
import com.loh.tally.ui.base.presenter.BasePresenter;
import com.loh.tally.ui.presentations.poll.adapter.PollPagerAdapter;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * File: PollPresenter.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */
@ViewScope
public class PollPresenter extends BasePresenter<PollContract.View> implements PollContract.Presenter {

    private final PresentationService presentationService;
    private final Bus bus;

    @Inject
    public PollPresenter(PresentationService presentationService, Bus bus) {
        this.presentationService = presentationService;
        this.bus = bus;
    }

    @Override
    public void retrievePolls(AsyncCallback<List<Poll>> callback) {
        List<Poll> polls = new ArrayList<>();

        String presentationID = getView().getPresentationID();
        presentationService.getPollsReference(presentationID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    Poll poll = ds.getValue(Poll.class);
                    poll.setID(ds.getKey());
                    poll.setPresID(presentationID);
                    polls.add(poll);

                    Timber.d("Poll: " + poll.getQuestion());
                }

                callback.onSuccess(polls);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    @Override
    public List<Poll> getPolls() {
        return null;
    }

    @Override
    public List<PollPagerAdapter.PollType> getPollTypes() {
        return null;
    }


}
