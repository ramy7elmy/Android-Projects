package com.example.android.News;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Helper methods related to requesting and receiving event data from the Guardian.
 */
final class Helper {

    private static final int READ_TIME_OUT = 10000;
    private static final int CONNECT_TIME_OUT = 15000;

    /**
     * Create a private constructor because no one should ever create a {@link Helper} object.
     * This class is only meant to hold static variables and methods, which can be accessed
     * directly from the class name Helper (and an object instance of Helper is not needed).
     */
    private Helper() {
    }

    // Fetching data via network.
    static List<Event> fetchEventData(String requestUrl) {

        // Create URL object
        URL url = createUrl(requestUrl);

        // Perform HTTP request to the URL and receive a JSON response back
        String jsonResponse;
        jsonResponse = makeHttpRequest(url);

        // Return the list of {@link Event}s
        return extractFeaturesFromJson(jsonResponse);
    }

    /**
     * Returns new URL object from the given string URL.
     */
    private static URL createUrl(String urlString) {
        URL url = null;
        try {
            url = new URL(urlString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return url;
    }

    /**
     * Make an HTTP request to the given URL and return a String as the response.
     */
    private static String makeHttpRequest(URL url) {

        String jsonResponse = "";

        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection httpURLConnection = null;
        InputStream inputStream = null;
        try {
            httpURLConnection = (HttpURLConnection) url.openConnection();
            httpURLConnection.setReadTimeout(READ_TIME_OUT);
            httpURLConnection.setConnectTimeout(CONNECT_TIME_OUT);
            httpURLConnection.setRequestMethod("GET");
            httpURLConnection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // If the request was successful (response code 200),
        // then read the input stream and parse the response.
        try {
            assert httpURLConnection != null;
            if (httpURLConnection.getResponseCode() == 200) {
                inputStream = httpURLConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else Log.e(Helper.class.toString(),
                    "Error response code:" + httpURLConnection.getResponseCode());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpURLConnection != null)
                httpURLConnection.disconnect();
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return jsonResponse;
    }

    /**
     * Convert the {@link InputStream} into a String which contains the
     * whole JSON response from the server.
     */
    private static String readFromStream(InputStream inputStream) {

        StringBuilder stringBuilder = new StringBuilder();

        if (inputStream != null) {
            InputStreamReader inputStreamReader =
                    new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            try {
                String line = reader.readLine();

                while (line != null) {
                    stringBuilder.append(line);
                    line = reader.readLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }

    /**
     * Return a list of {@link Event} objects that has been built up from
     * parsing the given JSON response.
     */
    private static List<Event> extractFeaturesFromJson(String jsonResponse) {

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(jsonResponse))
            return null;

        // Create an empty ArrayList that we can start adding events to.
        List<Event> events = new ArrayList<>();

        // Try to parse the JSON response string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {
            // Create a JSONObject from the JSON response string
            JSONObject baseJsonObject = new JSONObject(jsonResponse);

            // Extract the JSONObject associated with the key called "response".
            JSONObject responseObject = baseJsonObject.getJSONObject("response");

            // Extract the JSONArray associated with the key called "results".
            JSONArray resultsArray = responseObject.getJSONArray("results");

            // For each event in the resultsArray, create an {@link Event} object.
            for (int i = 0; i < resultsArray.length(); i++) {

                JSONObject event = resultsArray.getJSONObject(i);

                // Extract the value for the key called "sectionName".
                String section = event.getString("sectionName");

                // Extract the value for the key called "webTitle".
                String title = event.getString("webTitle");

                // Extract the value for the key called "webPublicationDate".
                String date = event.getString("webPublicationDate");

                // Extract the value for the key called "webUrl".
                String webUrl = event.getString("webUrl");

                // Extract the value for the array called "tags".
                JSONArray tags = event.getJSONArray("tags");

                // Get the first object of tags.
                JSONObject contributor = tags.getJSONObject(0);

                // Extract the value for the key called "webTitle".
                String contributorName = contributor.getString("webTitle");

                // Create a new {@link Event} object with the section, title, date,
                // and webUrl from the JSON response.
                Event newEvent = new Event(section, title, date, webUrl, contributorName);

                // Add the new {@link Event} to the list of events.
                events.add(newEvent);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Return the list of events.
        return events;
    }
}
