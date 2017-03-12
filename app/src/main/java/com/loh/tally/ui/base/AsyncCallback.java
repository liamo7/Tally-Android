package com.loh.tally.ui.base;

/**
 * File: AsyncCallback.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */

public interface AsyncCallback<T> {

    void onSuccess(T model);

    void onFailure();
}
