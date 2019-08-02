package com.ilh.alpro_telkom;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;
import com.ilh.alpro_telkom.helper.Config;
import com.ilh.alpro_telkom.ui.pelapor.PelaporActivity;
import com.ilh.alpro_telkom.ui.teknisi.TeknisiActivity;
import com.ilh.alpro_telkom.ui.validator.ValidatorActivity;
import com.ilh.alpro_telkom.util.NotificationUtils;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                SharedPreferences sp = getSharedPreferences(Config.SHARED_PREF_NAME, MODE_PRIVATE);
                String username = sp.getString(Config.SHARED_PREF_USERNAME, "");
                String rule = sp.getString(Config.SHARED_PREF_RULE, "");

                // TODO jika belum masuk ke LoginActivity
                if (username.equalsIgnoreCase("") || TextUtils.isEmpty(username)){
                    finishAffinity();
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                }
                // TODO jika sudah nantinya akan masuk ke Home
                else {
                    if (rule.contains("user")){
                        finishAffinity();
                        startActivity(new Intent(getApplicationContext(), PelaporActivity.class));
                    } else if (rule.contains("validator")){
                        finishAffinity();
                        startActivity(new Intent(getApplicationContext(), ValidatorActivity.class));
                    } else if (rule.contains("teknisi")){
                        finishAffinity();
                        startActivity(new Intent(getApplicationContext(), TeknisiActivity.class));
                    }

                }
            }
        }, 2000);



    }
}
