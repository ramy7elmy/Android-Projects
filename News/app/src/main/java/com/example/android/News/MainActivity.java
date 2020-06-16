package com.example.android.News;

import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.preference.PreferenceManager;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Event>> {

    private static final int EVENT_LOADER_ID = 1;
    private static final String REQUEST_URL = "https://content.guardianapis.com/search";

    private ProgressBar mProgressBar;
    private TextView mEmtyView;
    private EventAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // Find view with id feedBackTextView.
        mEmtyView = findViewById(R.id.feedBackTextView);

        // Find view with id mProgressBar.
        mProgressBar = findViewById(R.id.progressBar);

        // Instance of connectivity manager and call connectivity service.
        ConnectivityManager cm = (ConnectivityManager)
                getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        assert cm != null;
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();

        if (!isConnected) {
            mProgressBar.setVisibility(View.GONE);
            mEmtyView.setText(R.string.no_internet);
            return;
        }

        // Show the loader indicator.
        mProgressBar.setVisibility(View.VISIBLE);

        LoaderManager loaderManager = getSupportLoaderManager();
        loaderManager.initLoader(EVENT_LOADER_ID, null, this);

        // Find a reference to the {@link ListView} in the layout
        ListView listView = findViewById(R.id.list_View);
        listView.setEmptyView(mEmtyView);

        // Create a new mAdapter that takes an empty list of events as input.
        mAdapter = new EventAdapter(this, new ArrayList<Event>());

        // Set the mAdapter on the {@link ListView}
        // so the list can be populated in the user interface.
        listView.setAdapter(mAdapter);

        // Set an item click listener on the ListView, which sends an intent to a web browser
        // to open a website with more information about the selected event.
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                // Find the current event that was clicked on
                Event currentEvent = mAdapter.getItem(position);

                // Send the intent to launch a new activity.
                assert currentEvent != null;
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(currentEvent.getWebUrl())));
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            startActivity(new Intent(this, SettingsActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Instantiate and return a new Loader for the given ID.
     *
     * <p>This will always be called from the process's main thread.
     *
     * @param id   The ID whose loader is to be created.
     * @param args Any arguments supplied by the caller.
     * @return Return a new Loader instance that is ready to start loading.
     */
    @NonNull
    @Override
    public Loader<List<Event>> onCreateLoader(int id, @Nullable Bundle args) {

        SharedPreferences sharedPreferences =
                PreferenceManager.getDefaultSharedPreferences(this);

        String section = sharedPreferences.getString(
                getString(R.string.section_key),
                getString(R.string.section_def_value)
        );

        // parse breaks apart the URI string that's passed into its parameter.
        Uri baseUri = Uri.parse(REQUEST_URL);

        // buildUpon prepares the baseUri that we just parsed so we can add query parameters to it.
        Uri.Builder builder = baseUri.buildUpon();

        // Append query parameter and its value. For example.
        builder.appendQueryParameter("section", section);
        builder.appendQueryParameter("api-key", "ca901b53-f05f-4abe-bdd3-86f50fca7a8b");
        builder.appendQueryParameter("show-tags", "contributor");

        // Return the completed uri.
        return new EventLoader(this, builder.toString());
    }

    /**
     * Called when a previously created loader has finished its load.  Note
     * that normally an application is <em>not</em> allowed to commit fragment
     * transactions while in this call, since it can happen after an
     * activity's state is saved.  See {@link FragmentManager#beginTransaction()
     * FragmentManager.openTransaction()} for further discussion on this.
     *
     * <p>This function is guaranteed to be called prior to the release of
     * the last data that was supplied for this Loader.  At this point
     * you should remove all use of the old data (since it will be released
     * soon), but should not do your own release of the data since its Loader
     * owns it and will take care of that.  The Loader will take care of
     * management of its data so you don't have to.  In particular:
     *
     * <ul>
     * <li> <p>The Loader will monitor for changes to the data, and report
     * them to you through new calls here.  You should not monitor the
     * data yourself.  For example, if the data is a {@link Cursor}
     * and you place it in a {@link CursorAdapter}, use
     * the {@link CursorAdapter#CursorAdapter(Context,
     * Cursor, int)} constructor <em>without</em> passing
     * in either {@link CursorAdapter#FLAG_AUTO_REQUERY}
     * or {@link CursorAdapter#FLAG_REGISTER_CONTENT_OBSERVER}
     * (that is, use 0 for the flags argument).  This prevents the CursorAdapter
     * from doing its own observing of the Cursor, which is not needed since
     * when a change happens you will get a new Cursor throw another call
     * here.
     * <li> The Loader will release the data once it knows the application
     * is no longer using it.  For example, if the data is
     * a {@link Cursor} from a {@link CursorLoader},
     * you should not call close() on it yourself.  If the Cursor is being placed in a
     * {@link CursorAdapter}, you should use the
     * {@link CursorAdapter#swapCursor(Cursor)}
     * method so that the old Cursor is not closed.
     * </ul>
     *
     * <p>This will always be called from the process's main thread.
     *
     * @param loader The Loader that has finished.
     * @param data   The data generated by the Loader.
     */
    @Override
    public void onLoadFinished(@NonNull Loader<List<Event>> loader, List<Event> data) {
        mAdapter.clear();
        mProgressBar.setVisibility(View.GONE);
        if (data != null && !data.isEmpty())
            mAdapter.addAll(data);
        else mEmtyView.setText(R.string.no_news);
    }

    /**
     * Called when a previously created loader is being reset, and thus
     * making its data unavailable.  The application should at this point
     * remove any references it has to the Loader's data.
     *
     * <p>This will always be called from the process's main thread.
     *
     * @param loader The Loader that is being reset.
     */
    @Override
    public void onLoaderReset(@NonNull Loader<List<Event>> loader) {
        mAdapter.clear();
    }
}
