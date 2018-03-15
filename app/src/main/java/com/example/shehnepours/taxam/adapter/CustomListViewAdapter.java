package com.example.shehnepours.taxam.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.IdRes;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.example.shehnepours.taxam.R;
import com.example.shehnepours.taxam.parents.CustomTextView;
import com.example.shehnepours.taxam.utils.RulesItems;

import java.util.ArrayList;

/**
 * Created by shehnepour.s on 3/2/2018.
 */

public class CustomListViewAdapter extends ArrayAdapter<RulesItems> {
    private final Context context;
    private final ArrayList<RulesItems> arrayList;
    private int initialElevation = 12;

    public CustomListViewAdapter(@NonNull Context context, ArrayList<RulesItems> arrayList) {
        super(context, R.layout.layout_row_listview, arrayList);
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.layout_row_listview,parent,false);

        CustomTextView title = (CustomTextView) rowView.findViewById(R.id.rule_title_text);
        ImageView arrowImage = (ImageView) rowView.findViewById(R.id.arrow_image);

        title.setText(arrayList.get(position).getTitle());
        title.setStateListAnimator(null);
        title.setBackgroundColor(Color.WHITE);

        title.setElevation((float)(initialElevation-position));
        arrowImage.setElevation((float)(initialElevation-position));
        if(arrayList.get(position).getHasSubItem() == 0) {
            arrowImage.setVisibility(View.GONE);
        }
        return rowView;
    }
}
