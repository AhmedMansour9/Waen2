package com.waen.waen;

import android.app.Application;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;

/**
 * Created by Ahmed on 07/12/2018.
 */

public class OneSignal extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        com.onesignal.OneSignal.startInit(this)
                .inFocusDisplaying(com.onesignal.OneSignal.OSInFocusDisplayOption.Notification)
                .unsubscribeWhenNotificationsAreDisabled(true)
                .init();


//
        String email = "example@domain.com";
        String emailAuthHash = "123456789"; // Email auth hash generated from your server
        com.onesignal.OneSignal.setEmail(email, emailAuthHash, new com.onesignal.OneSignal.EmailUpdateHandler() {
            @Override
            public void onSuccess() {
                // Email successfully synced with OneSignal
//                Toast.makeText(OneSignal.this, "", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(com.onesignal.OneSignal.EmailUpdateError error) {
                // Error syncing email, check error.getType() and error.getMessage() for details
            }
        });
    }
}
