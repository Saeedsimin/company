package com.example.shehnepours.taxam.faragments.taxFileFragments.addedValueFragments;

import android.app.ProgressDialog;
import android.content.Intent;
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
import com.example.shehnepours.taxam.faragments.taxFileFragments.operationalFragments.OperationalAgreementFragment;
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
import org.w3c.dom.Text;

/**
 * Created by shehnepour.s on 12/17/2017.
 */

public class AddedValueAgreementFragment extends Fragment {
    private View view;
    private ImageButton addedPickImageButton;
    private ShadowEditText agremmentValueEditText;
    private ShadowEditText taxValueEditText;
    private ShadowEditText dueValueEditText;
    private ShadowEditText penaltyValueEditText;
    private CustomButton confirmButton;
    private ImageView preview;
    private ProgressDialog dialog;

    private PickSetup setup;
    private TaxFileSharedPrefManager sharedPrefManager;

    private String encodedImage = "";
    private TaxFile taxFile;
    private int agreementValue;
    private int dueValue;
    private int taxValue;
    private int penaltyValue;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_addedvalue_agreement_fragment,container,false);
        addedPickImageButton = (ImageButton)view.findViewById(R.id.pick_image_agreement_added);

        agremmentValueEditText = (ShadowEditText) view.findViewById(R.id.agreement_value);
        taxValueEditText = (ShadowEditText) view.findViewById(R.id.agreement_tax);
        dueValueEditText = (ShadowEditText) view.findViewById(R.id.agreement_due);
        penaltyValueEditText = (ShadowEditText) view.findViewById(R.id.agreement_penalty);
        preview = (ImageView) view.findViewById(R.id.image_preview_imageview);
        confirmButton = (CustomButton) view.findViewById(R.id.agreement_transition_btn);
        sharedPrefManager = new TaxFileSharedPrefManager(getContext());
        taxFile = sharedPrefManager.getTaxFile();

        addedPickImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setup = PickImageDialogHandler.setupDialog(getActivity(),AddedValueAgreementFragment.this,preview);
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

    private void checkForCorrectInput() {
        if (agremmentValueEditText.getText().toString().length() == 0) {
            if (taxValueEditText.getText().toString().length() == 0) {
                Toast.makeText(getContext(),"لطفا مبلغ مالیات را وارد کنید",Toast.LENGTH_SHORT).show();
            } else if (dueValueEditText.getText().toString().length() == 0) {
                Toast.makeText(getContext(),"لطفا مبلغ عوارض را وارد کنید",Toast.LENGTH_SHORT).show();
            } else if (penaltyValueEditText.getText().toString().length() == 0) {
                Toast.makeText(getContext(),"لطفا مبلغ جریمه را وارد کنید",Toast.LENGTH_SHORT).show();
            } else {
                taxValue = Integer.parseInt(taxValueEditText.getText().toString());
                dueValue = Integer.parseInt(dueValueEditText.getText().toString());
                penaltyValue = Integer.parseInt(penaltyValueEditText.getText().toString());
                agreementValue = taxValue + dueValue + penaltyValue;
                agremmentValueEditText.setText(agreementValue);
                onSendAgreementInfoToServer();
            }
        } else {
            agreementValue = Integer.parseInt(agremmentValueEditText.getText().toString());
            if (taxValueEditText.getText().toString().length() == 0) {
                taxValue = 0;
            } else {
                taxValue = Integer.parseInt(taxValueEditText.getText().toString());
            }

            if (dueValueEditText.getText().toString().length() == 0) {
                dueValue = 0;
            } else {
                dueValue = Integer.parseInt(dueValueEditText.getText().toString());
            }

            if (penaltyValueEditText.getText().toString().length() == 0) {
                penaltyValue = 0;
            } else {
                penaltyValue = Integer.parseInt(penaltyValueEditText.getText().toString());
            }
            onSendAgreementInfoToServer();
        }


    }

    private void onSendAgreementInfoToServer() {
        dialog = ProgressDialog.show(getContext(),"","در حال ثبت اطلاعات توافق ...",true);
        sendAgreementInfoToServer();
    }

    private void sendAgreementInfoToServer() {
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
            requestJsonObject.put(Variables.AVAREZ,dueValue);
            requestJsonObject.put(Variables.JARIME,penaltyValue);
            requestJsonObject.put(Variables.TAX,taxValue);
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
        taxFile.setJarime(penaltyValue);
        taxFile.setAvarez(dueValue);
        taxFile.setTax(taxValue);
        sharedPrefManager.saveTaxFile(taxFile);

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
                }).show(AddedValueAgreementFragment.this.getFragmentManager());

    }

}
