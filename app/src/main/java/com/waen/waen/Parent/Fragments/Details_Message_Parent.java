package com.waen.waen.Parent.Fragments;


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
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.waen.waen.Admin.NetworikConntection;
import com.waen.waen.Language;
import com.waen.waen.Main.Fragments.Inbox;
import com.waen.waen.Main.Fragments.Sent;
import com.waen.waen.Main.SendMessage;
import com.waen.waen.R;
import com.waen.waen.SharedPrefManager;
import com.waen.waen.SuperVisor.Fragments.Details_Message_Supervisor;
import com.waen.waen.SuperVisor.Model.Inbox_details;
import com.waen.waen.SuperVisor.Presenter.Message_Inbox_Presenter;
import com.waen.waen.SuperVisor.View.Messages_Inbox_View;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Details_Message_Parent extends Fragment implements Messages_Inbox_View {


    public Details_Message_Parent() {
        // Required empty public constructor
    }

    View view;
    NetworikConntection networikConntection;
    String User_Admin, User;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    FloatingActionButton floatingActionButton;
    Message_Inbox_Presenter message_Inbox_Presenter;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_details__message__parent, container, false);
        networikConntection = new NetworikConntection(getActivity());
        message_Inbox_Presenter=new Message_Inbox_Presenter(getActivity(),this);
        floatingActionButton = view.findViewById(R.id.fab);
        User_Admin = SharedPrefManager.getInstance(getActivity()).getUserTokenAdmin();
        User = SharedPrefManager.getInstance(getActivity()).getUserToken();
        viewPager = view.findViewById(R.id.viewpa);
        String Role=SharedPrefManager.getInstance(getContext()).getRole();
        setupViewPager(viewPager);
        if(Role.equals("supervisor")) {
            message_Inbox_Presenter.Inbox_SuperVisor(User);
        }else if(Role.equals("parent")){
            message_Inbox_Presenter.Inbox_Parent(User);

        }
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
                Intent intent = new Intent(getActivity(), SendMessage.class);
                intent.putExtra("type", "parent");
                startActivity(intent);
            }
        });


        return view;
    }
    private void setupViewPager(ViewPager viewPager) {
        Adapter adapter = new Adapter(getChildFragmentManager());

        if (Language.isRTL()) {
            // The view has RTL layout
            adapter.addFragment(new Inbox(), "Inbox");
            adapter.addFragment(new Sent(), "Sent");
        } else {
            // The view has LTR layout
            adapter.addFragment(new Sent(), "Sent");
            adapter.addFragment(new Inbox(), "Inbox");
        }

        viewPager.setCurrentItem(adapter.getCount() - 1);
        viewPager.setAdapter(adapter);
    }

    @Override
    public void GetMessagesParent(List<Inbox_details> inbox_details) {
        int counter=inbox_details.size();
        TabLayout.Tab tab = tabLayout.getTabAt(1); // fourth tab
        View tabView = tab.getCustomView();
        TextView textView = tabView.findViewById(R.id.cartt);
        textView.setText("  (" + counter + ")");

    }

    @Override
    public void SendMessages_SuperVisor(List<Inbox_details> inbox_details) {

    }

    @Override
    public void Inbox_Parent_Admin(List<Inbox_details> inbox_details) {

    }

    @Override
    public void Inbox_SuperVisor_Admin(List<Inbox_details> inbox_details) {

    }

    @Override
    public void Sent_Parent_Admin(List<Inbox_details> inbox_details) {

    }

    @Override
    public void Sent_SuperVisor_Admin(List<Inbox_details> inbox_details) {

    }

    @Override
    public void Error() {

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