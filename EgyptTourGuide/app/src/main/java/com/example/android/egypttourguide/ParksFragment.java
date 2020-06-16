package com.example.android.egypttourguide;


import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class ParksFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.places_list, container, false);

        // Create a list of places.
        final ArrayList<Place> places = new ArrayList<>();
        places.add(new Place(getString(R.string.p1n),
                getString(R.string.p1a), R.drawable.pom_1,
                30.029336, 31.212966));
        places.add(new Place(getString(R.string.p2n),
                getString(R.string.p2a), R.drawable.psp_1,
                30.074605, 31.192198));
        places.add(new Place(getString(R.string.p3n),
                getString(R.string.p3a), R.drawable.pip_1,
                30.054928, 31.336290));
        places.add(new Place(getString(R.string.p4n),
                getString(R.string.p4a), R.drawable.pfp_1,
                30.080607, 31.502167));
        places.add(new Place(getString(R.string.p5n),
                getString(R.string.p5a), R.drawable.pfnp_1,
                30.033498, 31.226398));
        places.add(new Place(getString(R.string.p6n),
                getString(R.string.p6a), R.drawable.pomk_1,
                30.031433, 31.225678));
        places.add(new Place(getString(R.string.p7n),
                getString(R.string.p7a), R.drawable.pgzp_1,
                30.044920, 31.227508));

        // Create an {@link PlacesAdapter}, whose data source is a list of {@link places}. The
        // adapter knows how to create list items for each item in the list.
        PlacesAdapter adapter = new PlacesAdapter(getActivity(), places, R.color.fragment_parks);

        // Find the {@link ListView} object in the view hierarchy of the {@link Activity}.
        // There should be a {@link ListView} with the view ID called list, which is declared in the
        // places_word_list.xml layout file.
        ListView listView = rootView.findViewById(R.id.list);

        // Make the {@link ListView} use the {@link PlacesAdapter} we created above, so that the
        // {@link ListView} will display list items for each {@link Place} in the list.
        listView.setAdapter(adapter);

        // Set a click listener to open google maps when the list item is clicked on
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Place place = places.get(position);
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("geo:" + place.getXCoordinate() + ","
                        + place.getYCoordinate() + getString(R.string.zoom_value)));
                if (intent.resolveActivity(Objects.requireNonNull(getActivity()).
                        getPackageManager()) != null)
                    startActivity(intent);
            }
        });

        // Inflate the layout for this fragment
        return rootView;
    }
}