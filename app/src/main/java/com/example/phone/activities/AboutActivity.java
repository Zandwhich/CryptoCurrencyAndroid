package com.example.phone.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.example.phone.R;
import com.example.phone.utility.network.AbstractAPICall;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * The class that controls the 'About' page
 */
final public class AboutActivity extends AppCompatActivity {

    private TextView mTVName;

    private TextView mTVSummary;

    private TextView mTVAcceptedCryptos;

    private TextView mTVAcceptedFiats;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        this.mTVName = findViewById(R.id.tv_about_name);
        this.mTVSummary = findViewById(R.id.tv_about_summary);
        this.mTVAcceptedCryptos = findViewById(R.id.tv_about_accepted_cryptos);
        this.mTVAcceptedFiats = findViewById(R.id.tv_about_accepted_fiats);
    }//end onCreate()

    public AboutActivity(AbstractAPICall call) {
        super();


    }//end AboutActivity()
}//end AboutActivity
