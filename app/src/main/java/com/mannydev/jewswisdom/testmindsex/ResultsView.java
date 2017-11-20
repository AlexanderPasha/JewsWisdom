package com.mannydev.jewswisdom.testmindsex;

import android.app.Fragment;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;


import com.mannydev.jewswisdom.ActivityTestMindSex;
import com.mannydev.jewswisdom.MainActivity;
import com.mannydev.jewswisdom.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ResultsView extends Fragment {
    @BindView(R.id.txtResultTitle)
    TextView txtResultTitle;
    @BindView(R.id.txtResult)
    TextView txtResult;
    @BindView(R.id.btnRetry)
    Button btnRetry;
    @BindView(R.id.btnShare)
    Button btnShare;
    @BindView(R.id.btnBackToMain)
    Button btnBackToMain;

    Unbinder unbinder;
    MediaPlayer mp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_result, null);
        unbinder = ButterKnife.bind(this, view);

        mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.mysound);

        String result = ActivityTestMindSex.testMindSex.showResults();

        switch (result){
            case "MM":
                txtResultTitle.setText("Склад мышления : Мужской");
                txtResult.setText(getResources().getText(R.string.test_mind_sex_male_male));
                break;
            case "MF":
                txtResultTitle.setText("Склад мышления : Женский");
                txtResult.setText(getResources().getText(R.string.test_mind_sex_male_female));
                break;
            case "FF":
                txtResultTitle.setText("Склад мышления : Женский");
                txtResult.setText(getResources().getText(R.string.test_mind_sex_female_female));
                break;
            case "FM":
                txtResultTitle.setText("Склад мышления : Мужской");
                txtResult.setText(getResources().getText(R.string.test_mind_sex_female_male));
                break;
            case "BOTH":
                txtResultTitle.setText("Склад мышления : 50/50");
                txtResult.setText(getResources().getText(R.string.test_mind_sex_both));
                break;
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btnRetry, R.id.btnShare, R.id.btnBackToMain})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnRetry:
                ActivityTestMindSex.testMindSex = new TestMindSex();
                ActivityTestMindSex.fTrans = getFragmentManager().beginTransaction();
                ActivityTestMindSex.fTrans.replace(R.id.fragmentContainer, new Vopros0());
                ActivityTestMindSex.fTrans.commit();
                break;
            case R.id.btnShare:
                Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, txtResultTitle.getText()+"\n \n"+
                        txtResult.getText() + "\n \n" +
                        "https://play.google.com/store/apps/details?id=com.mannydev.jewswisdom");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
            case R.id.btnBackToMain:
                Intent intent = new Intent(getActivity().getApplicationContext(), MainActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void soundClick() {
        mp.start();
    }
}
