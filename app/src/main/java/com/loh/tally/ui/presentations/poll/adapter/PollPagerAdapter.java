package com.loh.tally.ui.presentations.poll.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.loh.tally.domain.model.Poll;
import com.loh.tally.ui.base.dagger.scope.ViewScope;
import com.loh.tally.ui.presentations.poll.fragment.MultipleChoiceFragment;
import com.loh.tally.ui.presentations.poll.fragment.OpenFormFragment;

import java.util.List;

import javax.inject.Inject;

/**
 * File: PollPagerAdapter.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */
@ViewScope
public class PollPagerAdapter extends FragmentPagerAdapter {

    private List<Poll> polls;

    @Inject
    public PollPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        //PollType pollType = PollType.valueOf(polls.get(position).getQuestionType());
        //Timber.d(pollType.getType());
        Poll poll = polls.get(position);
        String pollType = poll.getQuestionType();

        switch (pollType) {

            case "Multiple Choice":
                return MultipleChoiceFragment.newInstance(poll);

            case "Open":
                return OpenFormFragment.newInstance(poll);

            default:
                break;
        }

        return null;
    }

    @Override
    public int getCount() {
        return this.polls != null ? this.polls.size() : 0;
    }

    public void setPolls(List<Poll> polls) {
        this.polls = polls;
        notifyDataSetChanged();
    }

    public Poll getPoll(int position) {
        return this.polls.get(position);
    }

    public enum PollType {
        MULTIPLE_CHOICE("Multiple Choice"),
        OPEN_FORM("Open");

        private final String type;

        PollType(String type) {
            this.type = type;
        }

        public String getType() {
            return type;
        }
    }
}
