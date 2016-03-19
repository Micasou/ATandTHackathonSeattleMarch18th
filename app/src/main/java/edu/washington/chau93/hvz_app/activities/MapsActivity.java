package edu.washington.chau93.hvz_app;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.google.android.gms.location.LocationRequest;
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

import java.util.Arrays;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback,
        GoogleMap.OnMapClickListener,
        GoogleMap.OnMarkerDragListener {
    /**Google map object */
    private GoogleMap myMap;
    /**Boolean to determine if user is creating the game*/
    private boolean myCreatingGame;
    /** upper left hand bound of the user decided map*/
    private Marker myUpperLeftBound;
    /** lower right hand bound of the user decided map*/
    private Marker myLowerRightBound;
    /** users current location*/
    private  LatLng myPosition;
    /** Keeps track of the users location settings */
    private LocationManager myLocationManager;
    /** Creating a criteria object to retrieve provider*/
    Criteria myCriteria;
    /** Polygon shape that resizes with the inner+outter bounds */
    Polygon myBounds;

    /**Init all variables for the map. Constructor*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_maps2);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        myMap = googleMap;
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
                double offset = .0003; //Used to offset the location for the initial markers
                LatLng tempMarker = new LatLng(myPosition.latitude+offset, myPosition.longitude+offset);
                myUpperLeftBound = myMap.addMarker(new MarkerOptions()
                        .position(tempMarker)
                        .draggable(true)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                tempMarker = new LatLng(myPosition.latitude-offset, myPosition.longitude-offset);
                myLowerRightBound = myMap.addMarker(new MarkerOptions()
                        .position(tempMarker)
                        .draggable(true)
                        .icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED)));
                this.drawPolygon();
                myMap.setOnMarkerDragListener(this);
                myMap.moveCamera(CameraUpdateFactory.newLatLng(myPosition));
            } else {
                //location wasn't retrieved, must do stuff
                //TODO IMPLEMENT ALTERNATIVE
            }
            // Add a marker in Sydney a
        }
    }
    /** Creates the polygon that outlines the playable map area */
    private void drawPolygon() {
        LatLng tempLatLng = new LatLng((myLowerRightBound.getPosition().latitude+myUpperLeftBound.getPosition().latitude)/2,
                (myLowerRightBound.getPosition().longitude+myUpperLeftBound.getPosition().longitude)/2
        );
        if(myBounds != null)
            myBounds.remove();
        myBounds = myMap.addPolygon(new PolygonOptions()
                .addAll(createRectangle(tempLatLng,
                        (myLowerRightBound.getPosition().latitude-myUpperLeftBound.getPosition().latitude)*2,
                        (myLowerRightBound.getPosition().longitude-myUpperLeftBound.getPosition().longitude)*2))
                .addHole(createRectangle(tempLatLng,
                        (myLowerRightBound.getPosition().latitude-myUpperLeftBound.getPosition().latitude)/2,
                        (myLowerRightBound.getPosition().longitude-myUpperLeftBound.getPosition().longitude)/2))
                .fillColor(Color.RED));
    }
    /** Given a center point and half the width + height create list of points for rectangle */
    private List<LatLng> createRectangle(LatLng center, double halfWidth, double halfHeight) {
        return Arrays.asList(new LatLng(center.latitude - halfHeight, center.longitude - halfWidth),
                new LatLng(center.latitude - halfHeight, center.longitude + halfWidth),
                new LatLng(center.latitude + halfHeight, center.longitude + halfWidth),
                new LatLng(center.latitude + halfHeight, center.longitude - halfWidth),
                new LatLng(center.latitude - halfHeight, center.longitude - halfWidth));
    }
    @Override
    public void onMarkerDrag(Marker theMarker) {
    }
    /**Event to handle resizing the playable map area*/
    @Override
    public void onMarkerDragEnd(Marker theMarker) {
        theMarker.setIcon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        theMarker.setFlat(true);
        this.drawPolygon();
    }

    @Override
    public void onMarkerDragStart(Marker theMarker) {
    }

    @Override
    public void onMapClick(LatLng arg0) {
        // TODO Auto-generated method stub
       // map.animateCamera(CameraUpdateFactory.newLatLng(arg0));
    }


}
