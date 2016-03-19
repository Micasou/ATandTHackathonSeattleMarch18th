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
    private String myDisplayName;
    private List<Double> myLocation;

    public User() {}

    public User(String myDisplayName, List<Double> myLocation, List<Game> myGames) {
        this(null, myDisplayName, myLocation, myGames);
    }

    public User(String myUID, String myDisplayName, List<Double> myLocation, List<Game> myGames) {
        this.myUID = myUID;
        this.myDisplayName = myDisplayName;
        this.myLocation = myLocation;
    }

    public String getMyUID() {
        return myUID;
    }

    public String getMyDisplayName() {
        return myDisplayName;
    }

    public List<Double> getMyLocation() {
        return myLocation;
    }

    @Override
    public String toString() {
        return "User{" +
                "myUID='" + myUID + '\'' +
                ", myDisplayName='" + myDisplayName + '\'' +
                ", myLocation=" + myLocation +
                '}';
    }
}
