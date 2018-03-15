package com.example.shehnepours.taxam.views.expandableRecyclerView;

import android.view.View;
import android.widget.ImageButton;

import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.example.shehnepours.taxam.R;
import com.example.shehnepours.taxam.parents.CustomEditText;
import com.example.shehnepours.taxam.parents.CustomTextView;

/**
 * Created by shehnepour.s on 12/26/2017.
 */

public class CustomParentViewHolder extends ParentViewHolder {
    public CustomTextView parentTextView;
    public ImageButton parentImageButton;

    public CustomParentViewHolder(View itemView) {
        super(itemView);

        parentTextView = (CustomTextView)itemView.findViewById(R.id.parenttext);
        parentImageButton = (ImageButton)itemView.findViewById(R.id.arrow_image);

    }

    @Override
    public void setExpanded(boolean isExpanded) {
        super.setExpanded(isExpanded);
        if(isExpanded) {

        } else {
        }


    }
}
