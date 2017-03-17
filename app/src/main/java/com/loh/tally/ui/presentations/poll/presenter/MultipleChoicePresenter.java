package com.loh.tally.ui.presentations.poll.presenter;

import com.google.firebase.database.DatabaseReference;
import com.loh.tally.domain.database.presentation.PresentationService;
import com.loh.tally.domain.model.Poll;
import com.loh.tally.ui.base.TransactionCallback;
import com.loh.tally.ui.base.dagger.scope.ViewScope;
import com.loh.tally.ui.base.presenter.BasePresenter;

import java.util.List;

import javax.inject.Inject;

import timber.log.Timber;

/**
 * File: MultipleChoicePresenter.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
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
    public void detectResponseSubmission() {
        Poll poll = getView().getPoll();
        String userID = getView().getUserID();

        presentationService.detectSubmission(poll.getId(), userID, value -> getView().setClickable(false, value));
    }

    @Override
    public List<String> getChoices() {
        return getView().getPoll().getChoices();
    }

    @Override
    public void submitResponse(int position) {
        Poll poll = getView().getPoll();
        String userID = getView().getUserID();

        presentationService.submitMultipleChoiceResponse(poll.getId(), userID, position, poll.isSingleChoice(), new TransactionCallback() {
            @Override
            public void onSuccess() {
                getView().showCastMessage();
            }

            @Override
            public void onError(String error) {
                Timber.d(error);
            }
        });
    }
}
