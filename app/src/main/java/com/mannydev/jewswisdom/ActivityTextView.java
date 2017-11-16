package com.mannydev.jewswisdom;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.webkit.WebView;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityTextView extends AppCompatActivity {

    private static final String ID_FILE = "resId";
    private static final String TEXT_TITLE = "title";

    @BindView(R.id.adView)
    AdView adView;
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.webView)
    WebView webView;

    private int resId, resTitle;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_text_view);
        ButterKnife.bind(this);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        //Получаем данные о файле
        initResources();

        //Загружаем файл
        loadDataToWebView();

    }

    //Читаем файл по ID из папки row
    private String openFile(int id) {

        InputStream inputStream = this.getResources().openRawResource(id);
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String line;
        StringBuilder text = new StringBuilder();
        try {
            while ((line = bufferedReader.readLine()) != null) {
                text.append(line);
                text.append('\n');
            }
        } catch (IOException e) {
            return null;
        }
        return text.toString();

    }

    private void initResources(){
        Intent intent = getIntent();
        resId = intent.getIntExtra(ID_FILE,0);
        resTitle = intent.getIntExtra(TEXT_TITLE,0);
    }

    private void loadDataToWebView(){
        txtTitle.setText(getResources().getText(resTitle));
        String text = openFile(resId);
        String summary = "<!Doctype html><html><head><meta charset=utf-8></head><body bgcolor=\"#ffff00\">"
                + text + "</body></html>";
        webView.loadData(summary, "text/html; charset=utf-8", "utf-8");
    }

    /**
     * Called when leaving the activity
     */
    @Override
    public void onPause() {
        if (adView != null) {
            adView.pause();
        }
        super.onPause();
    }

    /**
     * Called when returning to the activity
     */
    @Override
    public void onResume() {
        super.onResume();
        if (adView != null) {
            adView.resume();
        }
    }

    /**
     * Called before the activity is destroyed
     */
    @Override
    public void onDestroy() {
        if (adView != null) {
            adView.destroy();
        }
        super.onDestroy();
    }
}
