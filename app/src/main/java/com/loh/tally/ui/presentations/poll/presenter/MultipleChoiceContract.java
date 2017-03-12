package com.loh.tally.ui.presentations.poll.presenter;

import com.loh.tally.ui.base.presenter.BaseContract;

/**
 * File: MultipleChoiceContract.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */

public interface MultipleChoiceContract {

    interface View extends BaseContract.ListView {

    }

    interface Presenter extends BaseContract.Presenter<MultipleChoiceContract.View> {

    }
}
