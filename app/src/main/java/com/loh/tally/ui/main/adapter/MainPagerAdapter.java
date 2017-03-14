package com.loh.tally.ui.main.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.loh.tally.ui.base.dagger.scope.ViewScope;
import com.loh.tally.ui.chat.list.fragment.ChatListFragment;
import com.loh.tally.ui.modules.list.fragment.ModuleListFragment;

/**
 * File: MainPagerAdapter.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */
@ViewScope
public class MainPagerAdapter extends FragmentPagerAdapter {

    public static final int PAGE_MODULE_LIST = 0;
    public static final int PAGE_CHAT_LIST = 1;

    public MainPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case PAGE_MODULE_LIST:
                return ModuleListFragment.newInstance();

            case PAGE_CHAT_LIST:
                return ChatListFragment.newInstance();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 2;
    }
}
