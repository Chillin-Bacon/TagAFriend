package com.tagafriend;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DisplayMapActivity2 extends AppCompatActivity
implements OnMapReadyCallback {

    // Double userLatitude = getIntent().getExtras().getDouble("userLatitude");
    // Double userLongitude = getIntent().getExtras().getDouble("userLongitude");
    double userLat;
    double userLong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle b = getIntent().getExtras();
        userLat = b.getDouble("userLat");
        userLong = b.getDouble("userLong");
        setContentView(R.layout.activity_display_map2);
        Toast.makeText(this, "user lat is: " + userLat, Toast.LENGTH_LONG);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.

        Toast.makeText(this, "user lat is: " + userLat, Toast.LENGTH_LONG).show();
        LatLng sydney = new LatLng(userLat, userLong);

        // if (userLatitude != null && userLongitude != null)
        //     sydney = new LatLng(userLatitude, userLongitude);

        googleMap.addMarker(new MarkerOptions().position(sydney)
                .title("Marker in Sydney"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}
