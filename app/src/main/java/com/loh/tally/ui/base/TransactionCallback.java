package com.loh.tally.ui.base;

/**
 * File: TransactionCallback.java
 * Date: 17/03/2017
 * Created By: Liam O'Hanlon
 */
public interface TransactionCallback {

    void onSuccess();

    void onError(String error);
}
