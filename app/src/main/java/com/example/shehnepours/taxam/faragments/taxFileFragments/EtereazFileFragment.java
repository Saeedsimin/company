package com.example.shehnepours.taxam.faragments.taxFileFragments;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v4.graphics.BitmapCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.shehnepours.taxam.R;
import com.example.shehnepours.taxam.SIPControl.WalkieTalkieActivity;
import com.example.shehnepours.taxam.adapter.ImagePreviewAdapter;
import com.example.shehnepours.taxam.parents.CustomButton;
import com.example.shehnepours.taxam.parents.CustomTextView;
import com.example.shehnepours.taxam.parents.ExpandableHeightGridView;
import com.example.shehnepours.taxam.utils.En2FaNumber;
import com.example.shehnepours.taxam.utils.PersianDate;
import com.example.shehnepours.taxam.utils.PickImageDialogHandler;
import com.example.shehnepours.taxam.utils.TimePickHandler;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.zfdang.multiple_images_selector.ImagesSelectorActivity;
import com.zfdang.multiple_images_selector.SelectorSettings;

import java.io.File;
import java.net.URI;
import java.nio.channels.Selector;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by shehnepour.s on 12/25/2017.
 */

public class EtereazFileFragment extends Fragment {

    private static final int REQUEST_CODE = 732;

    private View view;
    private ImageButton eterazPickImageButton;
    private CustomTextView dateTextView;
    private CustomButton confirmButton;
    private CustomButton layeheButton;
    private CustomButton consultBtn;
    private TimePickHandler dateHandler = new TimePickHandler(getActivity(),  dateTextView);
    private ExpandableHeightGridView gridView;



    private ImagePreviewAdapter previewAdapter;

    private String timeStamp;
    private String[] currentDate;
    private ArrayList<String> mResults = new ArrayList<>();
    private ArrayList<Bitmap> scaledPics = new ArrayList<>();
    private ArrayList<Bitmap> originalImages = new ArrayList<>();
    private ArrayList<String> encodedImages = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_eteraz_fragment,container,false);
        Fresco.initialize(getContext());
        eterazPickImageButton = (ImageButton) view.findViewById(R.id.pick_image_eteraz);
        dateTextView = (CustomTextView) view.findViewById(R.id.eteraz_date);
        confirmButton = (CustomButton) view.findViewById(R.id.eteraz_btn);
        layeheButton = (CustomButton) view.findViewById(R.id.layehe_eteraz_btn);
        gridView = (ExpandableHeightGridView) view.findViewById(R.id.preview_grid);
        dateHandler = new TimePickHandler(getActivity(),  dateTextView);
        consultBtn = (CustomButton)view.findViewById(R.id.consult_btn);

        consultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), WalkieTalkieActivity.class);
                startActivity(intent);
            }
        });

        currentDate = getCurrentDate();
        dateTextView.setText(currentDate[0] + "-" + currentDate[1] + "-" + currentDate[2]);
        Log.i("ETERAZ_DATE", "onCreateView: " + timeStamp.toString());



        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (dateHandler.getEnDate().length() != 0) {
                    timeStamp = dateHandler.getEnDate();
                }
                Log.i("ETERAZ_DATE", "onClick: "+ timeStamp);
                Log.i("ENCODED_SIZE", "onClick: " + encodedImages.size());
            }
        });

        dateTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateHandler = new TimePickHandler(getActivity(),  dateTextView);
                dateHandler.onRecieveDate();
            }
        });





        eterazPickImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(getContext(), ImagesSelectorActivity.class);
                intent.putExtra(SelectorSettings.SELECTOR_MAX_IMAGE_NUMBER,5);
                intent.putExtra(SelectorSettings.SELECTOR_SHOW_CAMERA,true);
                startActivityForResult(intent,REQUEST_CODE);


