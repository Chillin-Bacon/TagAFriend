package com.tagafriend;

import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.gms.location.LocationListener;
import android.Manifest;

import android.app.AlertDialog.Builder;
import android.app.AlertDialog;
import android.content.DialogInterface;


import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class DisplayMapActivity extends FragmentActivity implements OnMapReadyCallback {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_map);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    // public static final int MY_PERMISSIONS_REQUEST_LOCATION = 99;
    //
    // LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
    //
    // private GoogleMap mMap;
    //
    // Criteria criteria = new Criteria();
    //
    // String provider = locationManager.getBestProvider(criteria, false);
    //
    // @Override
    // protected void onCreate(Bundle savedInstanceState) {
    //     super.onCreate(savedInstanceState);
    //     setContentView(R.layout.activity_display_map);
    //
    //     // Obtain the SupportMapFragment and get notified when the map is ready to be used.
    //     SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
    //             .findFragmentById(R.id.map);
    //     mapFragment.getMapAsync(this);
    // }
    //
    // /**
    //  * Manipulates the map once available.
    //  * This callback is triggered when the map is ready to be used.
    //  * This is where we can add markers or lines, add listeners or move the camera. In this case,
    //  * we just add a marker near Sydney, Australia.
    //  * If Google Play services is not installed on the device, the user will be prompted to install
    //  * it inside the SupportMapFragment. This method will only be triggered once the user has
    //  * installed Google Play services and returned to the app.
    //  */
    // @Override
    public void onMapReady(GoogleMap googleMap) {
      //   mMap = googleMap;
      //
      //   Criteria criteria = new Criteria();
      //
      //   checkLocationPermission();
      //
      //   Location location = locationManager.getLastKnownLocation(locationManager.getBestProvider(criteria, false));
      //
      //   double lat = location.getLatitude();
      //   double lng = location.getLongitude();
      //
      //   // Add a marker in Sydney and move the camera
      //   LatLng sydney = new LatLng(lat,lng);
      //   MarkerOptions marker = new MarkerOptions().position(sydney).title("Anuradha Rajashekar");
      //   marker.icon(BitmapDescriptorFactory.fromResource(R.drawable.userplaceholder));
      //   mMap.addMarker(marker);
      //   // mMap.addMarker(new MarkerOptions().position(sydney).title("Anuradha's location"));
      //   mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
      //   mMap.animateCamera(CameraUpdateFactory.zoomTo(17.0f));
      //
      // /*  LatLng location2 = new LatLng(37.402276,-121.942161);
      //   mMap.addMarker(new MarkerOptions().position(location2).title("Anuradha's new location"));
      //  mMap.moveCamera(CameraUpdateFactory.newLatLng(location2));*/
      //
      //   //zoom into a particular position
      //   CameraUpdate zoom = CameraUpdateFactory.zoomTo(12);
      //   mMap.moveCamera(zoom);
      //   mMap.animateCamera(zoom);
    }
    //
    // public boolean checkLocationPermission() {
    //     if (ContextCompat.checkSelfPermission(this,
    //             Manifest.permission.ACCESS_FINE_LOCATION)
    //             != PackageManager.PERMISSION_GRANTED) {
    //
    //         // Should we show an explanation?
    //         if (ActivityCompat.shouldShowRequestPermissionRationale(this,
    //                 Manifest.permission.ACCESS_FINE_LOCATION)) {
    //
    //             // Show an explanation to the user *asynchronously* -- don't block
    //             // this thread waiting for the user's response! After the user
    //             // sees the explanation, try again to request the permission.
    //             new AlertDialog.Builder(this)
    //                     .setTitle(R.string.title_location_permission)
    //                     .setMessage(R.string.text_location_permission)
    //                     .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
    //                         @Override
    //                         public void onClick(DialogInterface dialogInterface, int i) {
    //                             //Prompt the user once explanation has been shown
    //                             ActivityCompat.requestPermissions(DisplayMapActivity.this,
    //                                     new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
    //                                     MY_PERMISSIONS_REQUEST_LOCATION);
    //                         }
    //                     })
    //                     .create()
    //                     .show();
    //
    //
    //         } else {
    //             // No explanation needed, we can request the permission.
    //             ActivityCompat.requestPermissions(this,
    //                     new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
    //                     MY_PERMISSIONS_REQUEST_LOCATION);
    //         }
    //         return false;
    //     } else {
    //         return true;
    //     }
    // }
    //
    // @Override
    // public void onRequestPermissionsResult(int requestCode,
    //                                        String permissions[], int[] grantResults) {
    //     switch (requestCode) {
    //         case MY_PERMISSIONS_REQUEST_LOCATION: {
    //             // If request is cancelled, the result arrays are empty.
    //             if (grantResults.length > 0
    //                     && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
    //
    //                 // permission was granted, yay! Do the
    //                 // location-related task you need to do.
    //                 if (ContextCompat.checkSelfPermission(this,
    //                         Manifest.permission.ACCESS_FINE_LOCATION)
    //                         == PackageManager.PERMISSION_GRANTED) {
    //
    //                     //Request location updates:
    //                 }
    //
    //             } else {
    //
    //                 // permission denied, boo! Disable the
    //                 // functionality that depends on this permission.
    //
    //             }
    //             return;
    //         }
    //
    //     }
    // }
}
