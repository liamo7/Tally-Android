package com.loh.tally.ui.authentication.adapter;

import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.loh.tally.R;
import com.loh.tally.ui.authentication.view.ChoiceView;
import com.loh.tally.ui.authentication.view.LoginView;
import com.loh.tally.ui.authentication.view.RegisterView;
import com.squareup.otto.Bus;

/**
 * File: AuthenticationPagerAdapter.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */

public class AuthenticationPagerAdapter extends PagerAdapter {

    public static final int PAGE_REGISTER = 0;
    public static final int PAGE_CHOICE = 1;
    public static final int PAGE_LOGIN = 2;

    private final Bus bus;

    public AuthenticationPagerAdapter(Bus bus) {
        this.bus = bus;
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View view = null;
        LayoutInflater inflater = LayoutInflater.from(container.getContext());

        switch (position) {

            case PAGE_REGISTER:
                view = inflater.inflate(R.layout.pager_authentication_register, container, false);
                new RegisterView(view, bus);
                break;

            case PAGE_CHOICE:
                view = inflater.inflate(R.layout.pager_authentication_choice, container, false);
                new ChoiceView(view, bus);
                break;

            case PAGE_LOGIN:
                view = inflater.inflate(R.layout.pager_authentication_login, container, false);
                new LoginView(view, bus);
                break;
        }

        if (view != null) {
            container.addView(view);
        }

        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }
}
