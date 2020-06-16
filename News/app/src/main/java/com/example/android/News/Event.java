package com.example.android.News;

/**
 * An {@link Event} object contains information related to a single event.
 */
class Event {

    // Section or category of an event.
    private String mSection;

    // Title of an event.
    private String mTitle;

    // Date of an event.
    private String mDate;

    // Web URL of an event.
    private String mWebUrl;

    // Contributor of an event.
    private String mContributor;

    /**
     * Constructs a new {@link Event} object.
     *
     * @param section is the section or category of the event.
     * @param title   is the header of an event.
     * @param date    is the date of an event.
     * @param webUrl  is the website URL to find more details about the event.
     */
    Event(String section, String title, String date, String webUrl, String contributor) {
        this.mSection = section;
        this.mTitle = title;
        this.mDate = date;
        this.mWebUrl = webUrl;
        this.mContributor = contributor;
    }

    /**
     * Returns the section of the event.
     */
    String getSection() {
        return mSection;
    }

    /**
     * Returns the title of the event.
     */
    String getTitle() {
        return mTitle;
    }

    /**
     * Returns the date of the event.
     */
    String getDate() {
        return mDate;
    }

    /**
     * Returns the web URL of the event.
     */
    String getWebUrl() {
        return mWebUrl;
    }

    /**
     * Returns the contributor of the event.
     */
    String getContributor() {
        return mContributor;
    }
}