//                List<Bitmap> pics;
//                ImagePreviewAdapter previewAdapter = new ImagePreviewAdapter(getContext(),pics);


            }
        });

        setupBackButton();
        return view;

    }
    private void setupBackButton() {

        TextView backIcon;
        backIcon = (TextView)getActivity().findViewById(R.id.back_btn);
        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getFragmentManager().popBackStack();
            }
        });
        view.setFocusableInTouchMode(true);
        view.requestFocus();
        view.setOnKeyListener( new View.OnKeyListener()
        {
            @Override
            public boolean onKey( View v, int keyCode, KeyEvent event )
            {
                if( keyCode == KeyEvent.KEYCODE_BACK )
                {
                    getFragmentManager().popBackStack();
                    return true;
                }
                return false;
            }
        } );
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQUEST_CODE) {
            Log.i("RESULT_CODE", "onActivityResult: " + resultCode);
            if(resultCode == AppCompatActivity.RESULT_OK) {



                mResults = data.getStringArrayListExtra(SelectorSettings.SELECTOR_RESULTS);
                assert mResults != null;

                for(int i = 0; i<mResults.size();i++) {
                    File sd = Environment.getExternalStorageDirectory();
                    Log.i("PIC_RESULTS", "onActivityResult: " + mResults.get(i));
                    File image = new File(mResults.get(i));
                    Log.i("FILE_RESULT", "onActivityResult: " + image.getTotalSpace());
                    BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                    Bitmap orBitmap = BitmapFactory.decodeFile(mResults.get(i));
                    Bitmap bitmap = PickImageDialogHandler.rotateImage(orBitmap,90);
                    Bitmap scaled = PickImageDialogHandler.scaleImage(bitmap);
                    String encoded = PickImageDialogHandler.encodeImage(bitmap);
                    Log.i("BITMAP_RESULT", "onActivityResult: " + scaled.getByteCount());
                    originalImages.add(bitmap);
                    encodedImages.add(encoded);
                    scaledPics.add(scaled);

                }
                previewAdapter = new ImagePreviewAdapter(getContext(),scaledPics,encodedImages,originalImages,mResults,view,getActivity().getSupportFragmentManager(),"ET");
                gridView.setAdapter(previewAdapter);
                gridView.setExpanded(true);

            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private String[] getCurrentDate() {
        timeStamp = new SimpleDateFormat("yyyy-MM-dd").format(Calendar.getInstance().getTime());
        PersianDate date = new PersianDate();
        int[] calenderArray = new int[3];
        calenderArray = date.toJalali(Integer.parseInt(timeStamp.substring(0,4)),Integer.parseInt(timeStamp.substring(5,7)),Integer.parseInt(timeStamp.substring(8,10)));
        String yearDate = "";
        String monthDate = "";
        String dayDate = "";
        String[] dateArray = new String[3];



        timeStamp = Integer.valueOf(calenderArray[0]).toString() + "-" + Integer.valueOf(calenderArray[1]).toString() + "-"  + Integer.valueOf(calenderArray[2]).toString();
        for (int i = 0; i< Integer.valueOf(calenderArray[0]).toString().length(); i++) {
            yearDate = yearDate.concat(En2FaNumber.En2FaChanger(Integer.valueOf(calenderArray[0]).toString().substring(i,i+1)));
        }
        for (int i = 0; i< Integer.valueOf(calenderArray[1]).toString().length(); i++) {
            monthDate = monthDate.concat(En2FaNumber.En2FaChanger(Integer.valueOf(calenderArray[1]).toString().substring(i,i+1)));
        }
        for (int i = 0; i< Integer.valueOf(calenderArray[2]).toString().length(); i++) {
            dayDate = dayDate.concat(En2FaNumber.En2FaChanger(Integer.valueOf(calenderArray[2]).toString().substring(i,i+1)));
        }

        dateArray[0] = yearDate;
        dateArray[1] = monthDate;
        dateArray[2] = dayDate;

        return dateArray;
    }
}
