package com.example.shehnepours.taxam.faragments.taxFileFragments;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.expandablerecyclerview.Model.ParentObject;
import com.example.shehnepours.taxam.R;
import com.example.shehnepours.taxam.SIPControl.WalkieTalkieActivity;
import com.example.shehnepours.taxam.SharedPreferenceManager.TaxFileSharedPrefManager;
import com.example.shehnepours.taxam.SharedPreferenceManager.TaxPictureSharedPreference;
import com.example.shehnepours.taxam.adapter.ExpandableAdapter;
import com.example.shehnepours.taxam.adapter.ImagePreviewAdapter;
import com.example.shehnepours.taxam.apiServices.taxFormServices.SendImageApiService;
import com.example.shehnepours.taxam.apiServices.taxFormServices.TaxFormApiService;
import com.example.shehnepours.taxam.constants.Variables;
import com.example.shehnepours.taxam.datamodel.TaxFile;
import com.example.shehnepours.taxam.datamodel.TaxPicture;
import com.example.shehnepours.taxam.faragments.taxFileFragments.addedValueFragments.AddedValueAgreementFragment;
import com.example.shehnepours.taxam.faragments.taxFileFragments.operationalFragments.OperationalAgreementFragment;
import com.example.shehnepours.taxam.parents.CustomButton;
import com.example.shehnepours.taxam.parents.CustomEditText;
import com.example.shehnepours.taxam.parents.CustomTextView;
import com.example.shehnepours.taxam.parents.ExpandableHeightGridView;
import com.example.shehnepours.taxam.parents.ShadowEditText;
import com.example.shehnepours.taxam.utils.PickImageDialogHandler;
import com.example.shehnepours.taxam.utils.TimePickHandler;
import com.example.shehnepours.taxam.views.expandableRecyclerView.ChildObject;
import com.example.shehnepours.taxam.views.expandableRecyclerView.ChildrenListHandler;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;
import com.zfdang.multiple_images_selector.ImagesSelectorActivity;
import com.zfdang.multiple_images_selector.SelectorSettings;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


/**
 * Created by shehnepour.s on 12/24/2017.
 */

public class NewTaxFormFragment extends Fragment {

    private static final int REQUEST_CODE = 732;

    private View view;
    private CustomButton confirmButton;
    private CustomButton objectionButton;
    private CustomButton ruleButton;
    private CustomButton yourDecisionButton;
    private CustomButton agreementButton;
    private CustomButton eblaghDateButton;
    private CustomButton consultBtn;
    private ShadowEditText eblaghTakhasosEditText;
    private ShadowEditText eblaghEbrazEditText;
    private ShadowEditText eblaghYearEditText;
    private ImageButton taxFormImageButton;
    private ProgressDialog dialog;
    private ImageView previewImageView;
    private CustomTextView previewCaptionTextView;
    private TimePickHandler dateHandler;
    private FragmentManager fragmentManager;
    private RecyclerView taxTypeRecyclerView;
    private RecyclerView stepRecyclerView;
    private ExpandableAdapter taxTypeAdapter;
    private ExpandableAdapter stepAdapter;
    private PickSetup setup;
    private TaxFileSharedPrefManager taxFileSharedPrefManager;
    private TaxPictureSharedPreference taxPictureSharedPreference;
    private ImagePreviewAdapter previewAdapter;
    private ExpandableHeightGridView gridView;

    private String taxType;
    private String year;
    private String marhale;
    private String letterType;
    private int tashkhisi;
    private int ebrazi;
    private String eblaghDate;
    private String pictureURI;
    private String encodedImage = "";
    private String timeStamp;

