package com.loh.tally.ui.presentations.list.presenter;

import com.google.firebase.database.DatabaseReference;
import com.loh.tally.domain.database.presentation.PresentationService;
import com.loh.tally.ui.base.dagger.scope.ViewScope;
import com.loh.tally.ui.base.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * File: PresentationListPresenter.java
 * Date: 11/03/2017
 * Created By: Liam O'Hanlon
 */
@ViewScope
public class PresentationListPresenter extends BasePresenter<PresentationListContract.View> implements PresentationListContract.Presenter {

    private final PresentationService presentationService;

    @Inject
    public PresentationListPresenter(PresentationService presentationService) {
        this.presentationService = presentationService;
    }

    @Override
    public DatabaseReference getPresentationModulesReference() {
        String moduleID = getView().getModuleID();

        if (moduleID == null) {
            return null;
        }

        return presentationService.getPresentationModuleReference(moduleID);
    }
}
