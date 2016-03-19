package edu.washington.chau93.hvz_app.activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import edu.washington.chau93.hvz_app.R;
import edu.washington.chau93.hvz_app.models.Game;

public class InGameMapsActivity extends FragmentActivity implements OnMapReadyCallback
       {
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
   // private Timer myTimer;
    List<HumanPings> humanPings;

    /**
     * Init all variables for the map. Constructor
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.in_game_activity_maps);
        if(humanPings== null)
            humanPings = new ArrayList<HumanPings>();

        myGame = MainActivity.getMyGameMap().get("-KDG8ojSYUYEWBGSO1hM");
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
     /*   myTimer = new Timer();
        myTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                updatePings();
            }
        }, 0, 10000);*/
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }
    private void updatePings() {
        if(!humanPings.isEmpty()) {
            for (HumanPings i : humanPings) {
                i.decay();
            }
        }
    }
    @Override
    public void onMapReady(GoogleMap googleMap) {
        myMap = googleMap;
        if(myGame != null) {
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
                myMap.setOnMapClickListener(new GoogleMap.OnMapClickListener(){

                    @Override
                    public void onMapClick(LatLng point) {
                        humanPings.add(new HumanPings(point));
                    }
                });
                this.drawPolygon();
                myMap.moveCamera(CameraUpdateFactory.newLatLngZoom(myPosition, 19));
            } else {
                //location wasn't retrieved, must do stuff
                //TODO IMPLEMENT ALTERNATIVE
            }
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
//    @Override
//    public void onMapClick(LatLng arg0) {
//        humanPings.add(new HumanPings(arg0));
//    }
    private class HumanPings {
        private double radius;
        private CircleOptions myCircleOptions;
        private int myOpcaity;
        private LatLng myCenter;
        private Circle myCirlce;
        public HumanPings(LatLng center){
            myOpcaity = 255;
            radius = 10; //measured in meters
            myCenter = center;
            myCircleOptions = new CircleOptions().center(myCenter).radius(radius).fillColor(Color.argb(myOpcaity,255,0,0));
            myCirlce = myMap.addCircle(myCircleOptions);
        }
        private void decay() {
            myOpcaity -= 255*.1;
            radius += 2;
            if(myOpcaity < 50) {
                myCirlce.remove();
                return;
            }

            myCirlce.setRadius(radius);
            myCirlce.setFillColor(Color.argb(myOpcaity,255,0,0));
        }
    }
}
