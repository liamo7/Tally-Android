package com.loh.tally.ui.authentication.presenter;

import com.loh.tally.ui.authentication.event.AuthenticationChoiceEvent;
import com.loh.tally.ui.base.presenter.BasePresenter;

/**
 * File: AuthenticationPresenter.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */
public class AuthenticationPresenter extends BasePresenter<AuthenticationContract.View> implements AuthenticationContract.Presenter {

    public AuthenticationPresenter() {
    }

    @Override
    public void handleChoice(AuthenticationChoiceEvent event) {
        if (event.getChoice() == AuthenticationChoiceEvent.AuthenticationChoice.REGISTER) {
            getView().navigateToRegister();
            return;
        }

        getView().navigateToLogin();
    }
}
