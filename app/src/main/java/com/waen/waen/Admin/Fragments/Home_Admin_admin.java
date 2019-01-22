package com.waen.waen.Admin.Fragments;


import android.graphics.Color;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.onesignal.OneSignal;
import com.waen.waen.Language;
import com.waen.waen.R;
import com.waen.waen.Splash;

import org.json.JSONObject;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home_Admin_admin extends Fragment {


    public Home_Admin_admin() {
        // Required empty public constructor
    }
    private TabLayout tabLayout;
    private ViewPager viewPager;
    View view;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_home_admin, container, false);
        viewPager =view.findViewById(R.id.viewpager);
        tabLayout =view.findViewById(R.id.tabs);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setRotationX(180);
        tabLayout.setTabTextColors(Color.parseColor("#ffffff"), Color.parseColor("#fffc00"));
        tabLayout.setSelectedTabIndicatorHeight((int) (5 * getResources().getDisplayMetrics().density));
        RefreshTabs();
        setupTabIcons();


        return view;
    }

    public void RefreshTabs(){
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                FragmentManager fm = getFragmentManager(); // or 'getSupportFragmentManager();'
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
        View view2 = getLayoutInflater().inflate(R.layout.iconrequest_admin, null);
        View view3 = getLayoutInflater().inflate(R.layout.iconfeedback_admin, null);
        View view4 = getLayoutInflater().inflate(R.layout.iconnotification_admin, null);
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
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        if(Language.isRTL()){
            adapter.addFrag(new Notification_admin(), getResources().getString(R.string.notification));
            adapter.addFrag(new Feedback_admin(), getResources().getString(R.string.feedback));
            adapter.addFrag(new Requests_admin(), getResources().getString(R.string.request));
            adapter.addFrag(new Maps_Bus_admin(), getResources().getString(R.string.bus));

        }else {
            adapter.addFrag(new Maps_Bus_admin(), getResources().getString(R.string.bus));
            adapter.addFrag(new Requests_admin(), getResources().getString(R.string.request));
            adapter.addFrag(new Feedback_admin(), getResources().getString(R.string.feedback));
            adapter.addFrag(new Notification_admin(), getResources().getString(R.string.notification));

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

    }
}
