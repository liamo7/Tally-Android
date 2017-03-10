package com.loh.tally.ui.authentication.activity;

import android.os.Bundle;
import android.support.v4.view.ViewPager;

import com.loh.tally.R;
import com.loh.tally.ui.authentication.adapter.AuthenticationPagerAdapter;
import com.loh.tally.ui.authentication.event.AuthenticationChoiceEvent;
import com.loh.tally.ui.authentication.event.AuthenticationLoginEvent;
import com.loh.tally.ui.authentication.event.AuthenticationRegisterEvent;
import com.loh.tally.ui.authentication.presenter.AuthenticationContract;
import com.loh.tally.ui.base.activity.BaseActivity;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

import butterknife.BindView;

public class AuthenticationActivity extends BaseActivity implements AuthenticationContract.View {

    @BindView(R.id.viewpager) ViewPager viewPager;

    @Inject AuthenticationContract.Presenter presenter;
    @Inject AuthenticationPagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        presenter.attach(this);

        setupViewPager();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.detach();
    }

    @Override
    protected void inject() {
        getViewComponent().inject(this);
    }

    private void setupViewPager() {
        viewPager.setAdapter(pagerAdapter);
        viewPager.setOffscreenPageLimit(2);
        viewPager.setCurrentItem(AuthenticationPagerAdapter.PAGE_CHOICE);
    }

    @Subscribe
    public void onChoiceButtonClicked(AuthenticationChoiceEvent event) {
        presenter.handleChoice(event);
    }

    @Subscribe
    public void onLoginButtonClicked(AuthenticationLoginEvent event) {
        presenter.handleLogin(event);
    }

    @Subscribe
    public void onRegisterButtonClicked(AuthenticationRegisterEvent event) {
        presenter.handleRegister(event);
    }

    @Override
    public void navigateToRegister() {
        viewPager.setCurrentItem(AuthenticationPagerAdapter.PAGE_REGISTER);
    }

    @Override
    public void navigateToLogin() {
        viewPager.setCurrentItem(AuthenticationPagerAdapter.PAGE_LOGIN);
    }
}
