package com.example.shehnepours.taxam.Activity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.text.style.TypefaceSpan;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SubMenu;
import android.view.View;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.shehnepours.taxam.R;
import com.example.shehnepours.taxam.adapter.TaxFileViewPagerAdapter;
import com.example.shehnepours.taxam.adapter.ViewPagerAdapter;
import com.example.shehnepours.taxam.constants.Variables;
import com.example.shehnepours.taxam.faragments.TaxFileFragment;
import com.example.shehnepours.taxam.faragments.UserAccountsFragment;
import com.example.shehnepours.taxam.faragments.UserShortcutsFragment;
import com.example.shehnepours.taxam.faragments.rules.RulesFragment;
import com.example.shehnepours.taxam.faragments.rules.directRules.DirectRulesFrameLayout;
import com.example.shehnepours.taxam.faragments.services.TaxamServicesFragment;
import com.example.shehnepours.taxam.faragments.taxFileFragments.addedValueFragments.AddedValueAgreementFragment;
import com.example.shehnepours.taxam.parents.ActivityTitleTextView;
import com.example.shehnepours.taxam.views.GraphViewData;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import lecho.lib.hellocharts.model.AxisValue;
import lecho.lib.hellocharts.view.LineChartView;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.views.BannerSlider;

public class UserProfileActivity extends AppCompatActivity {

    private final String GRAPH_TAG = "graph";

    private static long back_pressed = 0;
    public Toolbar menuToolbar;
    private LineGraphSeries<DataPoint> series;
    private ViewPager pager = null;
    private ViewPagerAdapter pagerAdapter = null;
    private NavigationView navigationView;
    private View headerView;
    private LayoutInflater inflater;
    private View currentView;
    private View newView;
    private TextView titleTextView;
    private FragmentManager fragmentManager;
    private ActivityTitleTextView toolbarTitleTextView;
    private Typeface baykanTypeface;
    private Typeface sansTypeface;
    public TextView backIcon;

    private int currentPage = 0;
    private int pageNumber = 0;
    private DrawerLayout drawerLayout;
    private ArrayList<Fragment> fragments = new ArrayList<>();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        baykanTypeface = Typeface.createFromAsset(getAssets(),"fonts/byekan.ttf");
        sansTypeface = Typeface.createFromAsset(getAssets(),"fonts/IRANSans_Medium.ttf");
        fragmentManager = getSupportFragmentManager();
        FragmentTransaction submitTransaction = fragmentManager.beginTransaction();
        Fragment userShortcutFragment = new UserShortcutsFragment(UserProfileActivity.this);
        submitTransaction.add(R.id.fragment_container,userShortcutFragment);
        submitTransaction.commit();
        fragments.add(userShortcutFragment);

        setupToolbar();
        setupNavigationView();
        setupNavigationHeader();

    }

    public ArrayList<Fragment> getFragments(){
        return fragments;
    }

    @Override
    public void onBackPressed() {


        if(back_pressed + 2000>System.currentTimeMillis()) {
            super.onBackPressed();
            getFragments().remove(fragments.size()-1);
            Intent intent = new Intent(Intent.ACTION_MAIN);
            intent.addCategory(Intent.CATEGORY_HOME);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);//***Change Here***
            startActivity(intent);
            finish();
            System.exit(0);
        }
        else {
            Toast.makeText(UserProfileActivity.this,"برای خروج، لطفا دوباره دکمه خروج را فشار دهید",Toast.LENGTH_SHORT).show();
            back_pressed = System.currentTimeMillis();
        }
    }

    public void setupToolbar() {
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer_layout);
        drawerLayout.setScrimColor(getResources().getColor(android.R.color.transparent));

        menuToolbar= (Toolbar)findViewById(R.id.profile_menu_toolbar);
        AppBarLayout.LayoutParams params = (AppBarLayout.LayoutParams) menuToolbar.getLayoutParams();
        params.setScrollFlags(0);
        toolbarTitleTextView = (ActivityTitleTextView) findViewById(R.id.toolbar_title);
        backIcon = (TextView)findViewById(R.id.back_btn);

        backIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();

            }
        });

        // setting our customized toolbar as main action bar
        setSupportActionBar(menuToolbar);

        ActionBar actionBar = getSupportActionBar();

        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setHomeAsUpIndicator(R.drawable.ic_icon_back_black);
