package com.example.shehnepours.taxam.adapter;

import android.content.Context;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.Adapter.ExpandableRecyclerAdapter;
import com.bignerdranch.expandablerecyclerview.ClickListeners.ParentItemClickListener;
import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ChildViewHolder;
import com.bignerdranch.expandablerecyclerview.ViewHolder.ParentViewHolder;
import com.example.shehnepours.taxam.R;
import com.example.shehnepours.taxam.parents.CustomButton;
import com.example.shehnepours.taxam.utils.FontsHandeller;
import com.example.shehnepours.taxam.views.expandableRecyclerView.ChildObject;
import com.example.shehnepours.taxam.views.expandableRecyclerView.ChildrenListHandler;
import com.example.shehnepours.taxam.views.expandableRecyclerView.CustomChildViewHolder;
import com.example.shehnepours.taxam.views.expandableRecyclerView.CustomParentViewHolder;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;

/**
 * Created by shehnepour.s on 12/24/2017.
 */

public class ExpandableAdapter extends ExpandableRecyclerAdapter<ParentViewHolder,ChildViewHolder>{

    public LayoutInflater inflater;
    public Context context;
    public CustomChildViewHolder childViewHolder;
    public CustomParentViewHolder parentViewHolder;
    public ChildrenListHandler handler;
    public ChildObject childObject;
    public int selectedChildId = 0;



    public ExpandableAdapter(Context context, List<ParentObject> parentItemList) {
        super(context, parentItemList);
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public ParentViewHolder onCreateParentViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.layout_listview_parenttext,viewGroup,false);
        return new CustomParentViewHolder(view);
    }

    @Override
    public ChildViewHolder onCreateChildViewHolder(ViewGroup viewGroup) {
        View view = inflater.inflate(R.layout.layout_listview_childtext,viewGroup,false);
        return new CustomChildViewHolder(view);
    }

    @Override
    public void onBindParentViewHolder(ParentViewHolder viewHolder, int i, Object o) {
        parentViewHolder = (CustomParentViewHolder)viewHolder;
        handler = (ChildrenListHandler)o;
        parentViewHolder.parentTextView.setText(handler.getTitle());
    }

    @Override
    public void onBindChildViewHolder(final ChildViewHolder viewHolder, int i, Object o) {
        childViewHolder = (CustomChildViewHolder) viewHolder;
        final ChildObject child = (ChildObject)o;
        childViewHolder.childTextView.setText(child.getTitle());
        childObject = child;
        childViewHolder.childTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedChildId = child.getId();
                parentViewHolder.parentTextView.setText(child.getTitle());

            }
        });
    }

    @Override
    public void onParentItemClickListener(int position) {
        super.onParentItemClickListener(position);
    }

    public CustomChildViewHolder getChildViewHolder(){
        return childViewHolder;
    }

    public ChildObject getChildObject() {
        return childObject;
    }

    public int getSelectedChildId() {
        return selectedChildId;
    }




}
