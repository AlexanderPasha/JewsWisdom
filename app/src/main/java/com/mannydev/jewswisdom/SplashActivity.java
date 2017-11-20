package com.mannydev.jewswisdom;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        Intent intent = new Intent(this, MainActivity.class);

        DataBaseHelper myDbHelper;
        myDbHelper = new DataBaseHelper(this);
        myDbHelper.createDataBase();

        startActivity(intent);
        finish();
    }
}
