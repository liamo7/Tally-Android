package com.loh.tally.ui.presentations.poll.presenter;

import com.loh.tally.ui.base.dagger.scope.ViewScope;
import com.loh.tally.ui.base.presenter.BasePresenter;

import javax.inject.Inject;

/**
 * File: MultipleChoicePresenter.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */
@ViewScope
public class MultipleChoicePresenter extends BasePresenter<MultipleChoiceContract.View> implements MultipleChoiceContract.Presenter {

    @Inject
    public MultipleChoicePresenter() {

    }
}
