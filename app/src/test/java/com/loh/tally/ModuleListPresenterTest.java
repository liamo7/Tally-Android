package com.loh.tally;

import com.loh.tally.ui.modules.list.presenter.ModuleListContract;
import com.loh.tally.ui.modules.list.presenter.ModuleListPresenter;

import org.junit.Before;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

/**
 * File: ModuleListPresenterTest.java
 * Date: 18/03/2017
 * Created By: Liam O'Hanlon
 */
public class ModuleListPresenterTest {

    @Mock ModuleListContract.View view;

    private ModuleListContract.Presenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new ModuleListPresenter();
    }
}
