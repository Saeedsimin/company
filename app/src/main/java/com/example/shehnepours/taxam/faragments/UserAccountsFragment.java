package com.example.shehnepours.taxam.faragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.shehnepours.taxam.R;
import com.example.shehnepours.taxam.adapter.UserAccountsListViewAdapter;
import com.example.shehnepours.taxam.constants.Variables;
import com.example.shehnepours.taxam.parents.ActivityTitleTextView;
import com.example.shehnepours.taxam.parents.CustomButton;
import com.example.shehnepours.taxam.parents.CustomDialog;
import com.example.shehnepours.taxam.parents.CustomTextView;
import com.example.shehnepours.taxam.utils.UserAccount;

import java.util.ArrayList;

/**
 * Created by shehnepour.s on 3/6/2018.
 */

public class UserAccountsFragment extends Fragment {
    private View view;
    private ListView accountListView;
    private CustomButton addAccountButton;
    private ImageView addAccountImageView;
    private ActivityTitleTextView titleTextView;
    private CustomDialog editDialog;
    private CustomDialog deleteDialog;
    private CustomDialog enterDialog;

    private ArrayList<UserAccount> userAccounts;
    private UserAccountsListViewAdapter accountsListViewAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.layout_user_accounts,container,false);
        setupView();
        setupListener();
        setupTitle();
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

    private void setupTitle() {
        titleTextView.setText("فروشگاههای شما");
    }

    private void setupListener() {
        accountListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {
//                view.findViewById(R.id.)

                final CustomTextView companyName = (CustomTextView)view.findViewById(R.id.company_name);
                CustomTextView businessName = (CustomTextView) view.findViewById(R.id.company_business);
                final ImageView editIcon = (ImageView) view.findViewById(R.id.edit_ic);
                final ImageView deleteIcon = (ImageView) view.findViewById(R.id.delete_ic);

                companyName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        enterDialog = new CustomDialog(getContext(),R.style.DialogSlideAnim);
                        enterDialog.show();
                        enterDialog.dialText.setText("آیا مایلید به "+ companyName.getText()+" وارد شوید؟");
                        enterDialog.dialText.setTextColor(getContext().getResources().getColor(R.color.main_btn_bck));
                        enterDialog.no.setText("بله");
                        enterDialog.no.setTextColor(getContext().getResources().getColor(R.color.main_btn_bck));
                        enterDialog.yes.setText("خیر");
                        enterDialog.yes.setTextColor(getContext().getResources().getColor(R.color.main_btn_bck));
                        enterDialog.yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                enterDialog.dismiss();
                            }
                        });
                        enterDialog.no.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // TODO: 3/14/2018

                                goToFragment(new UserShortcutsFragment(getContext()));
                                enterDialog.dismiss();
                            }
                        });
                    }
                });
                businessName.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        enterDialog = new CustomDialog(getContext(),R.style.DialogSlideAnim);
                        enterDialog.show();
                        enterDialog.dialText.setText("آیا مایلید به "+ companyName.getText()+" وارد شوید؟");
                        enterDialog.dialText.setTextColor(getContext().getResources().getColor(R.color.main_btn_bck));
                        enterDialog.no.setText("بله");
                        enterDialog.no.setTextColor(getContext().getResources().getColor(R.color.main_btn_bck));
                        enterDialog.yes.setText("خیر");
                        enterDialog.yes.setTextColor(getContext().getResources().getColor(R.color.main_btn_bck));
                        enterDialog.yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                enterDialog.dismiss();
                            }
                        });
                        enterDialog.no.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // TODO: 3/14/2018
                                goToFragment(new UserShortcutsFragment(getContext()));
                                enterDialog.dismiss();
                            }
                        });

                    }
                });
                editIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        editDialog = new CustomDialog(getContext(),R.style.DialogSlideAnim);
                        editDialog.show();
                        editDialog.dialText.setText("آیا مایلید "+ companyName.getText()+" را ویرایش کنید؟");
                        editDialog.dialText.setTextColor(getContext().getResources().getColor(R.color.main_btn_bck));
                        editDialog.no.setText("بله");
                        editDialog.no.setTextColor(getContext().getResources().getColor(R.color.main_btn_bck));
                        editDialog.yes.setText("خیر");
                        editDialog.yes.setTextColor(getContext().getResources().getColor(R.color.main_btn_bck));
                        editDialog.yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                editDialog.dismiss();
                            }
                        });
                        editDialog.no.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // TODO: 3/14/2018


                                goToFragment(new BusinessRegisterFragment(getActivity()));
                                editDialog.dismiss();
                            }
                        });
                    }
                });
                deleteIcon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        deleteDialog = new CustomDialog(getContext(),R.style.DialogSlideAnim);
                        deleteDialog.show();
                        deleteDialog.dialText.setText("آیا مایلید "+ companyName.getText()+" را حذف کنید؟");
                        deleteDialog.dialText.setTextColor(getContext().getResources().getColor(R.color.main_btn_bck));
                        deleteDialog.no.setText("بله");
                        deleteDialog.no.setTextColor(getContext().getResources().getColor(R.color.main_btn_bck));
                        deleteDialog.yes.setText("خیر");
                        deleteDialog.yes.setTextColor(getContext().getResources().getColor(R.color.main_btn_bck));
                        deleteDialog.yes.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                deleteDialog.dismiss();
                            }
                        });
                        deleteDialog.no.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                // TODO: 3/14/2018

                                if(position !=0 && userAccounts.get(position).getIsSelected() == 1) {
                                    userAccounts.get(0).setIsSelected(1);
                                    userAccounts.remove(position);
//                                    accountsListViewAdapter.getView(0,null,null).findViewById(R.id.account_layout).
//                                            setBackgroundColor(getContext().getResources().getColor(R.color.item_selected));
                                } else if (position ==0 && userAccounts.get(position).getIsSelected() == 1) {
                                    userAccounts.remove(position);
                                    userAccounts.get(0).setIsSelected(1);

                                } else {
                                    userAccounts.remove(position);
                                }

                                accountsListViewAdapter.notifyDataSetChanged();
                                deleteDialog.dismiss();
                            }
                        });



                    }
                });

            }
        });
        addAccountImageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFragment(new BusinessRegisterFragment(getActivity()));
            }
        });
        addAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToFragment(new BusinessRegisterFragment(getActivity()));
            }
        });

    }

    private void setupView() {
        accountListView = (ListView)view.findViewById(R.id.accounts_list);
        setupListAdapter();
        addAccountButton = (CustomButton) view.findViewById(R.id.add_account_btn);
        addAccountImageView = (ImageView) view.findViewById(R.id.add_account_icon);
        titleTextView = (ActivityTitleTextView) getActivity().findViewById(R.id.toolbar_title);
    }

    private void setupListAdapter() {
        userAccounts = new ArrayList<>();
        userAccounts.add(new UserAccount("شرکت جمالی پور","سوپر مارکت",0));
        userAccounts.add(new UserAccount("شرکت هوشمندسازان","تولید اپلیکیشن",1));
        userAccounts.add(new UserAccount("فروشگاه سحابی","آهن آلات",0));
        accountsListViewAdapter = new UserAccountsListViewAdapter(getContext(),userAccounts);
        accountListView.setAdapter(accountsListViewAdapter);

    }
    private void goToFragment(Fragment fragment) {
        FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right)
                .replace(R.id.fragment_container,fragment).commit();
        transaction.addToBackStack(Variables.USER_SHORTCUT);

    }

}
