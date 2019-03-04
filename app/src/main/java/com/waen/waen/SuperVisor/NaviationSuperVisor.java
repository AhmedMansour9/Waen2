package com.waen.waen.SuperVisor;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.waen.waen.Admin.Fragments.Home_Admin;
import com.waen.waen.Admin.Fragments.SuperVisors_Admin;
import com.waen.waen.Main.Login;
import com.waen.waen.Parent.Fragments.Absence_Parent;
import com.waen.waen.Parent.Fragments.MapBus_Parent;
import com.waen.waen.Parent.Fragments.SuperVisors_Parent;
import com.waen.waen.R;
import com.waen.waen.SharedPrefManager;
import com.waen.waen.SuperVisor.Fragments.Choose_BackUp_Return;
import com.waen.waen.SuperVisor.Fragments.Maps_Bus_Supervisor;

import java.util.List;

public class NaviationSuperVisor extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    Fragment fr;
    private int mCurrentSelectedPosition = 0;
    NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_naviation_super_visor);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        navigationView.getMenu().getItem(0).setChecked(true);
        onNavigationItemSelected(navigationView.getMenu().getItem(0));
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            int index = Home_Supervisor.tabLayout.getSelectedTabPosition();
           if(fr instanceof Absence_Parent){
                BackToHome();

            } else if (index != 0) {
                Home_Supervisor.tabLayout.getTabAt(0).select();
            }
            else {
                super.onBackPressed();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        return true;
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        switch (item.getItemId()) {
            case R.id.home:
                mCurrentSelectedPosition = 0;
                FragmentManager fm = getSupportFragmentManager(); // or 'getSupportFragmentManager();'
                int count = fm.getBackStackEntryCount();
                if(count!=0) {
                    for (int i = 0; i < count; ++i) {
                        fm.popBackStack();
                    }
                }
                fr = new Home_Supervisor();
                break;

            case R.id.absence:
                mCurrentSelectedPosition = 1;
                fr = new Absence_Parent();
                break;
            case R.id.logout:
                mCurrentSelectedPosition = 2;

//                shareRole.putString("Role",null);
//                shareRole.commit();
//                Shared.putString("logggin",null);
//                Shared.apply();

                SharedPrefManager.getInstance(this).saveUserToken(null);
                SharedPrefManager.getInstance(this).saveUserTokenAdmin(null);
                SharedPrefManager.getInstance(this).saveRole(null);
                SharedPrefManager.getInstance(this).saveId(null);
                startActivity(new Intent(this, Login.class));
                finish();
                break;

            default:
                mCurrentSelectedPosition = 0;

        }
        if (item.isChecked()) {
            item.setChecked(false);
        } else {
            item.setChecked(true);
        }
        item.setChecked(true);


        FragmentManager fragmentManager=getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.flContent,fr);
        transaction.commit();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void BackToHome()
    {
        fr = new Home_Supervisor();
        if(fr !=null)
        {
            navigationView.getMenu().getItem(0).setChecked(true);
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.flContent,fr,fr.getTag()).commit();
        }

    }
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
