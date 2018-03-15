package com.example.shehnepours.taxam.views.expandableRecyclerView;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;

import java.util.List;

/**
 * Created by shehnepour.s on 12/26/2017.
 */

public class ChildrenListHandler implements ParentObject {
    private List<Object> childrenList;
    private String title;


    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return title;
    }
    @Override
    public List<Object> getChildObjectList() {
        return childrenList;
    }

    @Override
    public void setChildObjectList(List<Object> list) {
        childrenList = list;
    }
}
