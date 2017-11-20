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

public class Vopros9 extends Fragment {

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

        txtVopros.setText("Вы находитесь в приемной зубного врача. Насколько близко можете Вы сесть к представителю своего пола, не ощущая неловкости?");
        btnA.setText("На расстоянии меньше 15 сантиметров.");
        btnB.setText("От 15 до 60 сантиметров.");
        btnC.setText("Дальше чем на 60 сантиметров.");

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
        ActivityTestMindSex.fTrans.replace(R.id.fragmentContainer, new Vopros10());
        ActivityTestMindSex.fTrans.commit();
    }

    private void soundClick() {
        mp.start();
    }
}
