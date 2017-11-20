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

public class Vopros7 extends Fragment {

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

        txtVopros.setText("Вы заметили место для паркования машины, но, для того чтобы там припарковаться, Вам придется дать задний ход и буквально втиснуться в него. Ваши действия?");
        btnA.setText("Поищу другое место.");
        btnB.setText("Осторожно припаркую машину там, где решил.");
        btnC.setText("Не раздумывая, дам задний ход и припаркую машину.");

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
        ActivityTestMindSex.fTrans.replace(R.id.fragmentContainer, new Vopros8());
        ActivityTestMindSex.fTrans.commit();
    }

    private void soundClick() {
        mp.start();
    }
}
