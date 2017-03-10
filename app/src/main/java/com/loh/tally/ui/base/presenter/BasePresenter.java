package com.loh.tally.ui.base.presenter;

/**
 * File: BasePresenter.java
 * Date: 10/03/2017
 * Created By: Liam O'Hanlon
 * Description: Base presenter that handles the attaching and detaching of views.
 */
public abstract class BasePresenter<V extends BaseContract.View> implements BaseContract.Presenter<V> {

    private V view;

    public BasePresenter() {
    }

    @Override
    public void attach(V view) {
        this.view = view;
    }

    @Override
    public void setup() {

    }

    @Override
    public void detach() {
        this.view = null;
    }

    public V getView() {
        return view;
    }
}
