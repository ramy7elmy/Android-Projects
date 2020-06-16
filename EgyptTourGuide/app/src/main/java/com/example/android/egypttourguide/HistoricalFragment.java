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
public class HistoricalFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.places_list, container, false);

        // Create a list of places.
        final ArrayList<Place> places = new ArrayList<>();
        places.add(new Place(getString(R.string.his1n),
                getString(R.string.his1a), R.drawable.hkk_1,
                30.048045, 31.262243));
        places.add(new Place(getString(R.string.his2n),
                getString(R.string.his2a), R.drawable.hbc_1,
                30.166116, 31.037379));
        places.add(new Place(getString(R.string.his3n),
                getString(R.string.his3a), R.drawable.hcof_1,
                30.062970, 31.246442));
        places.add(new Place(getString(R.string.his4n),
                getString(R.string.his4a), R.drawable.hit_1,
                30.029025, 31.249383));
        places.add(new Place(getString(R.string.his5n),
                getString(R.string.his5a), R.drawable.hem_1,
                30.047338, 31.233669));
        places.add(new Place(getString(R.string.his6n),
                getString(R.string.his6a), R.drawable.hpy_1,
                29.979561, 31.134199));
        places.add(new Place(getString(R.string.his7n),
                getString(R.string.his7a), R.drawable.hlx_1,
                25.724388, 32.656019));

        // Create an {@link PlacesAdapter}, whose data source is a list of {@link places}. The
        // adapter knows how to create list items for each item in the list.
        PlacesAdapter adapter = new PlacesAdapter(getActivity(), places,
                R.color.fragment_historical);

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