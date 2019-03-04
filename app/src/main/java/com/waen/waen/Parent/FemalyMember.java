package com.waen.waen.Parent;

import android.app.Fragment;
import android.content.Intent;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.waen.waen.Main.Login;
import com.waen.waen.Parent.Fragments.MapBus_Parent;
import com.waen.waen.R;
import com.waen.waen.SharedPrefManager;
import com.waen.waen.SuperVisor.Fragments.Maps_Bus_Supervisor;

public class FemalyMember extends AppCompatActivity {
    ImageView logoout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_femaly_member);
        logoout = findViewById(R.id.logoout);
        MapBus_Parent newFragment = new MapBus_Parent();
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.femaly, newFragment);
        ft.commit();

        logoout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPrefManager.getInstance(FemalyMember.this).saveUserToken(null);
                SharedPrefManager.getInstance(FemalyMember.this).saveRole(null);
                SharedPrefManager.getInstance(FemalyMember.this).saveId(null);
                startActivity(new Intent(FemalyMember.this, Login.class));
                finish();

            }
        });

    }
}
