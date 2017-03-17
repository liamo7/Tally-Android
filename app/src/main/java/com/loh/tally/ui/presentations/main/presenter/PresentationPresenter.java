package com.loh.tally.ui.presentations.main.presenter;

import com.loh.tally.domain.authentication.AuthenticationManager;
import com.loh.tally.ui.base.dagger.scope.ViewScope;
import com.loh.tally.ui.base.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * File: PresentationPresenter.java
 * Date: 11/03/2017
 * Created By: Liam O'Hanlon
 */
@ViewScope
public class PresentationPresenter extends BasePresenter<PresentationContract.View> implements PresentationContract.Presenter {

    private final AuthenticationManager authenticationManager;

    @Inject
    public PresentationPresenter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void logout() {
        authenticationManager.logout();
    }

    @Override
    public void navigateToChat() {
        getView().navigateToChat();
    }

    @Override
    public void navigateToPresentations() {
        getView().navigateToPresentations();
    }
}
