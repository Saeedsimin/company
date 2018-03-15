package com.example.shehnepours.taxam.faragments.utils;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.shehnepours.taxam.R;
import com.example.shehnepours.taxam.constants.Variables;
import com.example.shehnepours.taxam.utils.PickImageDialogHandler;

import java.io.File;

/**
 * Created by shehnepour.s on 1/24/2018.
 */

public class EnlargeFragment extends Fragment {

    private View view;
    private ImageView enlargedImage;
    private String imageUri;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_enlarge_preview,container,false);
        enlargedImage = (ImageView) view.findViewById(R.id.expanded_imageview);
        imageUri = getArguments().getString(Variables.IMAGE_URI);
        Bitmap bitmap = BitmapFactory.decodeFile(imageUri);
        Log.i("ENLARGE_PICTURE", "onCreateView: " + enlargedImage.getWidth());
        Bitmap rotated = PickImageDialogHandler.rotateImage(bitmap,90);
        Bitmap scaled = PickImageDialogHandler.scalePreviewImage(rotated,enlargedImage);
        enlargedImage.setImageBitmap(scaled);

        enlargedImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });

        return view;
    }
}
