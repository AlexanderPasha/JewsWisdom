package com.mannydev.jewswisdom.testmindsex;


import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.InterstitialAd;
import com.mannydev.jewswisdom.ActivityTestMindSex;
import com.mannydev.jewswisdom.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Vopros10 extends Fragment {

    @BindView(R.id.txtVopros)
    TextView txtVopros;
    @BindView(R.id.btnA)
    Button btnA;
    @BindView(R.id.btnB)
    Button btnB;
    @BindView(R.id.btnC)
    Button btnC;

    Unbinder unbinder;
    MediaPlayer mp;
    InterstitialAd mInterstitialAd;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_vopros, null);
        unbinder = ButterKnife.bind(this, view);

        mInterstitialAd = new InterstitialAd(getActivity().getApplicationContext());
        mInterstitialAd.setAdUnitId("ca-app-pub-8078146669032188/5141611002");
        mInterstitialAd.loadAd(new AdRequest.Builder().build());

        mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.mysound);

        txtVopros.setText("Вы зашли поболтать к новому соседу. В квартире тихо, но где-то капает вода из крана. Ваши действия?");
        btnA.setText("Сразу же замечу этот звук, но постараюсь не обращать на него внимания.");
        btnB.setText("Если замечу, то, наверное, скажу об этом сразу.");
        btnC.setText("Это меня совсем не раздражает.");

        return view;
    }

    @Override
    public void onDestroyView() {
        if (mInterstitialAd.isLoaded()) {
            mInterstitialAd.show();
        }
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btnA, R.id.btnB, R.id.btnC})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btnA:
                ActivityTestMindSex.testMindSex.addA();
                soundClick();
                goNextQuestion();
                break;
            case R.id.btnB:
                ActivityTestMindSex.testMindSex.addB();
                soundClick();
                goNextQuestion();
                break;
            case R.id.btnC:
                ActivityTestMindSex.testMindSex.addC();
                soundClick();
                goNextQuestion();
                break;
        }
    }

    private void goNextQuestion(){
        ActivityTestMindSex.fTrans = getFragmentManager().beginTransaction();
        ActivityTestMindSex.fTrans.replace(R.id.fragmentContainer, new ResultsView());
        ActivityTestMindSex.fTrans.commit();
    }

    private void soundClick() {
        mp.start();
    }
}
