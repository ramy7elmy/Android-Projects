package com.example.android.android_me.ui;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import com.example.android.android_me.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (findViewById(R.id.android_me_linear_layout) != null) {
            if (savedInstanceState == null) {
                FragmentManager fragmentManager = getSupportFragmentManager();

                HeadFragment headFragment = new HeadFragment(0);
                fragmentManager.beginTransaction().add(R.id.head_frame, headFragment)
                        .commit();
                BodyFragment bodyFragment = new BodyFragment(0);
                fragmentManager.beginTransaction().add(R.id.body_frame, bodyFragment)
                        .commit();
                LegFragment legFragment = new LegFragment(0);
                fragmentManager.beginTransaction().add(R.id.leg_frame, legFragment)
                        .commit();
            }
        }
    }
}