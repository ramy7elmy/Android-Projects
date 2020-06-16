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
public class HotelsFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.places_list, container, false);

        // Create a list of places.
        final ArrayList<Place> places = new ArrayList<>();
        places.add(new Place(getString(R.string.hot1n),
                getString(R.string.hot1a),
                getString(R.string.hot1p), R.drawable.hofs_1,
                30.024457, 31.216989));
        places.add(new Place(getString(R.string.hot2n),
                getString(R.string.hot2a),
                getString(R.string.hot2p), R.drawable.honr_1,
                30.046153, 31.232013));
        places.add(new Place(getString(R.string.hot3n),
                getString(R.string.hot3a),
                getString(R.string.hot3p), R.drawable.hoics_1,
                30.073154, 31.346091));
        places.add(new Place(getString(R.string.hot4n),
                getString(R.string.hot4a),
                getString(R.string.hot4p), R.drawable.hojw_1,
                30.072241, 31.434352));
        places.add(new Place(getString(R.string.hot5n),
                getString(R.string.hot5a),
                getString(R.string.hot5p), R.drawable.hoss_1,
                30.072915, 31.344285));
        places.add(new Place(getString(R.string.hot6n),
                getString(R.string.hot6a),
                getString(R.string.hot6p), R.drawable.hogg_1,
                29.975110, 31.141115));
        places.add(new Place(getString(R.string.hot7n),
                getString(R.string.hot7a),
                getString(R.string.hot7p), R.drawable.hosh_1,
                30.047033, 31.235586));

        // Create an {@link PlacesAdapter}, whose data source is a list of {@link places}. The
        // adapter knows how to create list items for each item in the list.
        PlacesAdapter adapter = new PlacesAdapter(getActivity(), places, R.color.fragment_hotels);

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