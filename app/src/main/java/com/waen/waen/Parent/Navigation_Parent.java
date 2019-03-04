package com.waen.waen.Parent;

import android.content.Intent;
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
import com.waen.waen.Parent.Fragments.Absence_Parent;
import com.waen.waen.Admin.Fragments.SuperVisors_Admin;
import com.waen.waen.Main.Login;
import com.waen.waen.Parent.Fragments.Home_Parent;
import com.waen.waen.Parent.Fragments.SuperVisors_Parent;
import com.waen.waen.R;
import com.waen.waen.SharedPrefManager;
import com.waen.waen.SuperVisor.Home_Supervisor;

public class Navigation_Parent extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    Fragment fr;
    private int mCurrentSelectedPosition = 0;
    NavigationView navigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__parent);

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
            int index = Home_Parent.tabLayout.getSelectedTabPosition();
            if(fr instanceof SuperVisors_Parent){
                BackToHome();

            }else if(fr instanceof Absence_Parent){
                BackToHome();

            } else if (index != 0) {
                Home_Parent.tabLayout.getTabAt(0).select();
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
                fr = new Home_Parent();
                break;
            case R.id.supervisor:
                mCurrentSelectedPosition = 1;
                fr = new SuperVisors_Parent();
                break;
            case R.id.absence:
                mCurrentSelectedPosition = 2;
                fr = new Absence_Parent();
                break;
            case R.id.logout:
                mCurrentSelectedPosition = 3;
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
        transaction.replace(R.id.flContents,fr);
        transaction.commit();
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void BackToHome()
    {
        fr = new Home_Parent();
        if(fr !=null)
        {
            navigationView.getMenu().getItem(0).setChecked(true);
            FragmentManager manager = getSupportFragmentManager();
            manager.beginTransaction().replace(R.id.flContents,fr,fr.getTag()).commit();
        }
    }
}
