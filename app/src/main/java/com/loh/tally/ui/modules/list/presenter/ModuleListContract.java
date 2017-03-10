package com.loh.tally.ui.modules.list.presenter;

import com.loh.tally.ui.base.presenter.BaseContract;

/**
 * File: ModuleListContract.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: Details how the view and presenter for
 * {@link com.loh.tally.ui.modules.list.fragment.ModuleListFragment} communicate.
 */
public interface ModuleListContract {

    interface View extends BaseContract.ListView {

    }

    interface Presenter extends BaseContract.Presenter<View> {

    }
}
