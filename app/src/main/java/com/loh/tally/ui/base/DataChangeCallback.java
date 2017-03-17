package com.loh.tally.ui.base;

/**
 * File: DataChangeCallback.java
 * Date: 17/03/2017
 * Created By: Liam O'Hanlon
 */
public interface DataChangeCallback<T> {

    void onChange(T value);
}