    private int imageSuccess = 1;
    private ArrayList<String> mResults = new ArrayList<>();
    private ArrayList<Bitmap> originalImages = new ArrayList<>();
    private ArrayList<Bitmap> scaledPics = new ArrayList<>();
    private ArrayList<String> encodedImages = new ArrayList<>();


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_newtaxform_fragment,container,false);
        Fresco.initialize(getContext());
        taxTypeRecyclerView = (RecyclerView) view.findViewById(R.id.tax_type_form);
        stepRecyclerView = (RecyclerView) view.findViewById(R.id.step_form);
        ruleButton = (CustomButton)view.findViewById(R.id.rules_taxform_btn);
        yourDecisionButton = (CustomButton)view.findViewById(R.id.yourdecision_taxform_btn);
        agreementButton = (CustomButton)view.findViewById(R.id.iagree_taxform_btn);
        confirmButton = (CustomButton)view.findViewById(R.id.confirminfo_taxform_btn);
        objectionButton = (CustomButton) view.findViewById(R.id.eteraz_taxform_btn);
        eblaghDateButton = (CustomButton) view.findViewById(R.id.eblagh_date_btn);
        gridView = (ExpandableHeightGridView) view.findViewById(R.id.preview_grid);
        eblaghYearEditText = (ShadowEditText)view.findViewById(R.id.year_edittext);
        eblaghTakhasosEditText = (ShadowEditText)view.findViewById(R.id.eblagh_taskhis_edittext);
        eblaghEbrazEditText = (ShadowEditText)view.findViewById(R.id.eblagh_ebraz_edittext);
        fragmentManager = getActivity().getSupportFragmentManager();
        taxFormImageButton = (ImageButton)view.findViewById(R.id.taxform_imagebutton);
        consultBtn = (CustomButton)view.findViewById(R.id.consult_btn);


        Log.i("", "onCreateView: ");


        setupListViewAdapter();

        consultBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(getContext(), WalkieTalkieActivity.class);
                startActivity(in);
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkForCorrectInput();
            }
        });

        taxFormImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    PickImage();
            }
        });

        eblaghDateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateHandler = new TimePickHandler(getActivity(),  eblaghDateButton);
                dateHandler.onRecieveDate();

            }
        });



        objectionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFragment(new EtereazFileFragment());

            }
        });
        agreementButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(taxTypeAdapter.getSelectedChildId() == 1) {
                    goToFragment(new OperationalAgreementFragment());
                } else if(taxTypeAdapter.getSelectedChildId() == 2) {
                    goToFragment(new AddedValueAgreementFragment());
                }
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
                getActivity().getSupportFragmentManager().popBackStack();
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
                    getActivity().getSupportFragmentManager().popBackStack();
                    return true;
                }
                return false;
            }
        } );
    }

    private void PickImage() {

        Intent intent = new Intent(getContext(), ImagesSelectorActivity.class);
        intent.putExtra(SelectorSettings.SELECTOR_MAX_IMAGE_NUMBER,5);
        intent.putExtra(SelectorSettings.SELECTOR_SHOW_CAMERA,true);
        startActivityForResult(intent,REQUEST_CODE);

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
                previewAdapter = new ImagePreviewAdapter(getContext(),scaledPics,encodedImages,originalImages,mResults,view,fragmentManager,"NT");
                gridView.setAdapter(previewAdapter);
                gridView.setExpanded(true);
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    private void setupCaptionText() {
            if(stepAdapter.getSelectedChildId() == 1) {
                previewCaptionTextView.setText("پیش‌نمایش برگه تشخیص");
            } else if (stepAdapter.getSelectedChildId() == 2) {
                previewCaptionTextView.setText("پیش‌نمایش برگه هیئت حل اختلاف");
            } else if (stepAdapter.getSelectedChildId() == 3) {
                previewCaptionTextView.setText("پیش‌نمایش برگه هیئت تجدید نظر");
            } else if (stepAdapter.getSelectedChildId() == 4) {
                previewCaptionTextView.setText("پیش‌نمایش برگه شورای عالی مالی");
            } else if (stepAdapter.getSelectedChildId() == 5) {
                previewCaptionTextView.setText("پیش‌نمایش برگه ماده ۲۵۱ مکرر");
            } else if (stepAdapter.getSelectedChildId() == 6) {
                previewCaptionTextView.setText("پیش‌نمایش برگه دیوان محاسبات");
            }
    }

    private void checkForCorrectInput() {
        if (taxTypeAdapter.getSelectedChildId() == 0) {
            Toast.makeText(getContext(),"لطفا نوع مالیات را انتخاب کنید", Toast.LENGTH_SHORT).show();
        } else if (eblaghYearEditText.getText().toString().length()!=4){
            Toast.makeText(getContext(),"لطفا سال مربوط به پرونده را وارد کنید", Toast.LENGTH_SHORT).show();
        } else if (Integer.parseInt(eblaghYearEditText.getText().toString())>1400 || Integer.parseInt(eblaghYearEditText.getText().toString())<1390) {
            Toast.makeText(getContext(),"سال وارد شده نامعتبر است", Toast.LENGTH_SHORT).show();
        } else if (stepAdapter.getSelectedChildId() == 0 ) {
            Toast.makeText(getContext(),"لطفا مرحله مربوط به پرونده را وارد کنید", Toast.LENGTH_SHORT).show();
        }else if (eblaghTakhasosEditText.getText().toString().length() == 0 ) {
            Toast.makeText(getContext(),"لطفا مبلغ مربوط به مالیات تشخیصی را وارد کنید", Toast.LENGTH_SHORT).show();
        } else if (!(eblaghDateButton.getText().toString().contains("-"))) {
            Toast.makeText(getContext(),"لطفا تاریخ ابلاغ پرونده را وارد کنید", Toast.LENGTH_SHORT).show();
        } else if (eblaghEbrazEditText.getText().toString().length() == 0 ) {
            Toast.makeText(getContext(),"لطفا مبلغ مربوط به مالیات ابرازی را وارد کنید", Toast.LENGTH_SHORT).show();
        }
        else{
            onSendTaxFormInfo();
        }
    }

    private void onSendTaxFormInfo() {
        dialog = ProgressDialog.show(getContext(),"",getString(R.string.info_conf),true);
        getInputFormUser();
        if (encodedImage.length() != 0) {
            sendImageInfo();
        }
        sendTaxInfo();
    }

    private void sendImageInfo() {

        SendImageApiService apiService = new SendImageApiService(getContext());
        JSONObject requestJsonObject = new JSONObject();
        try {
            requestJsonObject.put(Variables.DATE,timeStamp);
            requestJsonObject.put(Variables.TAX_PICTURE_ITEM,encodedImage);
            requestJsonObject.put(Variables.SHENASE_MELI,1);
            requestJsonObject.put(Variables.YEAR_KEY,year);
            requestJsonObject.put(Variables.TAX_TYPE,taxType);
            Log.i("JSON_OBJECT", "sendImageInfo: " + Variables.DATE + " " + timeStamp);
            Log.i("JSON_OBJECT", "sendImageInfo: " + Variables.TAX_PICTURE_ITEM+" "+ encodedImage);
            Log.i("JSON_OBJECT", "sendImageInfo: " + Variables.SHENASE_MELI + " " + 1);
            Log.i("JSON_OBJECT", "sendImageInfo: " + Variables.YEAR_KEY+" "+ year);
            Log.i("JSON_OBJECT", "sendImageInfo: " + Variables.TAX_TYPE + " " + taxType);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        apiService.onSendImageConfirmation(requestJsonObject, new SendImageApiService.OnSendImageConfirmationComplete() {
            @Override
            public void onSendImageConfirmation(int success) {
                if (success == 2) {
                    imageSuccess = 0;
                }
            }
        });
    }

    private void sendTaxInfo() {
        TaxFormApiService apiService = new TaxFormApiService(getContext());
        JSONObject requestJsonObject = new JSONObject();
        try {
            requestJsonObject.put(Variables.YEAR_KEY,year);
            requestJsonObject.put(Variables.TARIKH_EBLAGH,eblaghDate);
            requestJsonObject.put(Variables.MALIAT_EBRAZI,ebrazi);
            requestJsonObject.put(Variables.MALIAT_TASHKHISI,tashkhisi);
            requestJsonObject.put(Variables.LETTER_TYPE,letterType);
            requestJsonObject.put(Variables.MARHALE,marhale);
            requestJsonObject.put(Variables.SHENASE_MELI,1);
            requestJsonObject.put(Variables.TAX_TYPE,taxType);
            Log.i("JSON_OBJECT", "sendTaxInfo: " +Variables.YEAR_KEY + " " + year);
            Log.i("JSON_OBJECT", "sendTaxInfo: " +Variables.TARIKH_EBLAGH + " " + eblaghDate);
            Log.i("JSON_OBJECT", "sendTaxInfo: " +Variables.MALIAT_EBRAZI + " " + ebrazi);
            Log.i("JSON_OBJECT", "sendTaxInfo: " +Variables.MALIAT_TASHKHISI + " " + tashkhisi);
            Log.i("JSON_OBJECT", "sendTaxInfo: " +Variables.LETTER_TYPE + " " + letterType);
            Log.i("JSON_OBJECT", "sendTaxInfo: " +Variables.MARHALE + " " + marhale);
            Log.i("JSON_OBJECT", "sendTaxInfo: " +Variables.SHENASE_MELI + " " + 1);
            Log.i("JSON_OBJECT", "sendTaxInfo: " +Variables.TAX_TYPE + " " + taxType);



        } catch (JSONException e) {
            e.printStackTrace();
        }
        apiService.onSendTaxFormConfirmation(requestJsonObject, new TaxFormApiService.OnSendTaxFormConfirmationComplete() {
            @Override
            public void onSendTaxFormConfirmation(int success) {
                dialog.dismiss();

                if (success == 3) {
                    Toast.makeText(getContext(),"در ارتباط با سرور خطایی رخ داد، لطفا مجددا تلاش کنید",Toast.LENGTH_SHORT).show();
                } else if (success == 2) {
                    Toast.makeText(getContext(),"این پرونده قبلا ایجاد و ثبت گردیده است",Toast.LENGTH_SHORT).show();
                } else if (success == 1 && imageSuccess == 0) {
                    Toast.makeText(getContext(),"در ثبت تصویر مشکلی پیش آمد، لطفا دوباره اقدام کنید",Toast.LENGTH_SHORT).show();
                } else if (success == 1 && imageSuccess == 1) {
                    Toast.makeText(getContext(),"پرونده با موفقیت ثبت گردید",Toast.LENGTH_SHORT).show();
                    saveTaxFileToSharedPreference();
                    if (encodedImage.length() != 0) {
                        saveTaxImageToSharedPreference();
                    }
                    saveTaxFileToSharedPreference();
                    beginEnableRestOfElemetns();
                }

            }
        });

    }

    private void saveTaxImageToSharedPreference() {
        TaxPicture taxPicture = new TaxPicture();
        taxPicture.setShenase_melli(1);
        taxPicture.setTax_type(taxType);
        taxPicture.setDate(timeStamp);
        taxPicture.setSal(year);
        taxPicture.setTaxPictureItem("");

        taxPictureSharedPreference = new TaxPictureSharedPreference(getContext());
        taxPictureSharedPreference.saveTaxPicture(taxPicture);
    }

    private void saveTaxFileToSharedPreference() {
        TaxFile taxFile = new TaxFile();
        taxFile.setTarikh_eblagh(eblaghDate);
        taxFile.setSal(year);
        taxFile.setMaliat_ebrazi(ebrazi);
        taxFile.setMaliat_tashkhisi(tashkhisi);
        taxFile.setLetter_type(taxType);
        taxFile.setMarhale(marhale);
        taxFile.setAgreement_amount(0);
        taxFile.setShenase_melli(1);
        taxFile.setTax(0);
        taxFile.setTax_type(taxType);
        taxFile.setAvarez(0);
        taxFile.setJarime(0);

        taxFileSharedPrefManager = new TaxFileSharedPrefManager(getContext());
        taxFileSharedPrefManager.saveTaxFile(taxFile);
    }

    private void getInputFormUser() {
        if(taxTypeAdapter.getSelectedChildId() == 1) {
            taxType = Variables.OPERATIONAL_TYPE;
        } else {
            taxType = Variables.VALUE_ADDED_TYPE;
        }

        year = eblaghYearEditText.getText().toString();

        if(stepAdapter.getSelectedChildId() == 1) {
            marhale = "1";
            letterType = Variables.TASHKHIS;
        } else if (stepAdapter.getSelectedChildId() == 2) {
            marhale = "2";
            letterType = Variables.EKHTELAF;
        } else if (stepAdapter.getSelectedChildId() == 3) {
            marhale = "3";
            letterType = Variables.TAJDID;
        } else if (stepAdapter.getSelectedChildId() == 4) {
            marhale = "4";
            letterType = Variables.SHORA;
        } else if (stepAdapter.getSelectedChildId() == 5) {
            marhale = "5";
            letterType = Variables.M251;
        } else if (stepAdapter.getSelectedChildId() == 6) {
            marhale = "6";
            letterType = Variables.DIVAN;
        }

        tashkhisi = Integer.parseInt(eblaghTakhasosEditText.getText().toString());
        ebrazi = Integer.parseInt(eblaghEbrazEditText.getText().toString());

        eblaghDate = dateHandler.getEnDate();
        Log.i("EBLAGH_DATE", "getInputFormUser: " + eblaghDate);
//        char[] dateTemp = temp.toCharArray();
//        dateTemp[4] = '-';
//        dateTemp[7] = '-';
//        eblaghDate = dateTemp.toString();
//        Log.i("EBLAGH_DATE", "getInputFormUser: " + eblaghDate);

    }


    private void beginEnableRestOfElemetns() {
        confirmButton.setEnabled(false);
        confirmButton.setBackgroundColor(getResources().getColor(R.color.tab_background_unselected));
        confirmButton.setElevation(0);


        taxFormImageButton.setEnabled(false);
        taxFormImageButton.setBackgroundColor(getResources().getColor(R.color.tab_background_unselected));
        taxFormImageButton.setElevation(0);

        ruleButton.setEnabled(true);
        ruleButton.setBackgroundColor(getResources().getColor(R.color.other_btn_bck));
        ruleButton.setElevation(getResources().getDimension(R.dimen.standard_margin));

        objectionButton.setEnabled(true);
        objectionButton.setBackground(getResources().getDrawable(R.color.other_btn_bck));
        objectionButton.setElevation(getResources().getDimension(R.dimen.standard_margin));

        agreementButton.setEnabled(true);
        agreementButton.setBackground(getResources().getDrawable(R.color.other_btn_bck));
        agreementButton.setElevation(getResources().getDimension(R.dimen.standard_margin));

        yourDecisionButton.setTextColor(getResources().getColor(R.color.main_btn_bck));



    }

    public void goToFragment(Fragment fragment) {

        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right)
                .replace(R.id.newtax_frame_layout,fragment).commit();
        transaction.addToBackStack(Variables.USER_SHORTCUT);

    }

    public void setupListViewAdapter(){
        taxTypeRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        yearRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        stepRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        ArrayList<ParentObject> parentObjects = new ArrayList<>();
        ChildrenListHandler taxTypeParent = new ChildrenListHandler();
        taxTypeParent.setTitle("نوع مالیات");
        ArrayList<Object> taxTypeList = new ArrayList<>();
        taxTypeList.add(new ChildObject("عملکرد",1));
        taxTypeList.add(new ChildObject("ارزش افزوده",2));
        taxTypeParent.setChildObjectList(taxTypeList);
        parentObjects.add(taxTypeParent);

        taxTypeAdapter = new ExpandableAdapter(getContext(),parentObjects);
        taxTypeAdapter.setCustomParentAnimationViewId(R.id.arrow_image);
        taxTypeAdapter.setParentClickableViewAnimationDefaultDuration();
        taxTypeAdapter.setParentAndIconExpandOnClick(true);
        taxTypeRecyclerView.setAdapter(taxTypeAdapter);

        parentObjects = new ArrayList<>();
        ChildrenListHandler stepTypeParent = new ChildrenListHandler();
        stepTypeParent.setTitle("نوع برگه ابلاغیه (مرحله)");
        ArrayList<Object> stepTypeList = new ArrayList<>();
        stepTypeList.add(new ChildObject("برگه تشخیص",1));
        stepTypeList.add(new ChildObject("هیئت حل اختلاف",2));
        stepTypeList.add(new ChildObject("هیئت تجدید نظر",3));
        stepTypeList.add(new ChildObject("شورای عالی مالیاتی",4));
        stepTypeList.add(new ChildObject("ماده ۲۵۱ مکرر",5));
        stepTypeList.add(new ChildObject("دیوان محاسبات",6));
        stepTypeParent.setChildObjectList(stepTypeList);
        parentObjects.add(stepTypeParent);

        stepAdapter= new ExpandableAdapter(getContext(),parentObjects);
        stepAdapter.setCustomParentAnimationViewId(R.id.arrow_image);
        stepAdapter.setParentClickableViewAnimationDefaultDuration();
        stepAdapter.setParentAndIconExpandOnClick(true);
        stepRecyclerView.setAdapter(stepAdapter);


    }


}
