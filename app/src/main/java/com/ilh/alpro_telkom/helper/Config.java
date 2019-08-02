package com.ilh.alpro_telkom.helper;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;

public final class Config {
    public static final String SHARED_PREF_NAME = "ALPRO_TELKOM";
    public static final String SHARED_PREF_ID = "ALPRO_ID_USER";
    public static final String SHARED_PREF_USERNAME = "ALPRO_USERNAME";
    public static final String SHARED_PREF_RULE = "ALPRO_RULE";
    public static final String SHARED_PREF_REGID_FIREBASE = "ALPRO_REGID_FIREBASE";
    public static final String SHARED_PREF_ERROR_MSG = "ALPRO_ERROR_MSG";



    // global topic to receive app wide push notifications
    public static final String TOPIC_GLOBAL = "global";

    // broadcast receiver intent filters
    public static final String REGISTRATION_COMPLETE = "registrationComplete";
    public static final String PUSH_NOTIFICATION = "pushNotification";

    // id to handle the notification in the notification tray
    public static final int NOTIFICATION_ID = 100;
    public static final int NOTIFICATION_ID_BIG_IMAGE = 101;


    public static void sharedPref(Context context, String idUser, String username, String rule) {
        SharedPreferences sharedPreferences  = context.getSharedPreferences(Config.SHARED_PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(Config.SHARED_PREF_ID, idUser);
        editor.putString(Config.SHARED_PREF_USERNAME, username);
        editor.putString(Config.SHARED_PREF_RULE, rule);


        editor.apply();
    }
}
