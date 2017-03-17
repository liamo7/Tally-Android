package com.loh.tally.ui.authentication.view;

import android.view.View;

import com.loh.tally.R;
import com.loh.tally.ui.authentication.event.AuthenticationChoiceEvent;
import com.squareup.otto.Bus;

import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * File: ChoiceView.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 */
public class ChoiceView {

    private final View layout;
    private final Bus bus;

    public ChoiceView(View layout, Bus bus) {
        this.layout = layout;
        this.bus = bus;
        ButterKnife.bind(this, layout);
    }

    @OnClick(R.id.loginButton)
    protected void onLoginButtonClicked() {
        bus.post(new AuthenticationChoiceEvent(AuthenticationChoiceEvent.AuthenticationChoice.LOGIN));
    }

    @OnClick(R.id.registerButton)
    protected void onRegisterButtonClicked() {
        bus.post(new AuthenticationChoiceEvent(AuthenticationChoiceEvent.AuthenticationChoice.REGISTER));
    }
}
