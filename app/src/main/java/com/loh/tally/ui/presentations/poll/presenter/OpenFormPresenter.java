package com.loh.tally.ui.presentations.poll.presenter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.loh.tally.domain.database.presentation.PresentationService;
import com.loh.tally.ui.base.AsyncCallback;
import com.loh.tally.ui.base.dagger.scope.ViewScope;
import com.loh.tally.ui.base.presenter.BasePresenter;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * File: OpenFormPresenter.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */
@ViewScope
public class OpenFormPresenter extends BasePresenter<OpenFormContract.View> implements OpenFormContract.Presenter {

    private final PresentationService presentationService;

    @Inject
    public OpenFormPresenter(PresentationService presentationService) {
        this.presentationService = presentationService;
    }

    @Override
    public void retrievePollResponses(AsyncCallback<List<String>> callback) {
        String pollID = getView().getPollID();
        List<String> responses = new ArrayList<>();

        presentationService.getPollResponsesReference(pollID).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    String message = ds.getValue(String.class);
                    responses.add(message);
                }

                callback.onSuccess(responses);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public void retrievePoll() {

    }

    @Override
    public void submitResponse() {
        String pollID = getView().getPollID();
        String message = getView().getResponse();

        presentationService.getPollResponsesReference(pollID).push().setValue(message);
        getView().clearMessage();
    }
}
