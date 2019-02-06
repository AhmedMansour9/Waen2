package com.waen.waen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.onesignal.OneSignal;
import com.waen.waen.Admin.Activities.Navigation_Admin;
import com.waen.waen.Admin.Fragments.Maps_Bus_admin;
import com.waen.waen.Main.Login;
import com.waen.waen.SuperVisor.Home_Supervisor;

import org.json.JSONObject;


public class Splash extends AppCompatActivity {
    Thread splashTread;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        final String token = SharedPrefManager.getInstance(this).getUserToken();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_admin);

//        com.onesignal.OneSignal.postNotification(new JSONObject("{'contents': {'en':'Test Message'}, " +
//                "'include_player_ids': ['" + userId + "']}"), null);

        StartAnimations();
    }
    private void StartAnimations() {
        Animation anim = AnimationUtils.loadAnimation(this, R.anim.alpha);
        anim.reset();
        RelativeLayout l=findViewById(R.id.lin_lay);
        l.clearAnimation();
        l.startAnimation(anim);

        anim = AnimationUtils.loadAnimation(this, R.anim.translate);
        anim.reset();
        ImageView iv = (ImageView) findViewById(R.id.logo);
        iv.clearAnimation();
        iv.startAnimation(anim);

        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    // Splash screen pause time
                    while (waited < 3500) {
                        sleep(100);
                        waited += 100;
                    }
                    Intent intent = new Intent(Splash.this,
                            Login.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                    startActivity(intent);
                    Splash.this.finish();
                } catch (InterruptedException e) {
                    // do nothing
                } finally {
                    Splash.this.finish();
                }
            }
        };
        splashTread.start();
    }


}
