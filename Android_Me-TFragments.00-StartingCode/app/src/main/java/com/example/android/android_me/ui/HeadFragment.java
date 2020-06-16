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
public class HeadFragment extends Fragment {

    private int imageIndex;

    public HeadFragment(){}

    public HeadFragment(int headIndex) {
        imageIndex = headIndex;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        if (savedInstanceState != null){
            imageIndex = savedInstanceState.getInt("index");
        }

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_head, container, false);

            final ImageView imageView = rootView.findViewById(R.id.head_image_view);
            imageView.setImageResource(AndroidImageAssets.getHeads().get(imageIndex));
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (imageIndex < AndroidImageAssets.getHeads().size()-1){
                        imageIndex++;
                        imageView.setImageResource(AndroidImageAssets.getHeads().get(imageIndex));
                    } else {
                        imageIndex = 0;
                        imageView.setImageResource(AndroidImageAssets.getHeads().get(imageIndex));
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
