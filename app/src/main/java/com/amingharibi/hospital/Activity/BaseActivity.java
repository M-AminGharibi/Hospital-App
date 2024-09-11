package com.amingharibi.hospital.Activity;

import android.app.Application;

import com.amingharibi.hospital.R;

import com.parse.Parse;

public class BaseActivity extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(getString(R.string.back4app_app_id))
                .clientKey(getString(R.string.back4app_client_key))
                .server(getString(R.string.back4app_server_url))
                .build());

    }
}