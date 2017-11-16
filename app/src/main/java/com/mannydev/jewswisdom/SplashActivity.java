package com.mannydev.jewswisdom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.io.IOException;

public class SplashActivity extends AppCompatActivity {

    private Intent intent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        intent = new Intent(this, MainActivity.class);

        DataBaseHelper myDbHelper;
        myDbHelper = new DataBaseHelper(this);
        myDbHelper.createDataBase();

        startActivity(intent);
        finish();
    }
}
