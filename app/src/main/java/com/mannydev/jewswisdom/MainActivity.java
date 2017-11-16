package com.mannydev.jewswisdom;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "Main Activity Logs";
    private static final String ID_FILE = "resId";
    private static final String TEXT_TITLE = "title";

    @BindView(R.id.adView)
    AdView adView;
    @BindView(R.id.buttonBook)
    Button buttonBook;
    @BindView(R.id.buttonShare)
    Button buttonShare;
    @BindView(R.id.buttonRate)
    Button buttonRate;
    @BindView(R.id.toolbar2)
    Toolbar toolbar2;
    @BindView(R.id.textView)
    TextView textView;
    @BindView(R.id.button)
    Button button;

    private SQLiteDatabase database;
    private PopupMenu popup;
    private MediaPlayer mp;
    private Animation animation;
    private List<String> dataList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        ButterKnife.bind(this);

        //Рекламный банер
        MobileAds.initialize(getApplicationContext(),
                "ca-app-pub-8078146669032188~1289393263");

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        dataList  = new ArrayList<>();
        mp = MediaPlayer.create(MainActivity.this, R.raw.mysound);

        animation  = AnimationUtils.loadAnimation(this, R.anim.alphaprozrachnost1);

        //Работа с БД
        DataBaseHelper myDbHelper = new DataBaseHelper(this);
        database = myDbHelper.openDataBase();
        Cursor cursor = database.query("evrey", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int dataColIndex = cursor.getColumnIndex("_mudrost");

            do {
                dataList.add(cursor.getString(dataColIndex));
            } while (cursor.moveToNext());
            cursor.close();
        } else Log.v(TAG, "Таблица пустая");

        //Всплывающее меню чтива
        popup = new PopupMenu(this, buttonBook);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                Intent intent = new Intent(getApplicationContext(), ActivityTextView.class);
                switch (item.getItemId()) {
                    case R.id.menu3:
                        intent.putExtra(ID_FILE, R.raw.mirovozrenie);
                        intent.putExtra(TEXT_TITLE, R.string.mirovozrenie);
                        startActivity(intent);
                        break;
                    case R.id.menu4:
                        intent.putExtra(ID_FILE, R.raw.vospitanie);
                        intent.putExtra(TEXT_TITLE, R.string.vospitanie);
                        startActivity(intent);
                        break;
                    case R.id.menu5:
                        intent.putExtra(ID_FILE, R.raw.zakoni_semyi);
                        intent.putExtra(TEXT_TITLE, R.string.zakoni_semyi);
                        startActivity(intent);
                        break;
                    case R.id.menu6:
                        intent.putExtra(ID_FILE, R.raw.chto_takoe_lubov);
                        intent.putExtra(TEXT_TITLE, R.string.chto_takoe_lubov);
                        startActivity(intent);
                        break;
                    case R.id.menu7:
                        intent.putExtra(ID_FILE, R.raw.kak_stat_bogatim);
                        intent.putExtra(TEXT_TITLE, R.string.kak_stat_bogatim);
                        startActivity(intent);
                        break;
                    case R.id.menu8:
                        intent.putExtra(ID_FILE, R.raw.vdohnovenie);
                        intent.putExtra(TEXT_TITLE, R.string.vdohnovenie);
                        startActivity(intent);
                        break;
                    case R.id.menu9:
                        intent.putExtra(ID_FILE, R.raw.kak_motivirovat_sebya);
                        intent.putExtra(TEXT_TITLE, R.string.kak_motivirovat_sebya);
                        startActivity(intent);
                        break;
                    case R.id.menu10:
                        intent.putExtra(ID_FILE, R.raw.tridtsat_zakonov_zhizni);
                        intent.putExtra(TEXT_TITLE, R.string.tridtsat_zakonov_zhizni);
                        startActivity(intent);
                        break;
                    case R.id.menu11:
                        intent.putExtra(ID_FILE, R.raw.vliyaem_na_lyudey);
                        intent.putExtra(TEXT_TITLE, R.string.vliyaem_na_lyudey);
                        startActivity(intent);
                        break;
                    case R.id.menu12:
                        intent.putExtra(ID_FILE, R.raw.zastav_sebya_rabotat);
                        intent.putExtra(TEXT_TITLE, R.string.zastav_sebya_rabotat);
                        startActivity(intent);
                        break;
                    default:
                }
                soundClick();
                return true;
            }
        });

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
        database.close();
        super.onDestroy();
    }

    @OnClick({R.id.buttonBook, R.id.buttonShare, R.id.buttonRate, R.id.button})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.buttonBook:
                soundClick();
                popup.show();
                break;
            case R.id.buttonShare:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, textView.getText()+"\n \n"+
                        "https://play.google.com/store/apps/details?id=com.mannydev.jewswisdom");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
            case R.id.buttonRate:
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("market://details?id=com.mannydev.jewswisdom"));
                view.getContext().startActivity(intent);
                break;
            case R.id.button:
                textView.startAnimation(animation);
                soundClick();
                int a = 0, b = dataList.size();
                int random_number = a + (int) (Math.random() * b);
                textView.setText(dataList.get(random_number));
                break;
        }
    }

    private void soundClick(){
        mp.start();
    }
}
