package com.example.shehnepours.taxam.adapter;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.app.BundleCompat;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.shehnepours.taxam.R;
import com.example.shehnepours.taxam.constants.Variables;
import com.example.shehnepours.taxam.faragments.utils.EnlargeFragment;
import com.example.shehnepours.taxam.utils.EnlargeImage;
import com.example.shehnepours.taxam.utils.PickImageDialogHandler;
import com.squareup.picasso.Picasso;

import java.io.IOException;
import java.util.List;

/**
 * Created by shehnepour.s on 1/16/2018.
 */

public class ImagePreviewAdapter extends BaseAdapter {


    View v;
    ImageView picture;
    ImageView cancel;

    private Context context;
    private List<Bitmap> pics;
    private List<Bitmap> originalImages;
    private List<String> encoded;
    private List<String> imageUri;


    private FragmentManager fragmentManager;
    private View view;
    private EnlargeImage enlargeImage;
    private String type;
    private final LayoutInflater mInflater;

    public ImagePreviewAdapter(Context context, List<Bitmap> pics, List<String> encoded, List<Bitmap> originalImages, List<String> imageUri,View view, FragmentManager fragmentManager, String type) {
        this.originalImages = originalImages;
        Animator animator = null;
        this.type = type;
        this.imageUri = imageUri;
//        enlargeImage = new EnlargeImage(animator,view,type);
        this.view = view;
        this.context = context;
        this.pics = pics;
        this.encoded = encoded;
        this.fragmentManager = fragmentManager;
        mInflater = LayoutInflater.from(context);
    }


    @Override
    public int getCount() {
        return pics.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {

        if (convertView == null) {
            v = mInflater.inflate(R.layout.layout_image_preview_holder, parent, false);
            v.setTag(R.id.picture, v.findViewById(R.id.picture));
            v.setTag(R.id.cancel_icon,v.findViewById(R.id.cancel_icon));
        } else
            v = convertView;

        picture = (ImageView) v.getTag(R.id.picture);
        cancel    = (ImageView) v.getTag(R.id.cancel_icon);
        picture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                ImageView preview= (ImageView) view.findViewById(R.id.expanded_imageview);
//                preview.setImageBitmap(PickImageDialogHandler.scalePreviewImage(originalImages.get(position),preview));
//                preview.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//
//                    }
//                });
//                enlargeImage.zoomImageFromThumb(picture,originalImages.get(position));
//                String Uri = imageUri.get(position);
//                goToPreviewFragment(new EnlargeFragment(),Uri);
                final Dialog nagDialog = new Dialog(view.getContext(),R.style.DialogSlideAnim);
                nagDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                nagDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                nagDialog.setCancelable(false);
                nagDialog.setContentView(R.layout.layout_enlarge_preview);
                ImageView ivPreview = (ImageView)nagDialog.findViewById(R.id.expanded_imageview);
                ivPreview.setMaxHeight(300);
                ivPreview.setMaxWidth(80);
                ivPreview.setImageBitmap(PickImageDialogHandler.scalePreviewImage(originalImages.get(position),ivPreview));
//                ivPreview.setImageBitmap(originalImages.get(position));
//                Picasso.with(context).load(imageUri.get(position)).fit().centerCrop().into(ivPreview);
                ivPreview.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {

                        nagDialog.dismiss();
                    }
                });
                nagDialog.show();

            }
        });

        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                originalImages.remove(position);
                encoded.remove(position);
                pics.remove(position);
                ImagePreviewAdapter.this.notifyDataSetChanged();
            }
        });


        picture.setImageBitmap(pics.get(position));
        return v;
    }

    private void goToPreviewFragment(Fragment fragment, String imageUri) {
        Bundle bundle = new Bundle();
        bundle.putString(Variables.IMAGE_URI,imageUri);
        fragment.setArguments(bundle);
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.newtax_frame_layout,fragment).commit();
        transaction.addToBackStack(Variables.ENLARGE_FRAGMENT);

    }

//    public class ImagePreviewViewHolder extends RecyclerView.ViewHolder {
//
//        private ImageView preview;
//
//        public ImagePreviewViewHolder(View itemView) {
//            super(itemView);
//            preview = (ImageView)itemView.findViewById(R.id.preview_holder);
//
//        }
//    }

}
