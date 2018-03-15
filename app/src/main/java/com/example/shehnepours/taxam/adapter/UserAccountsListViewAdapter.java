package com.example.shehnepours.taxam.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.example.shehnepours.taxam.R;
import com.example.shehnepours.taxam.parents.CustomTextView;
import com.example.shehnepours.taxam.utils.UserAccount;

import java.util.ArrayList;

/**
 * Created by shehnepour.s on 3/13/2018.
 */

public class UserAccountsListViewAdapter extends ArrayAdapter<UserAccount> {


    private ArrayList<UserAccount> arrayList;
    private Context context;

    public UserAccountsListViewAdapter(@NonNull Context context, ArrayList<UserAccount> arrayList) {
        super(context, R.layout.layout_row_user_accounts, arrayList);
        this.arrayList = arrayList;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.layout_row_user_accounts,parent,false);


        LinearLayout userAccountLayout = (LinearLayout) rowView.findViewById(R.id.account_layout);
        CustomTextView companyName = (CustomTextView) rowView.findViewById(R.id.company_name);
        CustomTextView businessName = (CustomTextView) rowView.findViewById(R.id.company_business);
        ImageView editIcon= (ImageView) rowView.findViewById(R.id.edit_ic);
        ImageView deleteIcon = (ImageView) rowView.findViewById(R.id.delete_ic);

        companyName.setText(arrayList.get(position).getAccountName());
        businessName.setText(arrayList.get(position).getBusinessName());
        if(arrayList.get(position).getIsSelected() == 1) {
            userAccountLayout.setBackgroundColor(context.getResources().getColor(R.color.item_selected));
        }
        return rowView;
    }
}
