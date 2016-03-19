package edu.washington.chau93.hvz_app.models;

/**
 * Created by Aaron on 3/19/2016.
 */
public class CustomLatLng {
    private double latitude;
    private double longitude;

    public CustomLatLng(final double lat, final double lon) {
        latitude = lat;
        longitude = lon;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
