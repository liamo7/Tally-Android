package com.loh.tally.ui.base.presenter;

/**
 * File: BaseContract.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: Contract that all presenter and views can implement with common functionality.
 */
public interface BaseContract {

    interface View {

    }

    interface ListView extends BaseContract.View {

    }

    interface Presenter<V extends BaseContract.View> {

        void attach(V view);

        void setup();

        void detach();
    }
}
