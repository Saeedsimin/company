package com.example.shehnepours.taxam.views.expandableRecyclerView;

import android.view.View;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.example.shehnepours.taxam.R;
import com.example.shehnepours.taxam.parents.CustomTextView;

/**
 * Created by shehnepour.s on 12/26/2017.
 */

public class CustomChildViewHolder extends ChildViewHolder {
    public CustomTextView childTextView;

    public CustomChildViewHolder(View itemView) {
        super(itemView);

        childTextView = (CustomTextView)itemView.findViewById(R.id.childtext);

    }

}
