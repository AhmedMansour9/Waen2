package com.waen.waen.SuperVisor;


import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.onesignal.OSPermissionSubscriptionState;
import com.onesignal.OneSignal;
import com.waen.waen.Language;
import com.waen.waen.R;
import com.waen.waen.SharedPrefManager;
import com.waen.waen.SuperVisor.Fragments.Backup_Supervisor;
import com.waen.waen.SuperVisor.Fragments.Choose_BackUp_Return;
import com.waen.waen.SuperVisor.Fragments.Details_Message_Supervisor;
import com.waen.waen.SuperVisor.Fragments.Maps_Bus_Supervisor;
import com.waen.waen.SuperVisor.Fragments.Return_Supervisor;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home_Supervisor extends AppCompatActivity {


    public Home_Supervisor() {
        // Required empty public constructor
    }
    private TabLayout tabLayout;
    private ViewPager viewPager;
    View view;
    TextView userName,userGender,userPhone,userRemaining;
    String Usertoken;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_home_supervisor);
        viewPager =findViewById(R.id.viewpager);
        Usertoken=SharedPrefManager.getInstance(getBaseContext()).getUserToken();
        tabLayout =findViewById(R.id.tabs);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setRotationX(180);
        tabLayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#fffc00"));
        tabLayout.setSelectedTabIndicatorHeight((int) (5 * getResources().getDisplayMetrics().density));
        setupTabIcons();
        RefreshTabs();


    }

    public void RefreshTabs(){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                FragmentManager fm = getSupportFragmentManager(); // or 'getSupportFragmentManager();'
                int count = fm.getBackStackEntryCount();
                if(count!=0) {
                    for (int i = 0; i < count; ++i) {
                        fm.popBackStack();
                    }
                }
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }
    private void setupTabIcons() {
        View view1 = getLayoutInflater().inflate(R.layout.iconmap_admin, null);
        View view2 = getLayoutInflater().inflate(R.layout.iconbackup_supervisor, null);
        View view3 = getLayoutInflater().inflate(R.layout.iconreturn_supervisor, null);
        View view4 = getLayoutInflater().inflate(R.layout.iconfeedback_admin, null);
        if(Language.isRTL()){
            tabLayout.getTabAt(0).setCustomView(view4);
            tabLayout.getTabAt(1).setCustomView(view3);
            tabLayout.getTabAt(2).setCustomView(view2);
            tabLayout.getTabAt(3).setCustomView(view1);
        }else {
            tabLayout.getTabAt(0).setCustomView(view1);
            tabLayout.getTabAt(1).setCustomView(view2);
            tabLayout.getTabAt(2).setCustomView(view3);
            tabLayout.getTabAt(3).setCustomView(view4);
        }
    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        String  statue= SharedPrefManager.getInstance(this).getStartTrip();
        if(Language.isRTL()){
            if(statue!=null){
                adapter.addFrag(new Details_Message_Supervisor(), getResources().getString(R.string.feedback));
                adapter.addFrag(new Return_Supervisor(), getResources().getString(R.string.retur));
                adapter.addFrag(new Backup_Supervisor(), getResources().getString(R.string.backup));
                adapter.addFrag(new Maps_Bus_Supervisor(), getResources().getString(R.string.bus));
            }else {
                adapter.addFrag(new Details_Message_Supervisor(), getResources().getString(R.string.feedback));
                adapter.addFrag(new Return_Supervisor(), getResources().getString(R.string.retur));
                adapter.addFrag(new Backup_Supervisor(), getResources().getString(R.string.backup));
                adapter.addFrag(new Choose_BackUp_Return(), getResources().getString(R.string.bus));
            }
        }else {
            if(statue!=null){
                adapter.addFrag(new Maps_Bus_Supervisor(), getResources().getString(R.string.bus));
                adapter.addFrag(new Backup_Supervisor(), getResources().getString(R.string.backup));
                adapter.addFrag(new Return_Supervisor(), getResources().getString(R.string.retur));
                adapter.addFrag(new Details_Message_Supervisor(), getResources().getString(R.string.feedback));
            }else {
                adapter.addFrag(new Choose_BackUp_Return(), getResources().getString(R.string.bus));
                adapter.addFrag(new Backup_Supervisor(), getResources().getString(R.string.backup));
                adapter.addFrag(new Return_Supervisor(), getResources().getString(R.string.retur));
                adapter.addFrag(new Details_Message_Supervisor(), getResources().getString(R.string.feedback));
            }
        }
        viewPager.setAdapter(adapter);
    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }
        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }
        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // TODO Auto-generated method stub
        }

    }

//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
////        if (viewPager.getCurrentItem() != 0) {
////
////            viewPager.setCurrentItem(0,true);
////        }else{
////            super.onBackPressed();
////        }
//    }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        List<Fragment> fragments = getSupportFragmentManager().getFragments();
        if (fragments != null) {
            for (Fragment fragment : fragments) {
                fragment.onRequestPermissionsResult(requestCode, permissions, grantResults);
            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        List<Fragment> listOfFragments =getSupportFragmentManager().getFragments();

        if(listOfFragments.size()>=1){
            for (Fragment fragment : listOfFragments) {
                if(fragment instanceof Maps_Bus_Supervisor){
                    fragment.onActivityResult(requestCode, resultCode, data);
                }
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

}
