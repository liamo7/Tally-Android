package com.loh.tally.ui.presentations.poll.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;

import com.loh.tally.R;
import com.loh.tally.domain.model.Poll;
import com.loh.tally.ui.authentication.event.AuthenticationLogoutEvent;
import com.loh.tally.ui.base.AsyncCallback;
import com.loh.tally.ui.base.activity.BaseActivity;
import com.loh.tally.ui.chat.detail.activity.ChatActivity;
import com.loh.tally.ui.presentations.poll.adapter.PollPagerAdapter;
import com.loh.tally.ui.presentations.poll.presenter.PollContract;
import com.loh.tally.util.IntentUtil;
import com.squareup.otto.Subscribe;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;

public class PollActivity extends BaseActivity implements PollContract.View, AsyncCallback<List<Poll>> {

    @BindView(R.id.viewpager) ViewPager viewPager;
    @BindView(R.id.chatFab) FloatingActionButton fab;

    @Inject PollContract.Presenter presenter;
    @Inject PollPagerAdapter pollPagerAdapter;

    private MenuItem chatMenu;

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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_chat, menu);
        chatMenu = menu.findItem(R.id.action_chat);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_chat) {
            onChatViewClicked();
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupViewpager() {
        viewPager.setAdapter(pollPagerAdapter);
        viewPager.setOffscreenPageLimit(pollPagerAdapter.getCount());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                presenter.handlePollSelected(position);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    @Override
    public String getPresentationID() {
        return getIntent().getBundleExtra(IntentUtil.BUNDLE_KEY).getString(IntentUtil.INTENT_PRESENTATION_KEY, null);
    }

    @Override
    public Poll getPollFromAdapter(int position) {
        return pollPagerAdapter.getPoll(position);
    }

    @Override
    public void showFabChat() {
        fab.show();
    }

    @Override
    public void hideFabChat() {
        fab.hide();
    }

    @Override
    public void hideChatMenu() {
        if (chatMenu != null) {
            chatMenu.setVisible(false);
        }
    }

    @Override
    public void showChatMenu() {
        if (chatMenu != null) {
            chatMenu.setVisible(true);
        }
    }

    @Override
    public void onSuccess(List<Poll> model) {
        pollPagerAdapter.setPolls(model);
    }

    @Override
    public void onError(String message) {
    }

    @OnClick({R.id.chatFab})
    protected void onChatViewClicked() {
        Bundle bundle = new Bundle();
        bundle.putString(IntentUtil.INTENT_MODULE_KEY, getModuleKey());
        startActivity(ChatActivity.getStartingIntent(this, bundle));
    }

    @Override
    public String getModuleKey() {
        return getIntent().getBundleExtra(IntentUtil.BUNDLE_KEY).getString(IntentUtil.INTENT_MODULE_KEY);
    }
}
