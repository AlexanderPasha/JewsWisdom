package com.mannydev.jewswisdom.testuverenost;


import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mannydev.jewswisdom.ActivityTestUverenost;
import com.mannydev.jewswisdom.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class Vopros2 extends Fragment {

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

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_vopros, null);
        unbinder = ButterKnife.bind(this, view);

        mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.mysound);

        txtVopros.setText("Ваш знак зодиака?");
        btnA.setText("Лев, Овен, Козерог, Телец");
        btnB.setText("Близнецы, Скорпион, Водолей, Стрелец");
        btnC.setText("Рыбы, Рак, Дева, Весы");

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btnA, R.id.btnB, R.id.btnC})
    public void onViewClicked(View view) {
        switch (view.getId()) {

            case R.id.btnA:
                ActivityTestUverenost.testResults.addA();
                soundClick();
                goNextQuestion();
                break;
            case R.id.btnB:
                ActivityTestUverenost.testResults.addB();
                soundClick();
                goNextQuestion();
                break;
            case R.id.btnC:
                ActivityTestUverenost.testResults.addC();
                soundClick();
                goNextQuestion();
                break;
        }
    }

    private void goNextQuestion(){
        ActivityTestUverenost.fTrans = getFragmentManager().beginTransaction();
        ActivityTestUverenost.fTrans.replace(R.id.fragmentContainer, new Vopros3());
        ActivityTestUverenost.fTrans.commit();
    }

    private void soundClick() {
        mp.start();
    }
}
