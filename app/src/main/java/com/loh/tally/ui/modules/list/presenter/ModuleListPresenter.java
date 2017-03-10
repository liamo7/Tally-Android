package com.loh.tally.ui.modules.list.presenter;

import com.loh.tally.ui.base.dagger.scope.ViewScope;
import com.loh.tally.ui.base.presenter.BasePresenter;

/**
 * File: ModuleListPresenter.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: Delegates communication from {@link com.loh.tally.ui.modules.list.fragment.ModuleListFragment}
 * and the business logic.
 */
@ViewScope
public class ModuleListPresenter extends BasePresenter<ModuleListContract.View> implements ModuleListContract.Presenter {

    public ModuleListPresenter() {
    }
}
