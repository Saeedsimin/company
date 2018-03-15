package com.example.shehnepours.taxam.faragments.taxFileFragments.operationalFragments;

import android.app.ProgressDialog;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.shehnepours.taxam.R;
import com.example.shehnepours.taxam.SharedPreferenceManager.TaxFileSharedPrefManager;
import com.example.shehnepours.taxam.apiServices.taxFormServices.POSTGeneralApiService;
import com.example.shehnepours.taxam.constants.ServiceScriptsAdresses;
import com.example.shehnepours.taxam.constants.Variables;
import com.example.shehnepours.taxam.datamodel.TaxFile;
import com.example.shehnepours.taxam.faragments.taxFileFragments.NewTaxFormFragment;
import com.example.shehnepours.taxam.faragments.taxFileFragments.addedValueFragments.AddedValueAgreementFragment;
import com.example.shehnepours.taxam.parents.CustomButton;
import com.example.shehnepours.taxam.parents.CustomEditText;
import com.example.shehnepours.taxam.parents.CustomTextView;
import com.example.shehnepours.taxam.parents.ShadowEditText;
import com.example.shehnepours.taxam.utils.PickImageDialogHandler;
import com.vansuita.pickimage.bean.PickResult;
import com.vansuita.pickimage.bundle.PickSetup;
import com.vansuita.pickimage.dialog.PickImageDialog;
import com.vansuita.pickimage.listeners.IPickResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by shehnepour.s on 12/20/2017.
 */

public class OperationalAgreementFragment extends Fragment {
    private View view;
    private ImageButton operationalImageButton;
    private ShadowEditText agreementValueEditText;
    private CustomButton confirmButton;
    private ImageView preview;
    private ProgressDialog dialog;

    private TaxFileSharedPrefManager sharedPrefManager;
    private PickSetup setup;

    private int agreementValue;
    private String encodedImage = "";
    private TaxFile taxFile;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_operational_agreement_fragment,container,false);
        operationalImageButton = (ImageButton) view.findViewById(R.id.pick_image_agreement_op);
        agreementValueEditText = (ShadowEditText) view.findViewById(R.id.agreement_value);
        confirmButton = (CustomButton) view.findViewById(R.id.agreement_transition_btn);
        preview = (ImageView) view.findViewById(R.id.image_preview_imageview);
        sharedPrefManager = new TaxFileSharedPrefManager(getContext());
        taxFile = sharedPrefManager.getTaxFile();



        operationalImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setup = PickImageDialogHandler.setupDialog(getActivity(),OperationalAgreementFragment.this,preview);
                pickImage();
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkForCorrectInput();
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

    private void pickImage() {
        PickImageDialog.build(setup)
                .setOnPickResult(new IPickResult() {
                    @Override
                    public void onPickResult(PickResult r) {
                        if (r.getError() == null) {
                            Log.i("PICK_IMAGE", "onPickResult: "+r.getError());
                            preview.setMaxHeight(300);
                            preview.setMaxWidth(80);
                            preview.setImageURI(r.getUri());
                            Bitmap bitmap = r.getBitmap();

                            Bitmap scaled = PickImageDialogHandler.scaleImage(bitmap);
                            preview.setImageBitmap(scaled);
                            encodedImage = PickImageDialogHandler.encodeImage(bitmap);
                            Log.i("ENCODED_IMAGE", "onClick: " + encodedImage.length());
                            PickImageDialogHandler.saveFileToInternalStorage(bitmap,getActivity());
                        } else {
                            Toast.makeText(getActivity(), r.getError().getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                }).show(OperationalAgreementFragment.this.getFragmentManager());

    }

    private void checkForCorrectInput() {
        if (agreementValueEditText.getText().toString().length() == 0) {
            Toast.makeText(getContext(),"لطفا مبلغ توافق را وارد نمایید",Toast.LENGTH_SHORT).show();
        } else {
            getInputFromUser();
        }
    }

    private void getInputFromUser() {
        agreementValue = Integer.parseInt(agreementValueEditText.getText().toString());
        onSendAgreementInfoToServer();
    }

    private void onSendAgreementInfoToServer() {
        dialog = ProgressDialog.show(getContext(),"","در حال ثبت اطلاعات توافق ...",true);
        sendAgreementInfo();
    }


    private void sendAgreementInfo() {
        POSTGeneralApiService apiService = new POSTGeneralApiService(getContext(),"SEND_AGREEMENT_INFO", ServiceScriptsAdresses.SEND_AGREEMENT_INFO_ADDRESS);
        JSONObject requestJsonObject = new JSONObject();
        try {
            requestJsonObject.put(Variables.YEAR_KEY,taxFile.getSal());
            requestJsonObject.put(Variables.TARIKH_EBLAGH,taxFile.getTarikh_eblagh());
            requestJsonObject.put(Variables.MALIAT_EBRAZI,taxFile.getMaliat_ebrazi());
            requestJsonObject.put(Variables.MALIAT_TASHKHISI,taxFile.getMaliat_tashkhisi());
            requestJsonObject.put(Variables.LETTER_TYPE,taxFile.getLetter_type());
            requestJsonObject.put(Variables.MARHALE,taxFile.getMarhale());
            requestJsonObject.put(Variables.SHENASE_MELI,1);
            requestJsonObject.put(Variables.AGREEMENT_PICTURE,encodedImage);
            requestJsonObject.put(Variables.AVAREZ,0);
            requestJsonObject.put(Variables.JARIME,0);
            requestJsonObject.put(Variables.TAX,0);
            requestJsonObject.put(Variables.AGREEMENT_AMOUNT,agreementValue);
            requestJsonObject.put(Variables.TAX_TYPE,taxFile.getTax_type());
        } catch (JSONException e) {
            e.printStackTrace();
        }
        apiService.onPOSTConfirmation(requestJsonObject, new POSTGeneralApiService.OnPOSTConfirmationComplete() {
            @Override
            public void onPOSTConfirmation(int success) {
                dialog.dismiss();
                if (success == 1) {
                    Toast.makeText(getContext(),"مبلغ توافق با موفقیت ثبت شد",Toast.LENGTH_SHORT).show();
                    saveAgreementToSharedPreference();
                } else {
                    Toast.makeText(getContext(),"در ثبت اطلاعات مشکل پیش آمد، لطفا مجددا تلاش کنید",Toast.LENGTH_SHORT).show();
                }

            }
        });



    }

    private void saveAgreementToSharedPreference() {
        taxFile.setAgreement_amount(agreementValue);
        sharedPrefManager.saveTaxFile(taxFile);
    }
}
