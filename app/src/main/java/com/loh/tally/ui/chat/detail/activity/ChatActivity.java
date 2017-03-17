package com.loh.tally.ui.chat.detail.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.loh.tally.R;
import com.loh.tally.ui.authentication.event.AuthenticationLogoutEvent;
import com.loh.tally.ui.base.activity.BaseActivity;
import com.loh.tally.ui.chat.detail.fragment.ChatDetailFragment;
import com.loh.tally.util.IntentUtil;

public class ChatActivity extends BaseActivity {

    public static Intent getStartingIntent(Context context, Bundle bundle) {
        Intent intent = new Intent(context, ChatActivity.class);
        intent.putExtra(IntentUtil.BUNDLE_KEY, bundle);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        setupBackToolbar();
        loadChatFragment();
    }

    @Override
    protected void inject() {
        getViewComponent().inject(this);
    }

    @Override
    public void onSignOutEvent(AuthenticationLogoutEvent event) {
        handleSignOut();
    }

    public String getModuleKey() {
        return getIntent().getBundleExtra(IntentUtil.BUNDLE_KEY).getString(IntentUtil.INTENT_MODULE_KEY);
    }

    public void loadChatFragment() {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frameContainer, ChatDetailFragment.newInstance(getModuleKey()), "TAG")
                .commit();
    }
}
