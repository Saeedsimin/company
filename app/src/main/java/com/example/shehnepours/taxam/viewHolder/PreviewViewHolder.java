package com.example.shehnepours.taxam.viewHolder;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.example.shehnepours.taxam.R;

/**
 * Created by shehnepour.s on 1/20/2018.
 */

public class PreviewViewHolder extends Fragment {

    private View previewHolder;
    private FrameLayout container;
    private ImageView preview;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        previewHolder = inflater.inflate(R.layout.layout_image_preview_holder,container,false);
        container = (FrameLayout)previewHolder.findViewById(R.id.container);
        preview = (ImageView)previewHolder.findViewById(R.id.expanded_imageview);

        return previewHolder;
    }
}
