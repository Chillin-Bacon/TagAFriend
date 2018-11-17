package com.tagafriend;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import static android.Manifest.permission.ACCESS_FINE_LOCATION;

public class Main2Activity extends AppCompatActivity {

    private Button mapsActivityButton, optionsActivityButton;

    public FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mapsActivityButton = findViewById(R.id.displayMapsButton);
        optionsActivityButton = findViewById(R.id.optionsButton);
        client = LocationServices.getFusedLocationProviderClient(this);
        mapsActivityButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                // Intent intent=new Intent(getApplicationContext(),SignInActivity.class);
                // startActivity(intent);


                Intent intent=new Intent(getApplicationContext(),DisplayMapActivity.class);
                startActivity(intent);

            }
        });

        optionsActivityButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                // Intent intent=new Intent(getApplicationContext(),SignInActivity.class);
                // startActivity(intent);


                Intent intent = new Intent(getApplicationContext(),OptionsActivity.class);
                startActivity(intent);

            }
        });
    }

}
