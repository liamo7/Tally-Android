package com.loh.tally.ui.chat.list.presenter;

import com.loh.tally.ui.base.dagger.scope.ViewScope;
import com.loh.tally.ui.base.presenter.BasePresenter;

/**
 * File: ChatListPresenter.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: Delegates communication from {@link com.loh.tally.ui.chat.list.fragment.ChatListFragment}
 * and the business logic.
 */
@ViewScope
public class ChatListPresenter extends BasePresenter<ChatListContract.View> implements ChatListContract.Presenter {

    public ChatListPresenter() {
    }
}
