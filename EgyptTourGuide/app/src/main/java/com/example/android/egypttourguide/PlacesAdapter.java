package com.example.android.egypttourguide;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

/**
 * {@link PlacesAdapter} is an {@link ArrayAdapter} that can provide the layout for each list item
 * based on a data source, which is a list of {@link Place} objects.
 */
public class PlacesAdapter extends ArrayAdapter<Place> {

    /**
     * Resource ID for the background color for this list of words
     */
    private int mColorResourceId;

    /**
     * Create a new {@link PlacesAdapter} object.
     *
     * @param context         is the current context (i.e. Activity) that the adapter is being created in.
     * @param places          is the list of {@link Place}s to be displayed.
     * @param colorResourceId is the resource ID for the background color for this list of words
     */
    PlacesAdapter(Context context, ArrayList<Place> places, int colorResourceId) {
        super(context, 0, places);
        mColorResourceId = colorResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(
                    R.layout.places_list_item, parent, false);
        }

        // Get the {@link Place} object located at this position in the list
        Place currentPlace = getItem(position);

        // Find the TextView in the places_list_item.xml layout with the ID name_text_view.
        TextView nameTextView = convertView.findViewById(R.id.name_text_view);
        // Get the name from the currentPlace object and set this text on
        // the name_text_view.
        assert currentPlace != null;
        nameTextView.setText(currentPlace.getName());

        // Find the TextView in the places_list_item.xml layout with the ID address_text_view.
        TextView addressTextView = convertView.findViewById(R.id.address_text_view);
        // Get the name from the currentPlace object and set this text on
        // the address_text_view.
        addressTextView.setText(currentPlace.getAddress());

        // Find the TextView in the places_list_item.xml layout with the ID phone_text_view.
        TextView phoneTextView = convertView.findViewById(R.id.phone_text_view);
        // Check if a phone is provided for this place or not
        if (currentPlace.hasPhone()) {
            // Get the phone from the currentPlace object and set this text on
            // the phone_text_view.
            phoneTextView.setText(currentPlace.getPhone());
            // Make sure the view is visible
            phoneTextView.setVisibility(View.VISIBLE);
        }
        // Otherwise hide the TextView (set visibility to GONE)
        else phoneTextView.setVisibility(View.GONE);

        // Find the ImageView in the places_list_item.xml layout with the ID image.
        ImageView imageView = convertView.findViewById(R.id.image);
        // Display the provided image based on the resource ID
        imageView.setImageResource(currentPlace.getPhotoRID());

        // Set the theme color for the list item
        View textContainer = convertView.findViewById(R.id.text_container);
        // Find the color that the resource ID maps to
        int color = ContextCompat.getColor(getContext(), mColorResourceId);
        // Set the background color of the text container View
        textContainer.setBackgroundColor(color);

        // Return the whole list item layout (containing TextViews and an Image) so that it can be
        // shown in the ListView.
        return convertView;
    }
}
