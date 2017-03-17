package com.loh.tally.ui.presentations.list.presenter;

import com.google.firebase.database.DatabaseReference;
import com.loh.tally.ui.base.presenter.BaseContract;

/**
 * File: PresentationListContract.java
 * Date: 11/03/2017
 * Created By: Liam O'Hanlon
 */
public interface PresentationListContract {

    interface View extends BaseContract.ListView {

        String getModuleID();
    }

    interface Presenter extends BaseContract.Presenter<PresentationListContract.View> {

        DatabaseReference getPresentationModulesReference();
    }
}
