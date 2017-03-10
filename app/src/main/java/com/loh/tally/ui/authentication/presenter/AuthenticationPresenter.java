package com.loh.tally.ui.authentication.presenter;

import com.loh.tally.domain.authentication.AuthenticationManager;
import com.loh.tally.ui.authentication.event.AuthenticationChoiceEvent;
import com.loh.tally.ui.authentication.event.AuthenticationLoginEvent;
import com.loh.tally.ui.authentication.event.AuthenticationRegisterEvent;
import com.loh.tally.ui.base.dagger.scope.ViewScope;
import com.loh.tally.ui.base.presenter.BasePresenter;

/**
 * File: AuthenticationPresenter.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */
@ViewScope
public class AuthenticationPresenter extends BasePresenter<AuthenticationContract.View> implements AuthenticationContract.Presenter {

    private final AuthenticationManager authenticationManager;

    public AuthenticationPresenter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public void handleChoice(AuthenticationChoiceEvent event) {
        if (event.getChoice() == AuthenticationChoiceEvent.AuthenticationChoice.REGISTER) {
            getView().navigateToRegister();
            return;
        }

        getView().navigateToLogin();
    }

    @Override
    public void handleLogin(AuthenticationLoginEvent event) {
        String email = event.getEmail();
        String password = event.getPassword();

        authenticationManager.login(email, password);

    }

    @Override
    public void handleRegister(AuthenticationRegisterEvent event) {
        String email = event.getEmail();
        String password = event.getPassword();

        authenticationManager.register(email, password);
    }

    @Override
    public void navigateToMain() {
        getView().navigateToMain();
    }

    @Override
    public void checkAuthenticationState() {
        if (authenticationManager.isLoggedIn()) {
            getView().navigateToMain();
        }
    }
}
