package com.example.truyencuoi;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private String topicName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //show Fragment
        showFrg(new M000SplashFrg());
    }

    private void showFrg(M000SplashFrg frg) {
        // id:R.id.ln_main is activity_main layout xml
        getSupportFragmentManager().beginTransaction().replace(R.id.ln_main, frg, null).commit();
    }

    public void gotoM001Screen() {
        getSupportFragmentManager().beginTransaction().replace(R.id.ln_main, new M001TopicFrg(), null).commit();
    }

    public void gotoM002Screen(String topicName) {
    // topicName is tag on m001_frg_topic layout
    }
}