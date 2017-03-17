package com.loh.tally.ui.presentations.poll.presenter;

import com.google.firebase.database.DatabaseReference;
import com.loh.tally.domain.database.presentation.PresentationService;
import com.loh.tally.ui.base.dagger.scope.ViewScope;
import com.loh.tally.ui.base.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * File: OpenFormPresenter.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
 */
@ViewScope
public class OpenFormPresenter extends BasePresenter<OpenFormContract.View> implements OpenFormContract.Presenter {

    private final PresentationService presentationService;

    @Inject
    public OpenFormPresenter(PresentationService presentationService) {
        this.presentationService = presentationService;
    }

    @Override
    public void submitResponse() {
        String pollID = getView().getPollID();
        String message = getView().getResponse();

        if (message.isEmpty() || pollID == null) {
            return;
        }

        presentationService.submitOpenFormResponse(pollID, message);
        getView().clearMessage();
        getView().scrollToTop();
    }

    @Override
    public DatabaseReference getPollResponseReference() {
        String pollID = getView().getPollID();
        return presentationService.getPollResponsesReference(pollID);
    }
}
