package com.example.android.android_me.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.example.android.android_me.R;
import com.example.android.android_me.data.AndroidImageAssets;

/**
 * A simple {@link Fragment} subclass.
 */
public class MasterListFragment extends Fragment {

    private int headIndex;
    private int bodyIndex;
    private int legIndex;
    private boolean twoPane;

    public MasterListFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_master_list, container, false);
        final GridView gridView = rootView.findViewById(R.id.images_grid_view);
        if (getString(R.string.screen_size).equals("tablet")) {
            twoPane = true;
            gridView.setNumColumns(2);
        } else {
            twoPane = false;
        }
        MasterListAdapter listAdapter = new MasterListAdapter(getContext(), AndroidImageAssets.getAll());
        gridView.setAdapter(listAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                int bodyPartNumber = position / 12;
                int listIndex = position - 12 * bodyPartNumber;

                switch (bodyPartNumber) {
                    case 0:
                        headIndex = listIndex;
                        break;
                    case 1:
                        bodyIndex = listIndex;
                        break;
                    case 2:
                        legIndex = listIndex;
                        break;
                }
                if (twoPane) {
                    FragmentManager fragmentManager = getFragmentManager();

                    HeadFragment headFragment = new HeadFragment(headIndex);
                    assert fragmentManager != null;
                    fragmentManager.beginTransaction().replace(R.id.head_frame, headFragment)
                            .commit();
                    BodyFragment bodyFragment = new BodyFragment(bodyIndex);
                    fragmentManager.beginTransaction().replace(R.id.body_frame, bodyFragment)
                            .commit();
                    LegFragment legFragment = new LegFragment(legIndex);
                    fragmentManager.beginTransaction().replace(R.id.leg_frame, legFragment)
                            .commit();
                } else {
                    Bundle bundle = new Bundle();
                    bundle.putInt("headIndex", headIndex);
                    bundle.putInt("bodyIndex", bodyIndex);
                    bundle.putInt("legIndex", legIndex);

                    Intent intent = new Intent(getContext(), AndroidMeActivity.class);
                    intent.putExtras(bundle);

                    startActivity(intent);
                }
            }
        });
        return rootView;
    }
}