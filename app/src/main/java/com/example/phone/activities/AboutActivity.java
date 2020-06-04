package com.example.phone.activities;

import android.os.Bundle;
import android.widget.TextView;

import com.example.phone.R;
import com.example.phone.utility.network.AbstractAPICall;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

/**
 * The class that controls the about page
 */
final public class AboutActivity extends AppCompatActivity {

    private TextView mTVName;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        this.mTVName = findViewById(R.id.tv_about_name);
    }//end onCreate()

    public AboutActivity(AbstractAPICall call) {
        super();


    }//end AboutActivity()
}//end AboutActivity
