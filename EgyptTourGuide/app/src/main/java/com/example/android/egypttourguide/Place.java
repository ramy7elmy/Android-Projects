package com.example.android.egypttourguide;

/**
 * {@link Place} represents a place user might want to visit.
 */
class Place {
    /**
     * mName is place name.
     * <p>
     * mAddress is place address.
     * <p>
     * mPhone is place phone.
     */
    private String mName;
    private String mAddress;
    private String mPhone;

    /**
     * mPhotoRID is the place icon resource ID.
     */
    private int mPhotoRID;

    /**
     * mXCordinate is the place x google map coordinate.
     * <p>
     * mYCordinate is the place y google map coordinate.
     */
    private double mXCoordinate, mYCoordinate;

    /**
     * Constructor for places without phone.
     *
     * @param mName        is place instant name.
     * @param mAddress     is place instant address.
     * @param mPhotoRID    is the place instant icon resource ID.
     * @param mXCoordinate is the place instant x google map coordinate.
     * @param mYCoordinate is the place instant y google map coordinate.
     */
    Place(String mName, String mAddress, int mPhotoRID, double mXCoordinate,
          double mYCoordinate) {
        this.mName = mName;
        this.mAddress = mAddress;
        this.mPhotoRID = mPhotoRID;
        this.mXCoordinate = mXCoordinate;
        this.mYCoordinate = mYCoordinate;
    }

    /**
     * Constructor for places with phone.
     *
     * @param mName        is place instant name.
     * @param mAddress     is place instant address.
     * @param mPhone       is place instant phone.
     * @param mPhotoRID    is the place instant icon resource ID.
     * @param mXCoordinate is the place instant x google map coordinate.
     * @param mYCoordinate is the place instant y google map coordinate.
     */
    Place(String mName, String mAddress, String mPhone, int mPhotoRID, double mXCoordinate,
          double mYCoordinate) {
        this.mName = mName;
        this.mAddress = mAddress;
        this.mPhone = mPhone;
        this.mPhotoRID = mPhotoRID;
        this.mXCoordinate = mXCoordinate;
        this.mYCoordinate = mYCoordinate;
    }

    /**
     * @return place name.
     */
    String getName() {
        return mName;
    }

    /**
     * @return place address.
     */
    String getAddress() {
        return mAddress;
    }

    /**
     * @return place phone.
     */
    String getPhone() {
        return mPhone;
    }

    /**
     * @return place icon resource ID.
     */
    int getPhotoRID() {
        return mPhotoRID;
    }

    /**
     * @return place x google map coordinate.
     */
    double getXCoordinate() {
        return mXCoordinate;
    }

    /**
     * @return place y google map coordinate.
     */
    double getYCoordinate() {
        return mYCoordinate;
    }

    boolean hasPhone() {
        return mPhone != null;
    }
}