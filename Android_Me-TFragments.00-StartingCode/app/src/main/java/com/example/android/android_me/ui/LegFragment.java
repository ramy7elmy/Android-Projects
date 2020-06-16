package com.example.android.android_me.ui;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

/**
 * A simple {@link Fragment} subclass.
 */
public class LegFragment extends Fragment {

    private int imageIndex;

    public LegFragment(){}

    public LegFragment(int legIndex) {
        imageIndex = legIndex;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState != null){
            imageIndex = savedInstanceState.getInt("index");
        }

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_leg, container, false);

        final ImageView imageView = rootView.findViewById(R.id.leg_image_view);
        imageView.setImageResource(AndroidImageAssets.getLegs().get(imageIndex));
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (imageIndex < AndroidImageAssets.getLegs().size()-1){
                    imageIndex++;
                    imageView.setImageResource(AndroidImageAssets.getLegs().get(imageIndex));
                } else {
                    imageIndex = 0;
                    imageView.setImageResource(AndroidImageAssets.getLegs().get(imageIndex));
                }
            }
        });
        return rootView;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        outState.putInt("index", imageIndex);
    }
}
