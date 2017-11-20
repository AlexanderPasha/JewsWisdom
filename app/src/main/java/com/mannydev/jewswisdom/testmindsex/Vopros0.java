package com.mannydev.jewswisdom.testmindsex;

import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mannydev.jewswisdom.ActivityTestMindSex;
import com.mannydev.jewswisdom.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;


public class Vopros0 extends Fragment {

    private static final boolean MALE = true;
    private static final boolean FEMALE = false;

    @BindView(R.id.txtVopros)
    TextView txtVopros;
    @BindView(R.id.btnA)
    Button btnA;
    @BindView(R.id.btnB)
    Button btnB;
    Unbinder unbinder;
    MediaPlayer mp;

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_test_pre_question, null);
        unbinder = ButterKnife.bind(this, view);

        mp = MediaPlayer.create(getActivity().getApplicationContext(), R.raw.mysound);

        txtVopros.setText("Ваш пол?");
        btnA.setText("Мужчина");
        btnB.setText("Женщина");

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.btnA, R.id.btnB})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnA:
                soundClick();
                ActivityTestMindSex.testMindSex.setSex(MALE);
                goNextQuestion();
                break;
            case R.id.btnB:
                soundClick();
                ActivityTestMindSex.testMindSex.setSex(FEMALE);
                goNextQuestion();
                break;
        }
    }

    private void goNextQuestion(){
        ActivityTestMindSex.fTrans = getFragmentManager().beginTransaction();
        ActivityTestMindSex.fTrans.replace(R.id.fragmentContainer, new Vopros1());
        ActivityTestMindSex.fTrans.commit();
    }

    private void soundClick() {
        mp.start();
    }
}