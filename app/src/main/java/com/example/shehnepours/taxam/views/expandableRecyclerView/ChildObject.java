package com.example.shehnepours.taxam.views.expandableRecyclerView;

/**
 * Created by shehnepour.s on 12/26/2017.
 */

public class ChildObject {

    private String title;
    private int id;

    public ChildObject(String title, int id) {
        this.title = title;
        this.id = id;
    }



    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
