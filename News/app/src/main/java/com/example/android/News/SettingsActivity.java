package com.example.android.News;

import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import androidx.preference.PreferenceManager;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.settings_activity);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.settings, new SettingsFragment())
                .commit();
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    public static class SettingsFragment extends PreferenceFragmentCompat implements Preference.OnPreferenceChangeListener {
        @Override
        public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
            setPreferencesFromResource(R.xml.root_preferences, rootKey);

            Preference section = findPreference(getString(R.string.section_key));
            assert section != null;
            bindPreferenceSummaryToValue(section);
        }

        // Assign the chosen value to preference value.
        private void bindPreferenceSummaryToValue(Preference preference) {

            preference.setOnPreferenceChangeListener(this);

            SharedPreferences preferences =
                    PreferenceManager.
                            getDefaultSharedPreferences(preference.getContext());

            String prefranceString =
                    preferences.getString(preference.getKey(), "");

            onPreferenceChange(preference, prefranceString);

        }

        /**
         * Called when a preference has been changed by the user. This is called before the state
         * of the preference is about to be updated and before the state is persisted.
         *
         * @param preference The changed preference
         * @param newValue   The new value of the preference
         * @return {@code true} to update the state of the preference with the new value
         */
        @Override
        public boolean onPreferenceChange(Preference preference, Object newValue) {
            return true;
        }
    }
}