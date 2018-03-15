package com.example.shehnepours.taxam.utils;

import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.os.Environment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.util.Base64;
import android.util.Log;
import android.view.Gravity;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.shehnepours.taxam.R;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.enums.EPickType;
import com.vansuita.pickimage.listeners.IPickResult;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

/**
 * Created by shehnepour.s on 1/2/2018.
 */

public class PickImageDialogHandler {

    private static final int THUMBNAIL_WIDTH = 300;
    private static final int THUMBNAIL_HEIGHT = 500;

    private static String encodedImage = "";


    public static PickSetup setupDialog(final Activity activity, Fragment fragment, final ImageView imageView) {
        PickImageDialog pickImageDialog = new PickImageDialog();
        PickSetup setup = new PickSetup()
                .setTitle("").setWidth(300).setHeight(900);
        setup.setCameraButtonText("دوربین");
        setup.setGalleryButtonText("گالری");
        setup.setCancelText("").setPickTypes(EPickType.GALLERY, EPickType.CAMERA);
        setup.setButtonOrientation(LinearLayoutCompat.HORIZONTAL);
        setup.setCameraIcon(R.drawable.ic_camera_green_4);
        setup.setGalleryIcon(R.drawable.ic_gallery_green_2);
        setup.setIconGravity(Gravity.TOP);
        setup.setProgressText("در حال پردازش ...");
        return setup;
    }

    public static void saveFileToInternalStorage(Bitmap bitmap, Activity activity) {
        ContextWrapper cw = new ContextWrapper(activity);
        File imageDir = new File(Environment.getExternalStorageDirectory() +"/TAXAM/Images/");//("TAXAM", Context.MODE_PRIVATE);
        Log.i("FileDirectory", "saveFileToInternalStorage: " + imageDir);
        imageDir.mkdirs();
        File imageFile = new File(imageDir,new Date().getTime() +".jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(imageFile);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                Toast.makeText(activity,"در ذخیره‌سازی تصویر بر روی حافظه شما مشکلی پیش آمد، لطفا از کافی بودن حافظه خود اطمینان حاصل پیدا کنید",Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static Bitmap scaleImage(Bitmap bitmap) {
        int nh = (int) (bitmap.getHeight()*(512.0/bitmap.getWidth()));
        Bitmap scaled = Bitmap.createScaledBitmap(bitmap,THUMBNAIL_WIDTH,THUMBNAIL_HEIGHT,true);
        return scaled;
    }

    public static Bitmap scalePreviewImage(Bitmap bitmap, ImageView view) {
        Bitmap scaled = Bitmap.createScaledBitmap(bitmap, 250,400,true);
        return scaled;
    }
    public static Bitmap rotateImage(Bitmap img,int degree) {
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap rotatedImg = Bitmap.createBitmap(img, 0, 0, img.getWidth(),img.getHeight(), matrix, true);
        return rotatedImg;
    }


    public static String encodeImage (Bitmap bitmap) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,100,baos);
        byte[] imageBytes = baos.toByteArray();
        String encoded = Base64.encodeToString(imageBytes,Base64.DEFAULT);
        return encoded;
    }


}
