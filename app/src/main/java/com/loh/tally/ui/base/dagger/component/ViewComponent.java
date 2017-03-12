package com.loh.tally.ui.base.dagger.component;

import com.loh.tally.ui.authentication.activity.AuthenticationActivity;
import com.loh.tally.ui.base.activity.BaseActivity;
import com.loh.tally.ui.base.dagger.module.ViewModule;
import com.loh.tally.ui.base.dagger.scope.ViewScope;
import com.loh.tally.ui.chat.detail.fragment.ChatDetailFragment;
import com.loh.tally.ui.chat.list.fragment.ChatListFragment;
import com.loh.tally.ui.main.activity.MainActivity;
import com.loh.tally.ui.modules.list.fragment.ModuleListFragment;
import com.loh.tally.ui.presentations.list.fragment.PresentationListFragment;
import com.loh.tally.ui.presentations.main.activity.PresentationActivity;
import com.loh.tally.ui.presentations.poll.activity.PollActivity;
import com.loh.tally.ui.presentations.poll.fragment.MultipleChoiceFragment;
import com.loh.tally.ui.presentations.poll.fragment.OpenFormFragment;
import com.loh.tally.ui.presentations.poll.fragment.PollFragment;

import dagger.Subcomponent;

/**
 * File: ViewComponent.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: All views within the application are constrained by this component and its scope.
 */
@ViewScope
@Subcomponent(modules = {ViewModule.class})
public interface ViewComponent {

    void inject(BaseActivity activity);

    void inject(AuthenticationActivity activity);

    void inject(MainActivity activity);

    void inject(PresentationActivity activity);

    void inject(ModuleListFragment fragment);

    void inject(ChatListFragment fragment);

    void inject(ChatDetailFragment fragment);

    void inject(PresentationListFragment fragment);

    void inject(PollActivity activity);

    void inject(PollFragment fragment);

    void inject(MultipleChoiceFragment fragment);

    void inject(OpenFormFragment fragment);
}
