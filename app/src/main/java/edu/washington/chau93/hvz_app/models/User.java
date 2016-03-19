package edu.washington.chau93.hvz_app.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * The user class. Holds information about a single user.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class User {
    private String myUID;
    private LatLng myLocation;
    private List<Game> myGames;

    public String getMyUID() {
        return myUID;
    }

    public List<Game> getMyGames() {
        return myGames;
    }

    public LatLng getMyLocation() {
        return myLocation;
    }
}
