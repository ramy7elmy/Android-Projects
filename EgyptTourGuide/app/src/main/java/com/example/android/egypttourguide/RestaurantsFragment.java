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
public class RestaurantsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.places_list, container, false);

        // Create a list of places.
        final ArrayList<Place> places = new ArrayList<>();
        places.add(new Place(getString(R.string.r1n),
                getString(R.string.r1a),
                getString(R.string.r1p), R.drawable.rbr_1,
                30.036562, 31.229530));
        places.add(new Place(getString(R.string.r2n),
                getString(R.string.r2a),
                getString(R.string.r2p), R.drawable.rlur_1,
                30.073950, 31.228164));
        places.add(new Place(getString(R.string.r3n),
                getString(R.string.r3a),
                getString(R.string.r3p), R.drawable.rpvr_1,
                30.043518, 31.231816));
        places.add(new Place(getString(R.string.r4n),
                getString(R.string.r4a),
                getString(R.string.r4p), R.drawable.rbsr_1,
                30.046205, 31.232014));
        places.add(new Place(getString(R.string.r5n),
                getString(R.string.r5a),
                getString(R.string.r5p), R.drawable.rssr_1,
                30.049978, 31.235807));
        places.add(new Place(getString(R.string.r6n),
                getString(R.string.r6a),
                getString(R.string.r6p), R.drawable.rscr_1,
                30.074402, 31.343962));
        places.add(new Place(getString(R.string.r7n),
                getString(R.string.r7a),
                getString(R.string.r7p), R.drawable.rafr_1,
                30.024330, 31.217596));

        // Create an {@link PlacesAdapter}, whose data source is a list of {@link places}. The
        // adapter knows how to create list items for each item in the list.
        PlacesAdapter adapter = new PlacesAdapter(getActivity(),
                places, R.color.fragment_restaurants);

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