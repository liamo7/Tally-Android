package com.loh.tally.ui.authentication.view;

import android.support.design.widget.TextInputEditText;
import android.view.View;

import com.loh.tally.R;
import com.loh.tally.ui.authentication.event.AuthenticationLoginEvent;
import com.loh.tally.ui.base.event.ValidationErrorEvent;
import com.squareup.otto.Bus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * File: LoginView.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 */
public class LoginView {

    @BindView(R.id.email) TextInputEditText emailEditText;
    @BindView(R.id.password) TextInputEditText passwordEditText;

    private final View view;
    private final Bus bus;

    public LoginView(View view, Bus bus) {
        this.view = view;
        this.bus = bus;
        ButterKnife.bind(this, view);
    }

    @OnClick(R.id.loginButton)
    protected void onLoginButtonClicked() {
        String email = emailEditText.getText().toString();
        String password = passwordEditText.getText().toString();

        if (email.isEmpty() || password.isEmpty()) {
            String message = view.getContext().getString(R.string.authentication_blank_error);
            bus.post(new ValidationErrorEvent(message));
            return;
        }

        bus.post(new AuthenticationLoginEvent(email, password));
    }
}
