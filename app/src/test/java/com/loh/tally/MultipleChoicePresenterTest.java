package com.loh.tally;

import com.loh.tally.domain.database.presentation.PresentationService;
import com.loh.tally.ui.base.TransactionCallback;
import com.loh.tally.ui.presentations.poll.presenter.MultipleChoiceContract;
import com.loh.tally.ui.presentations.poll.presenter.MultipleChoicePresenter;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;

/**
 * File: MultipleChoicePresenterTest.java
 * Date: 18/03/2017
 * Created By: Liam O'Hanlon
 */
public class MultipleChoicePresenterTest {

    private static final String POLL_ID = "-KfSliHi1K1VLmjhuqwa";
    private static final String USER_ID = "GEOp2FoWphg4KMk1r9LOz7aMEZa2";
    private static final int INDEX = 0;
    private static final boolean SINGLE_CHOICE = false;

    @Mock MultipleChoiceContract.View view;
    @Mock PresentationService presentationService;

    private MultipleChoiceContract.Presenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = new MultipleChoicePresenter(presentationService);
        presenter.attach(view);
    }

    @Test
    public void test_submit_response() {
        verify(presentationService).submitMultipleChoiceResponse(POLL_ID, USER_ID, INDEX, SINGLE_CHOICE, new TransactionCallback() {
            @Override
            public void onSuccess() {
                verify(view).showCastMessage();
            }

            @Override
            public void onError(String error) {
            }
        });
    }
}
