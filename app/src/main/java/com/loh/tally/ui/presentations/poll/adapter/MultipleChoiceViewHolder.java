package com.loh.tally.ui.presentations.poll.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RadioButton;

import com.loh.tally.R;
import com.loh.tally.ui.base.dagger.scope.ViewScope;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * File: MultipleChoiceViewHolder.java
 * Date: 12/03/2017
 * Created By: Liam O'Hanlon
 * Description: TODO:
 */
@ViewScope
public class MultipleChoiceViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.responseRadioButton) RadioButton radioButton;

    @Inject
    public MultipleChoiceViewHolder(View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }

    public void bind(String choice, int position, MultipleChoiceAdapter.OnResponseClickListener listener) {
        radioButton.setText(choice);
        radioButton.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked && listener != null) {
                listener.onResponseClicked(position);
            }
        });
    }
}
