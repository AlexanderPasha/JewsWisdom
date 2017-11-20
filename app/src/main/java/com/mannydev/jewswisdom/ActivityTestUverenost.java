package com.mannydev.jewswisdom;

import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.constraint.Guideline;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.mannydev.jewswisdom.testuverenost.TestResults;
import com.mannydev.jewswisdom.testuverenost.Vopros1;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ActivityTestUverenost extends AppCompatActivity {

    public static TestResults testResults;

    public static FragmentTransaction fTrans;
    @BindView(R.id.adView)
    AdView adView;
    @BindView(R.id.txtTitle)
    TextView txtTitle;
    @BindView(R.id.guideline)
    Guideline guideline;
    @BindView(R.id.fragmentContainer)
    FrameLayout fragmentContainer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_uverenost);
        ButterKnife.bind(this);

        AdRequest adRequest = new AdRequest.Builder().build();
        adView.loadAd(adRequest);

        testResults = new TestResults();
        fTrans = getFragmentManager().beginTransaction();
        fTrans.add(fragmentContainer.getId(), new Vopros1(), null);
        fTrans.commit();

    }

}
