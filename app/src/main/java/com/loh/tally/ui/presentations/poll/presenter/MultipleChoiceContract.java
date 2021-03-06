package com.loh.tally.ui.presentations.poll.presenter;

import com.google.firebase.database.DatabaseReference;
import com.loh.tally.domain.model.Poll;
import com.loh.tally.ui.base.presenter.BaseContract;

import java.util.List;

/**
 * File: MultipleChoiceContract.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
 */
public interface MultipleChoiceContract {

    interface View extends BaseContract.ListView {

        Poll getPoll();

        void setClickable(boolean clickable, int index);

        String getUserID();

        boolean canRespond();

        void showCastMessage();
    }

    interface Presenter extends BaseContract.Presenter<MultipleChoiceContract.View> {

        DatabaseReference getPollResponseReference();

        void detectResponseSubmission();

        List<String> getChoices();

        void submitResponse(int position);
    }
}