//        actionBar.setDisplayShowHomeEnabled(true);
//        actionBar.setHomeButtonEnabled(true);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,menuToolbar,0,0);
        drawerLayout.addDrawerListener(drawerToggle);
        drawerToggle.syncState();
//        toolbarTitleTextView.setText("پروفایل کاربری");
//        toolbarTitleTextView.setTypeface(sansTypeface);
//        toolbarTitleTextView.setTextColor(getResources().getColor(R.color.main_btn_bck));

//        for (int i = 0;i<menuToolbar.getChildCount();i++) {
//            if(menuToolbar.getChildAt(i) instanceof TextView) {
////                ((TextView) menuToolbar.getChildAt(i)).setTypeface(baykanTypeface);
//                toolbarTitleTextView = (TextView) menuToolbar.getChildAt(i);
//                toolbarTitleTextView.setText("پروفایل کاربری");
//                toolbarTitleTextView.setTypeface(baykanTypeface);
//            }
//        }

    }
    public void updateFragmentTitle(String title) {
        menuToolbar.setTitle(title);
    }


    private void setupNavigationView() {
        navigationView = (NavigationView)findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.navigation_menu_tax_file:
//                        Toast.makeText(UserProfileActivity.this,"foundddd!!",Toast.LENGTH_SHORT).show();
                        gotoTaxFile();
                        break;
                    case R.id.navigation_menu_rules:
                        goToFragment(new RulesFragment(UserProfileActivity.this));
                        break;
                    case R.id.navigation_menu_services:
                        goToFragment(new TaxamServicesFragment());
                        break;
                    case R.id.navigation_menu_user_account:
                        goToFragment(new UserAccountsFragment());
                        break;




                }

                return false;
            }
        });

    }

    public void gotoTaxFile() {
        toolbarTitleTextView.setText("پرونده مالیاتی");
        goToFragment(new TaxFileFragment(UserProfileActivity.this));


    }

    public void goToFragment(Fragment fragment) {

        drawerLayout.closeDrawers();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left,R.anim.slide_in_left,R.anim.slide_out_right)
                .replace(R.id.fragment_container,fragment).commit();
        transaction.addToBackStack(Variables.USER_SHORTCUT);

    }

    public void setupNavigationHeader() {
        setupTypefaceForMenu();
        headerView = navigationView.getHeaderView(0);
        headerView.setElevation(8);
        setupViewPager();


    }

    private void setupViewPager() {

        ImageButton nextImageButton = (ImageButton)headerView.findViewById(R.id.next_graph_button);
        ImageButton preImageButton = (ImageButton)headerView.findViewById(R.id.previous_graph_button);

        pagerAdapter = new ViewPagerAdapter();
        pager = (ViewPager) headerView.findViewById (R.id.view_pager);
        pager.setAdapter (pagerAdapter);

        pager.setCurrentItem(currentPage);

        // Create an initial view to display; must be a subclass of FrameLayout.
        inflater = getLayoutInflater();



        List<AxisValue> monthXvalues = new ArrayList<AxisValue>();
        List<AxisValue> seasonXvalues = new ArrayList<AxisValue>();
        List<AxisValue> yearXvalues = new ArrayList<AxisValue>();

        monthXvalues.add(new AxisValue(0).setLabel("مهر"));
        monthXvalues.add(new AxisValue(1).setLabel("آبان"));
        monthXvalues.add(new AxisValue(2).setLabel("آذر"));
        monthXvalues.add(new AxisValue(3).setLabel("دی"));
        monthXvalues.add(new AxisValue(4).setLabel("بهمن"));

        seasonXvalues.add(new AxisValue(0).setLabel("بهار"));
        seasonXvalues.add(new AxisValue(1).setLabel("تابستان"));
        seasonXvalues.add(new AxisValue(2).setLabel("پاییز"));
        seasonXvalues.add(new AxisValue(3).setLabel("زمستان"));
        seasonXvalues.add(new AxisValue(4).setLabel("بهار"));

        yearXvalues.add(new AxisValue(0).setLabel("۱۳۹۱"));
        yearXvalues.add(new AxisValue(1).setLabel("۱۳۹۲"));
        yearXvalues.add(new AxisValue(2).setLabel("۱۳۹۳"));
        yearXvalues.add(new AxisValue(3).setLabel("۱۳۹۴"));
        yearXvalues.add(new AxisValue(4).setLabel("۱۳۹۵"));


        addNewView(0,"مالیات بر ماه","مقدار",monthXvalues);
        addNewView(1,"مالیات بر فصل","مقدار",seasonXvalues);
        addNewView(2,"مالیات بر سال","مقدار",yearXvalues);



        nextImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage ++;
                currentPage = currentPage%pageNumber;
                pager.setCurrentItem(currentPage,true);


            }
        });
        preImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                currentPage --;
                if (currentPage<0) {
                    currentPage =pagerAdapter.getCount()-1;
                }
                currentPage = currentPage%pageNumber;
                pager.setCurrentItem(currentPage,true);
            }
        });


    }

    public void addNewView(int position, String Xlabel, String Ylabel, List<AxisValue> XaxisLables) {
        // Defining different fonts
        Typeface sanstypeface = Typeface.createFromAsset(getAssets(),"fonts/IRANSans_Light.ttf");
        Typeface baykanTypeface = Typeface.createFromAsset(getAssets(),"fonts/byekan.ttf");

        LineChartView texPerMonth = (LineChartView) inflater.inflate (R.layout.layout_graph, null);
        GraphViewData.graphDraw(texPerMonth,Xlabel,Ylabel,XaxisLables);
        texPerMonth.getChartData().getAxisXBottom().setTypeface(sanstypeface);
        texPerMonth.getChartData().getAxisYLeft().setTypeface(sanstypeface);
        pagerAdapter.addView (texPerMonth, pageNumber);
        pagerAdapter.notifyDataSetChanged();
        pageNumber++;

    }


    public void setupTypefaceForMenu() {
        Menu m = navigationView.getMenu();
        for (int i=0;i<m.size();i++) {
            MenuItem mi = m.getItem(i);
            //for aapplying a font to subMenu ...
            SubMenu subMenu = mi.getSubMenu();
            if (subMenu!=null && subMenu.size() >0 ) {
                for (int j=0; j <subMenu.size();j++) {
                    MenuItem subMenuItem = subMenu.getItem(j);
                    applyFontToMenuItem(subMenuItem);
                }
            }

            //the method we have create in activity
            applyFontToMenuItem(mi);
        }
    }

    private void applyFontToMenuItem(MenuItem mi) {
        Typeface sanstypeface = Typeface.createFromAsset(getAssets(),"fonts/IRANSans_UltraLight.ttf");
        SpannableString mNewTitle = new SpannableString(mi.getTitle());
        mNewTitle.setSpan(new CustomTypefaceSpan("" , sanstypeface), 0 , mNewTitle.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);

        if(mi.getItemId() == R.id.navigation_menu_contact_us || mi.getItemId() == R.id.navigation_menu_offers_critics) {
            mNewTitle.setSpan(new RelativeSizeSpan(0.7f), 0 , mNewTitle.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
            mNewTitle.setSpan(new ForegroundColorSpan(getResources().getColor(R.color.main_btn_bck)),0 , mNewTitle.length(),  Spannable.SPAN_INCLUSIVE_INCLUSIVE);
        }
        mi.setTitle(mNewTitle);
    }

    public void gotoFragmentByAddition(Fragment fragment) {
        fragmentManager.beginTransaction().setCustomAnimations(R.anim.slide_in_right,R.anim.slide_out_left)
                .add(R.id.fragment_container,fragment).commit();

    }


    private class CustomTypefaceSpan extends TypefaceSpan{
        private final Typeface newType;

        public CustomTypefaceSpan(String family, Typeface type) {
            super(family);
            newType = type;
        }

        @Override
        public void updateDrawState(TextPaint ds) {
            applyCustomTypeFace(ds, newType);
        }

        @Override
        public void updateMeasureState(TextPaint paint) {
            applyCustomTypeFace(paint, newType);
        }

        private void applyCustomTypeFace(Paint paint, Typeface tf) {
            int oldStyle;
            Typeface old = paint.getTypeface();
            if (old == null) {
                oldStyle = 0;
            } else {
                oldStyle = old.getStyle();
            }

            int fake = oldStyle & ~tf.getStyle();
            if ((fake & Typeface.BOLD) != 0) {
                paint.setFakeBoldText(true);
            }

            if ((fake & Typeface.ITALIC) != 0) {
                paint.setTextSkewX(-0.25f);
            }

            paint.setTypeface(tf);
        }
    }
}