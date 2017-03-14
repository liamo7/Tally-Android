package com.loh.tally.ui.authentication.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.loh.tally.R;
import com.loh.tally.ui.authentication.adapter.AuthenticationPagerAdapter;
import com.loh.tally.ui.authentication.event.AuthenticationChoiceEvent;
import com.loh.tally.ui.authentication.event.AuthenticationErrorEvent;
import com.loh.tally.ui.authentication.event.AuthenticationLoginEvent;
import com.loh.tally.ui.authentication.event.AuthenticationLogoutEvent;
import com.loh.tally.ui.authentication.event.AuthenticationRegisterEvent;
import com.loh.tally.ui.authentication.event.AuthenticationSuccessEvent;
import com.loh.tally.ui.authentication.presenter.AuthenticationContract;
import com.loh.tally.ui.base.activity.BaseActivity;
import com.loh.tally.ui.base.event.ValidationErrorEvent;
import com.loh.tally.ui.main.activity.MainActivity;
import com.loh.tally.util.IntentUtil;
import com.squareup.otto.Subscribe;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

public class AuthenticationActivity extends BaseActivity implements AuthenticationContract.View {

    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.container) View container;
    @BindView(R.id.barChart) BarChart barChart;

    @Inject AuthenticationContract.Presenter presenter;
    @Inject AuthenticationPagerAdapter pagerAdapter;

    public static Intent getStartingIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, AuthenticationActivity.class);
        intent.putExtra(IntentUtil.BUNDLE_KEY, bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        presenter.attach(this);
        setupViewPager();
        setupBarChart();

        presenter.checkAuthenticationState();
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

    @Subscribe
    @Override
    public void onSignOutEvent(AuthenticationLogoutEvent event) {
        handleSignOut();
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

    @Subscribe
    public void onAuthenticationError(AuthenticationErrorEvent event) {
        Snackbar.make(container, event.getMessage(), Snackbar.LENGTH_SHORT).show();
    }

    @Subscribe
    public void onAuthenticationSuccess(AuthenticationSuccessEvent event) {
        presenter.navigateToMain();
    }

    @Subscribe
    public void onValidationErrorEvent(ValidationErrorEvent event) {
        Snackbar.make(container, event.getMessage(), Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void navigateToRegister() {
        viewPager.setCurrentItem(AuthenticationPagerAdapter.PAGE_REGISTER);
    }

    @Override
    public void navigateToLogin() {
        viewPager.setCurrentItem(AuthenticationPagerAdapter.PAGE_LOGIN);
    }

    @Override
    public void navigateToMain() {
        Bundle bundle = new Bundle();
        startActivity(MainActivity.getStartingIntent(this, bundle));
        finish();
    }

    private void setupBarChart() {
        BarData barData = new BarData(getStartingDataSet());
        barChart.setData(barData);
        setupBarDesign();
    }

    private void setupBarDesign() {
        barChart.setDrawBorders(false);
        barChart.getXAxis().setDrawAxisLine(false);
        barChart.getAxisRight().setDrawAxisLine(false);
        barChart.getAxisLeft().setDrawAxisLine(false);
        barChart.setDrawGridBackground(false);
        barChart.getXAxis().setDrawGridLines(false);
        barChart.getAxisLeft().setDrawGridLines(false);
        barChart.getAxisRight().setDrawGridLines(false);
        barChart.getLegend().setEnabled(false);
        barChart.getXAxis().setDrawLabels(false);
        barChart.getAxisLeft().setDrawLabels(false);
        barChart.getAxisRight().setDrawLabels(false);
        barChart.getDescription().setEnabled(false);
        barChart.setClickable(false);
        barChart.setDrawValueAboveBar(false);
        barChart.getBarData().setDrawValues(false);
        barChart.setTouchEnabled(false);

    }

    private IBarDataSet getStartingDataSet() {
        List<BarEntry> yVals = new ArrayList<>();
        yVals.add(new BarEntry(0, 40));
        yVals.add(new BarEntry(1, 60));
        yVals.add(new BarEntry(2, 90));
        yVals.add(new BarEntry(3, 70));
        yVals.add(new BarEntry(4, 30));

        BarDataSet dataSet;

        dataSet = new BarDataSet(yVals, "Auth");
        dataSet.setColors(ColorTemplate.MATERIAL_COLORS);
        ArrayList<IBarDataSet> dataSets = new ArrayList<>();
        dataSets.add(dataSet);

        BarData data = new BarData(dataSets);
        data.setBarWidth(0.7f);
        barChart.setData(data);

        return dataSet;
    }
}
