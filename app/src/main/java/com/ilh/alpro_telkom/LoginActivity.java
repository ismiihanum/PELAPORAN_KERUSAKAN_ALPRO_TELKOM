package com.ilh.alpro_telkom;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import com.google.firebase.messaging.FirebaseMessaging;
import com.ilh.alpro_telkom.helper.Config;
import com.ilh.alpro_telkom.model.ResponseErrorModel;
import com.ilh.alpro_telkom.rest.ApiConfigServer;
import com.ilh.alpro_telkom.rest.ApiService;
import com.ilh.alpro_telkom.ui.pelapor.PelaporActivity;
import com.ilh.alpro_telkom.ui.teknisi.TeknisiActivity;
import com.ilh.alpro_telkom.ui.validator.ValidatorActivity;
import com.ilh.alpro_telkom.util.NotificationUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private EditText edtUsername;
    private EditText edtPassword;
    private Button btnLogin;

    private ResponseErrorModel responseErrorModel;

    private static final String TAG = SplashActivity.class.getSimpleName();
    private BroadcastReceiver mRegistrationBroadcastReceiver;
    private NotificationManager mManager;

    private String regId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        displayFirebaseRegId();
        mRegistrationBroadcastReceiver = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {

                // checking for type intent filter
                if (intent.getAction().equals(Config.REGISTRATION_COMPLETE)) {
                    // gcm successfully registered
                    // now subscribe to `global` topic to receive app wide notifications
                    FirebaseMessaging.getInstance().subscribeToTopic(Config.TOPIC_GLOBAL);

                    displayFirebaseRegId();

                } else if (intent.getAction().equals(Config.PUSH_NOTIFICATION)) {
                    // new push notification is received

                    String message = intent.getStringExtra("message");

                    Toast.makeText(getApplicationContext(), "Peringatan : " + message, Toast.LENGTH_LONG).show();

//                    edtRegID.setText(message);
                }
            }
        };
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loginRule();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        // register GCM registration complete receiver
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.REGISTRATION_COMPLETE));

        // register new push message receiver
        // by doing this, the activity will be notified each time a new message arrives
        LocalBroadcastManager.getInstance(this).registerReceiver(mRegistrationBroadcastReceiver,
                new IntentFilter(Config.PUSH_NOTIFICATION));

        // clear the notification area when the app is opened
        NotificationUtils.clearNotifications(getApplicationContext());
    }

    @Override
    protected void onPause() {
        LocalBroadcastManager.getInstance(this).unregisterReceiver(mRegistrationBroadcastReceiver);
        super.onPause();
    }

    // Fetches reg id from shared preferences
    // and displays on the screen
    private void displayFirebaseRegId() {
        SharedPreferences pref = getApplicationContext().getSharedPreferences(Config.SHARED_PREF_NAME, 0);
        regId = pref.getString("regId", null);
        Log.e(TAG, "Firebase reg id: " + regId);

//        if (!TextUtils.isEmpty(regId))
//            edtRegID.setText("Firebase Reg Id: " + regId);
//        else
//            edtRegID.setText("Firebase Reg Id is not received yet!");
    }
    private void loginRule() {
        ApiService apiService = ApiConfigServer.getApiService();
        apiService.login(edtUsername.getText().toString().trim(), edtPassword.getText().toString().trim()).enqueue(new Callback<ResponseErrorModel>() {
            @Override
            public void onResponse(Call<ResponseErrorModel> call, Response<ResponseErrorModel> response) {
                if (response.isSuccessful()){
                    responseErrorModel = response.body();
                    Toast.makeText(LoginActivity.this, "" + responseErrorModel.getRule(), Toast.LENGTH_SHORT).show();

                    if (responseErrorModel.getRule().contains("validator")){
                        Config.sharedPref(LoginActivity.this,responseErrorModel.getId(), responseErrorModel.getUsername(), responseErrorModel.getRule());
                        finishAffinity();
                        startActivity(new Intent(getApplicationContext(), ValidatorActivity.class));
                    } else if (responseErrorModel.getRule().contains("teknisi")){
                        Config.sharedPref(LoginActivity.this,responseErrorModel.getId(), responseErrorModel.getUsername(), responseErrorModel.getRule());
                        finishAffinity();
                        startActivity(new Intent(getApplicationContext(), TeknisiActivity.class));
                    } else if (responseErrorModel.getRule().contains("user")){
                        Config.sharedPref(LoginActivity.this,responseErrorModel.getId(), responseErrorModel.getUsername(), responseErrorModel.getRule());
                        finishAffinity();
                        startActivity(new Intent(getApplicationContext(), PelaporActivity.class));
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseErrorModel> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "" + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void initView() {
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        btnLogin = findViewById(R.id.btn_login);
    }
}
