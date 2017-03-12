package com.loh.tally.ui.base.dagger.module;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.loh.tally.domain.authentication.AuthenticationManager;
import com.loh.tally.domain.database.chat.ChatService;
import com.loh.tally.domain.database.modules.ModuleService;
import com.loh.tally.domain.database.presentation.PresentationService;
import com.loh.tally.ui.authentication.adapter.AuthenticationPagerAdapter;
import com.loh.tally.ui.authentication.presenter.AuthenticationContract;
import com.loh.tally.ui.authentication.presenter.AuthenticationPresenter;
import com.loh.tally.ui.base.dagger.scope.ViewScope;
import com.loh.tally.ui.chat.detail.presenter.ChatDetailContract;
import com.loh.tally.ui.chat.detail.presenter.ChatDetailPresenter;
import com.loh.tally.ui.chat.list.adapter.ChatListAdapter;
import com.loh.tally.ui.chat.list.presenter.ChatListContract;
import com.loh.tally.ui.chat.list.presenter.ChatListPresenter;
import com.loh.tally.ui.main.adapter.MainPagerAdapter;
import com.loh.tally.ui.main.presenter.MainContract;
import com.loh.tally.ui.main.presenter.MainPresenter;
import com.loh.tally.ui.modules.list.adapter.ModuleListAdapter;
import com.loh.tally.ui.modules.list.presenter.ModuleListContract;
import com.loh.tally.ui.modules.list.presenter.ModuleListPresenter;
import com.loh.tally.ui.presentations.list.presenter.PresentationListContract;
import com.loh.tally.ui.presentations.list.presenter.PresentationListPresenter;
import com.loh.tally.ui.presentations.main.adapter.PresentationPagerAdapter;
import com.loh.tally.ui.presentations.main.presenter.PresentationContract;
import com.loh.tally.ui.presentations.main.presenter.PresentationPresenter;
import com.loh.tally.ui.presentations.poll.adapter.PollPagerAdapter;
import com.loh.tally.ui.presentations.poll.presenter.PollContract;
import com.loh.tally.ui.presentations.poll.presenter.PollPresenter;
import com.loh.tally.util.IntentUtil;
import com.squareup.otto.Bus;

import dagger.Module;
import dagger.Provides;

/**
 * File: ViewModule.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: Details how all components scoped by {@link ViewScope} are provided.
 */
@Module
public class ViewModule {

    private final AppCompatActivity activity;

    public ViewModule(AppCompatActivity activity) {
        this.activity = activity;
    }

    @Provides
    @ViewScope
    public AppCompatActivity provideActivity() {
        return this.activity;
    }

    // Auth components
    @Provides
    @ViewScope
    public AuthenticationContract.Presenter provideAuthenticationPresenter(AuthenticationManager authenticationManager) {
        return new AuthenticationPresenter(authenticationManager);
    }

    @Provides
    @ViewScope
    public AuthenticationPagerAdapter provideAuthenticationPagerAdapter(Bus bus) {
        return new AuthenticationPagerAdapter(bus);
    }

    // Main Components
    @Provides
    @ViewScope
    public MainContract.Presenter provideMainPresenter(AuthenticationManager authenticationManager) {
        return new MainPresenter(authenticationManager);
    }

    @Provides
    @ViewScope
    public MainPagerAdapter provideMainPagerAdapter(AppCompatActivity activity) {
        return new MainPagerAdapter(activity.getSupportFragmentManager());
    }

    // Module List Components
    @Provides
    @ViewScope
    public ModuleListContract.Presenter provideModuleListPresenter() {
        return new ModuleListPresenter();
    }

    @Provides
    @ViewScope
    public ModuleListAdapter provideModuleListAdapter(ModuleService moduleService, AuthenticationManager authenticationManager) {
        return new ModuleListAdapter(moduleService.getModuleEnrolledReference(authenticationManager.getCurrentUser().getUid()));
    }

    // Chat List Components
    @Provides
    @ViewScope
    public ChatListContract.Presenter provideChatListPresenter() {
        return new ChatListPresenter();
    }

    @Provides
    @ViewScope
    public ChatListAdapter provideChatListAdapter(ModuleService moduleService, AuthenticationManager authenticationManager, ChatService chatService) {
        final String userID = authenticationManager.getCurrentUser().getUid();
        return new ChatListAdapter(moduleService.getModuleEnrolledReference(userID),
                moduleService.getModuleCreatedReference(userID), chatService.getRootRef());
    }

    // Chat Detail Components
    @Provides
    @ViewScope
    public ChatDetailContract.Presenter provideChatDetailPresenter(ChatService chatService) {
        return new ChatDetailPresenter(chatService);
    }

    // Presentation Components
    @Provides
    @ViewScope
    public PresentationContract.Presenter providePresentationPresenter(AuthenticationManager authenticationManager) {
        return new PresentationPresenter(authenticationManager);
    }

    @Provides
    @ViewScope
    public PresentationPagerAdapter providePresentationPagerAdapter(AppCompatActivity activity) {
        Bundle bundle = activity.getIntent().getBundleExtra(IntentUtil.BUNDLE_KEY);
        String moduleKey = bundle != null ? bundle.getString(IntentUtil.INTENT_MODULE_KEY, null) : null;

        return new PresentationPagerAdapter(activity.getSupportFragmentManager(), moduleKey);
    }

    // Presentation List components
    @Provides
    @ViewScope
    public PresentationListContract.Presenter providePresentationListPresenter(PresentationService presentationService) {
        return new PresentationListPresenter(presentationService);
    }

    // Poll Components
    @Provides
    @ViewScope
    public PollPagerAdapter providePollPagerAdapter(AppCompatActivity appCompatActivity) {
        return new PollPagerAdapter(appCompatActivity.getSupportFragmentManager());
    }

    @Provides
    @ViewScope
    public PollContract.Presenter providePollPresenter(PresentationService presentationService, Bus bus) {
        return new PollPresenter(presentationService, bus);
    }
}
