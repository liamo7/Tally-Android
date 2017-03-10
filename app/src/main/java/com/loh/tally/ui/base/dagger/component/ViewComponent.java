package com.loh.tally.ui.base.dagger.component;

import com.loh.tally.ui.authentication.activity.AuthenticationActivity;
import com.loh.tally.ui.base.activity.BaseActivity;
import com.loh.tally.ui.base.dagger.module.ViewModule;
import com.loh.tally.ui.base.dagger.scope.ViewScope;
import com.loh.tally.ui.chat.list.fragment.ChatListFragment;
import com.loh.tally.ui.main.activity.MainActivity;
import com.loh.tally.ui.modules.list.fragment.ModuleListFragment;

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

    void inject(ModuleListFragment fragment);

    void inject(ChatListFragment fragment);
}
