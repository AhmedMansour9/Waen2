package com.waen.waen.SuperVisor.Fragments;


import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.waen.waen.Admin.NetworikConntection;
import com.waen.waen.Main.Fragments.Inbox;
import com.waen.waen.Main.Fragments.Sent;
import com.waen.waen.Main.SendMessage;
import com.waen.waen.SharedPrefManager;
import com.waen.waen.Language;
import com.waen.waen.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Details_Message_Supervisor extends Fragment {


    public Details_Message_Supervisor() {
        // Required empty public constructor
    }

    View view;
    NetworikConntection networikConntection;
    String User_Admin, User;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    FloatingActionButton floatingActionButton;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_details__message_supervisor, container, false);
        networikConntection = new NetworikConntection(getActivity());
//        send_message_presenter=new Send_Message_Presenter(getContext(),this);
        floatingActionButton=view.findViewById(R.id.fab);
        User_Admin = SharedPrefManager.getInstance(getActivity()).getUserTokenAdmin();
        User = SharedPrefManager.getInstance(getActivity()).getUserToken();
        viewPager = view.findViewById(R.id.viewpa);
        setupViewPager(viewPager);

        tabLayout = view.findViewById(R.id.tas);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.setTabTextColors(
                ColorStateList.valueOf(Color.BLACK));
        if (Language.isRTL()) {
            tabLayout.getTabAt(1).select();
        } else {
            tabLayout.getTabAt(0).select();
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            tabLayout.setLayoutDirection(View.LAYOUT_DIRECTION_LTR);
        }


        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getActivity(), SendMessage.class);
                intent.putExtra("type","supervisor");
                startActivity(intent);
            }
        });


        return view;
    }

    public void init() {

    }

    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getChildFragmentManager());

        if (Language.isRTL()) {
            // The view has RTL layout
            adapter.addFragment(new Inbox(),"Inbox");
            adapter.addFragment(new Sent(), "Sent");
        } else {
            // The view has LTR layout
            adapter.addFragment(new Sent(),"Sent");
            adapter.addFragment(new Inbox(), "Inbox");

        }

        viewPager.setCurrentItem(adapter.getCount() - 1);
        viewPager.setAdapter(adapter);
    }

    static class Adapter extends FragmentStatePagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public Adapter(FragmentManager manager) {
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

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            // TODO Auto-generated method stub
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public void refresh() {
        //yout code in refresh.

    }
}