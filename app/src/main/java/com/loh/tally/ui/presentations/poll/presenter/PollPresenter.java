package com.loh.tally.ui.presentations.poll.presenter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.loh.tally.domain.database.presentation.PresentationService;
import com.loh.tally.domain.model.Poll;
import com.loh.tally.ui.base.AsyncCallback;
import com.loh.tally.ui.base.dagger.scope.ViewScope;
import com.loh.tally.ui.base.presenter.BasePresenter;
import com.squareup.otto.Bus;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

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
    public void handlePollSelected(int position) {
        Poll poll = getView().getPollFromAdapter(position);

        if (poll.getQuestionType().equals("Multiple Choice")) {
            getView().showFabChat();
            getView().hideChatMenu();
        } else {
            getView().hideFabChat();
            getView().showChatMenu();
        }
    }

    @Override
    public void retrievePolls(AsyncCallback<List<Poll>> callback) {
        if (callback == null) {
            return;
        }

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
                }

                callback.onSuccess(polls);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                callback.onError(databaseError.getMessage());
            }
        });

    }


}
