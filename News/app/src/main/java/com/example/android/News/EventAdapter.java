package com.example.android.News;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;

import java.util.List;

/**
 * An {@link EventAdapter} knows how to create a list item layout for each event
 * in the data source (a list of {@link Event} objects).
 * <p>
 * These list item layouts will be provided to an adapter view like ListView
 * to be displayed to the user.
 */
public class EventAdapter extends ArrayAdapter<Event> {

    /**
     * Constructor
     *
     * @param context The current context.
     * @param events  The objects to represent in the ListView.
     */
    EventAdapter(@NonNull Context context, @NonNull List<Event> events) {
        super(context, 0, events);
    }

    /**
     * Returns a list item view that displays information about the event at the given position
     * in the list of events.
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // Check if there is an existing list item view (called convertView) that we can reuse,
        // otherwise, if convertView is null, then inflate a new list item layout.
        if (convertView == null)
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.list_item, parent, false);

        // Find the event at the given position in the list of earthquakes
        Event currentEvent = getItem(position);

        // Find the TextView with view ID sectionTextView.
        TextView sectionView = convertView.findViewById(R.id.sectionTextView);
        // Display the section of the current event in that TextView.
        assert currentEvent != null;
        sectionView.setText(currentEvent.getSection());
        // Display the section color of the current event in that TextView.
        sectionView.setBackgroundColor(getSectionColor(currentEvent.getSection()));

        // Find the TextView with view ID titleTextView.
        TextView titleView = convertView.findViewById(R.id.titleTextView);
        // Display the title of the current event in that TextView.
        titleView.setText(currentEvent.getTitle());

        // Find the TextView with view ID dateTextView.
        TextView dateView = convertView.findViewById(R.id.dateTextView);
        // Display the date of the current event in that TextView.
        dateView.setText(currentEvent.getDate());

        // Find the TextView with view ID contributorView.
        TextView contributorView = convertView.findViewById(R.id.contributorView);
        // Display the contributor of the current event in that TextView.
        contributorView.setText(currentEvent.getContributor());

        // Find the TextView with view ID container.
        View containerView = convertView.findViewById(R.id.container);
        // Display the section color of the current event in that view.
        containerView.setBackgroundColor(getSectionColor(currentEvent.getSection()));

        return convertView;
    }

    /**
     * Return the color for the section based on the category of the event.
     *
     * @param section of the event.
     */
    private int getSectionColor(String section) {
        int colorId;
        switch (section) {
            case "Football":
                colorId = R.color.footballCatColor;
                break;
            case "Politics":
                colorId = R.color.politicsCatColor;
                break;
            case "News":
                colorId = R.color.newsCatColor;
                break;
            case "Sport":
                colorId = R.color.sportCatColor;
                break;
            default:
                colorId = R.color.defaultCatColor;
                break;
        }
        return ContextCompat.getColor(getContext(), colorId);
    }
}
