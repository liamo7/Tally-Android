package com.loh.tally.ui.presentations.poll.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.MenuItem;

import com.loh.tally.R;
import com.loh.tally.domain.model.Poll;
import com.loh.tally.ui.authentication.event.AuthenticationLogoutEvent;
import com.loh.tally.ui.base.AsyncCallback;
import com.loh.tally.ui.base.activity.BaseActivity;
import com.loh.tally.ui.presentations.poll.adapter.PollPagerAdapter;
import com.loh.tally.ui.presentations.poll.events.PollsRetrievedEvent;
import com.loh.tally.ui.presentations.poll.presenter.PollContract;
import com.loh.tally.util.IntentUtil;
import com.squareup.otto.Subscribe;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import timber.log.Timber;

public class PollActivity extends BaseActivity implements PollContract.View, AsyncCallback<List<Poll>> {

    @BindView(R.id.viewpager) ViewPager viewPager;

    @Inject PollContract.Presenter presenter;
    @Inject PollPagerAdapter pollPagerAdapter;

    public static Intent getStartingIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, PollActivity.class);
        intent.putExtra(IntentUtil.BUNDLE_KEY, bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_poll);
        setupBackToolbar();
        presenter.attach(this);

        setupViewpager();
        presenter.retrievePolls(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        return false;
    }

    private void setupViewpager() {
        viewPager.setAdapter(pollPagerAdapter);
        viewPager.setOffscreenPageLimit(pollPagerAdapter.getCount());
    }

    @Override
    public String getPresentationID() {
        return getIntent().getBundleExtra(IntentUtil.BUNDLE_KEY).getString(IntentUtil.INTENT_PRESENTATION_KEY, null);
    }

    @Subscribe
    public void onPollsRetrievedEvent(PollsRetrievedEvent event) {
        setupViewpager();
    }

    @Override
    public void onSuccess(List<Poll> model) {
        Timber.d("Poll Success: " + model.size());
        pollPagerAdapter.setPolls(model);
    }

    @Override
    public void onFailure() {
        Timber.e("Error");
    }
}
