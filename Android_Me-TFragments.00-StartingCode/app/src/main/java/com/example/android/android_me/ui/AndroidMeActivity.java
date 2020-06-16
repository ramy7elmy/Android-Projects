/*
* Copyright (C) 2017 The Android Open Source Project
*
* Licensed under the Apache License, Version 2.0 (the "License");
* you may not use this file except in compliance with the License.
* You may obtain a copy of the License at
*
*  	http://www.apache.org/licenses/LICENSE-2.0
*
* Unless required by applicable law or agreed to in writing, software
* distributed under the License is distributed on an "AS IS" BASIS,
* WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
* See the License for the specific language governing permissions and
* limitations under the License.
*/

package com.example.android.android_me.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import com.example.android.android_me.R;

// This activity will display a custom Android image composed of three body parts: head, body, and legs
public class AndroidMeActivity extends AppCompatActivity {

    private int headIndex;
    private int bodyIndex;
    private int legIndex;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android_me);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        assert bundle != null;
        headIndex = bundle.getInt("headIndex");
        bodyIndex = bundle.getInt("bodyIndex");
        legIndex = bundle.getInt("legIndex");

        if (savedInstanceState == null) {
            FragmentManager fragmentManager = getSupportFragmentManager();

            HeadFragment headFragment = new HeadFragment(headIndex);
            fragmentManager.beginTransaction().add(R.id.head_frame, headFragment)
                    .commit();
            BodyFragment bodyFragment = new BodyFragment(bodyIndex);
            fragmentManager.beginTransaction().add(R.id.body_frame, bodyFragment)
                    .commit();
            LegFragment legFragment = new LegFragment(legIndex);
            fragmentManager.beginTransaction().add(R.id.leg_frame, legFragment)
                    .commit();
        }
    }
}
