package com.loh.tally.ui.presentations.poll.presenter;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;
import com.loh.tally.domain.database.presentation.PresentationService;
import com.loh.tally.domain.model.Poll;
import com.loh.tally.ui.base.dagger.scope.ViewScope;
import com.loh.tally.ui.base.presenter.BasePresenter;

import java.util.List;

import javax.inject.Inject;

/**
 * File: MultipleChoicePresenter.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */
@ViewScope
public class MultipleChoicePresenter extends BasePresenter<MultipleChoiceContract.View> implements MultipleChoiceContract.Presenter {

    private final PresentationService presentationService;

    @Inject
    public MultipleChoicePresenter(PresentationService presentationService) {
        this.presentationService = presentationService;
    }

    @Override
    public DatabaseReference getPollResponseReference() {
        String pollID = getView().getPoll().getId();
        return presentationService.getPollResponsesReference(pollID);
    }

    @Override
    public void listenForResponseChange() {
        Poll poll = getView().getPoll();
        String userID = getView().getUserID();

        presentationService.getPollResponsesReference(poll.getId())
                .child("submission")
                .child(userID)
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            getView().setClickable(false, dataSnapshot.getValue(Integer.class));
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
    }

    @Override
    public List<String> getResponses() {
        return getView().getPoll().getChoices();
    }

    @Override
    public void submitResponse(int position) {
        Poll poll = getView().getPoll();

        presentationService.getPollResponsesReference(poll.getId()).child("values").child(String.valueOf(position))
                .runTransaction(new Transaction.Handler() {
                    @Override
                    public Transaction.Result doTransaction(MutableData mutableData) {
                        mutableData.setValue((long) mutableData.getValue() + 1);

                        if (poll.isSingleChoice()) {
                            setUserSubmission(position);
                        }

                        getView().showCastMessage();
                        return Transaction.success(mutableData);

                    }

                    @Override
                    public void onComplete(DatabaseError databaseError, boolean b, DataSnapshot dataSnapshot) {

                    }
                });
    }

    private void setUserSubmission(int index) {
        presentationService.getPollResponsesReference(getView().getPoll().getId())
                .child("submission")
                .child(getView().getUserID())
                .setValue(index);
    }
}
