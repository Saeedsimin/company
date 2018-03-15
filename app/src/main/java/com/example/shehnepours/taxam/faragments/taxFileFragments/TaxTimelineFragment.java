package com.example.shehnepours.taxam.faragments.taxFileFragments;

import android.app.Fragment;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.shehnepours.taxam.R;
import com.example.shehnepours.taxam.constants.Variables;
import com.example.shehnepours.taxam.parents.CustomButton;
import com.example.shehnepours.taxam.parents.VerticalTextView;

/**
 * Created by shehnepour.s on 3/14/2018.
 */

public class TaxTimelineFragment extends Fragment {

    private View view;
    private int step;
    private VerticalTextView tashkhis;
    private VerticalTextView ekhtelaf;
    private VerticalTextView tajdid;
    private VerticalTextView shora;
    private VerticalTextView madeh251;
    private VerticalTextView divan;

    private ImageView tashkhisDown;
    private ImageView ekhtelafDown;
    private ImageView tajdidDown;
    private ImageView shoraDown;
    private ImageView madehDown;
    private ImageView divanDown;

    private CustomButton consultButton;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_file_timeline,container,false);
        setupBundle();
        setupView();
        setupListener();
        setupBackButton();
        return view;
    }

    private void setupBackButton() {

    }

    private void setupListener() {
    }

    private void setupView() {
        tashkhis = (VerticalTextView)view.findViewById(R.id.barge_tashkhis_timeline);
        ekhtelaf = (VerticalTextView) view.findViewById(R.id.hale_ekhtelaf_timeline);
        tajdid = (VerticalTextView) view.findViewById(R.id.tajdid_timeline);
        shora = (VerticalTextView) view.findViewById(R.id.shora_timeline);
        madeh251 = (VerticalTextView) view.findViewById(R.id.madeh251_timeline);
        divan = (VerticalTextView) view.findViewById(R.id.divan_timeline);

        tashkhisDown = (ImageView) view.findViewById(R.id.ic_down_tashkhis);
        ekhtelafDown = (ImageView) view.findViewById(R.id.ic_down_ehktelaf);
        tajdidDown = (ImageView) view.findViewById(R.id.ic_down_tajdid);
        shoraDown = (ImageView) view.findViewById(R.id.ic_down_shora);
        madehDown = (ImageView) view.findViewById(R.id.madeh_down);
        divanDown = (ImageView) view.findViewById(R.id.ic_down_divan);



    }

    private void setupBundle() {
        Bundle bundle = this.getArguments();
        if(bundle!=null) {
            step = bundle.getInt(Variables.STEPS_NUMBER);
        }
    }
}
