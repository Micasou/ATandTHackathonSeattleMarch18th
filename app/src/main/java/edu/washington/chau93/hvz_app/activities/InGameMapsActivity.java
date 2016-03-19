package edu.washington.chau93.hvz_app.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import java.util.Observer;
import java.util.Observable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.washington.chau93.hvz_app.R;
import edu.washington.chau93.hvz_app.models.Game;
import edu.washington.chau93.hvz_app.models.GameMode;
import edu.washington.chau93.hvz_app.models.GameStatus;

public class InGameMapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnMapClickListener {
    /**
     * Google map object
     */
    private GoogleMap myMap;
    /** upper left hand bound of the user decided map*/

    /**
     * users current location
     */
    private LatLng myPosition;
    /**
     * Keeps track of the users location settings
     */
    private LocationManager myLocationManager;
    /**
     * Creating a criteria object to retrieve provider
     */
    private Criteria myCriteria;
    /**
     * Polygon shape that resizes with the inner+outter bounds
     */
    private Polygon myBounds;
    /** The observer object of the game */
    private Game myGame;
    private List<Double> myLatLngPoints;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    /**
     * Init all variables for the map. Constructor
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_game_activity_maps);

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        myMap = googleMap;
        myLatLngPoints = myGame.getMyBoundries();
        //Checking that users has accepted permissions
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                == PackageManager.PERMISSION_GRANTED) {
            myMap.setMyLocationEnabled(true);
            // Creating a criteria object to retrieve provider
            myCriteria = new Criteria();
            // Getting LocationManager object from System Service LOCATION_SERVICE
            myLocationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
            // Getting the name of the best provider
            String provider = myLocationManager.getBestProvider(myCriteria, true);
            // Getting Current Location
            Location location = myLocationManager.getLastKnownLocation(provider);
            if (location != null) {
                // Getting latitude of the current location
                double latitude = location.getLatitude();
                // Getting longitude of the current location
                double longitude = location.getLongitude();
                // Creating a LatLng object for the current location
                myPosition = new LatLng(latitude, longitude);

                this.drawPolygon();
                myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 19));
            } else {
                //location wasn't retrieved, must do stuff
                //TODO IMPLEMENT ALTERNATIVE
            }
        }
    }

    /**
     * Creates the polygon that outlines the playable map area
     * myLowerRightBound.getPosition().latitude == index 0 myLatLngPoints.
     *  myLowerRightBound.getPosition().longitude == index 1
     * myUpperLeftBound.getPosition().latitude == index 2
     * myUpperLeftBound.getPosition().longitude == index 3
     */
    private void drawPolygon() {
        LatLng tempLatLng = new LatLng((myLatLngPoints.get(0) +myLatLngPoints.get(2)) / 2,
                (myLatLngPoints.get(1) + myLatLngPoints.get(3)) / 2);
        if (myBounds != null)
            myBounds.remove();
        myBounds = myMap.addPolygon(new PolygonOptions()
                .addAll(createRectangle(tempLatLng,
                        (myLatLngPoints.get(0) - myLatLngPoints.get(2)) * 2,
                        (myLatLngPoints.get(1) - myLatLngPoints.get(3)) * 2))
                .addHole(createRectangle(tempLatLng,
                        (myLatLngPoints.get(0) - myLatLngPoints.get(2)) / 2,
                        (myLatLngPoints.get(1) - myLatLngPoints.get(3)) / 2))
                .fillColor(Color.argb(100, 255, 0, 0)));
    }

    /**
     * Given a center point and half the width + height create list of points for rectangle
     */
    private List<LatLng> createRectangle(LatLng center, double halfWidth, double halfHeight) {
        double greater;
        if (halfHeight > halfWidth)
            greater = halfHeight;
        else
            greater = halfHeight;
        return Arrays.asList(new LatLng(center.latitude - halfHeight, center.longitude - halfWidth),
                new LatLng(center.latitude - greater, center.longitude + greater),
                new LatLng(center.latitude + greater, center.longitude + greater),
                new LatLng(center.latitude + greater, center.longitude - greater),
                new LatLng(center.latitude - greater, center.longitude - greater));
    }
    @Override
    public void onMapClick(LatLng arg0) {
        // TODO Auto-generated method stub
        // map.animateCamera(CameraUpdateFactory.newLatLng(arg0));
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "InGameMaps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://edu.washington.chau93.hvz_app.activities/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "InGameMaps Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app URL is correct.
                Uri.parse("android-app://edu.washington.chau93.hvz_app.activities/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
