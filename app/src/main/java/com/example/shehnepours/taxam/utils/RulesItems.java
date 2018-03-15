package com.example.shehnepours.taxam.utils;

/**
 * Created by shehnepour.s on 3/2/2018.
 */

public class RulesItems {
    private String title;
    private int hasSubItem;

    public RulesItems(String title, int hasSubItem) {
        this.title = title;
        this.hasSubItem = hasSubItem;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getHasSubItem() {
        return hasSubItem;
    }

    public void setHasSubItem(int hasSubItem) {
        this.hasSubItem = hasSubItem;
    }
}
