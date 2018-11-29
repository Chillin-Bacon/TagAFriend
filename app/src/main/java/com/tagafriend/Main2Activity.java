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

    private Button mapsActivityButton,
            optionsActivityButton,
            btnAddToDatabase,
            btnAddFriendtoDB,
            btngetCurrentFriends,
            btnTagFriend,
            btnUnTagFriend,
            btnAmITagged;

    public FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        mapsActivityButton = findViewById(R.id.displayMapsButton);
        optionsActivityButton = findViewById(R.id.optionsButton);
        btnAddToDatabase = findViewById(R.id.testButton);
        btnAddFriendtoDB = findViewById(R.id.addFriendButton);
        btngetCurrentFriends = findViewById(R.id.currentFriends);
        btnTagFriend = findViewById(R.id.tagFriendButton);
        btnUnTagFriend = findViewById(R.id.untagButton);
        btnAmITagged = findViewById(R.id.checkedIfTagged);
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

        btnAddToDatabase.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                // Intent intent=new Intent(getApplicationContext(),SignInActivity.class);
                // startActivity(intent);


                Intent intent = new Intent(getApplicationContext(),AddToDatabase.class);
                startActivity(intent);

            }
        });

        btnAddFriendtoDB.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                // Intent intent=new Intent(getApplicationContext(),SignInActivity.class);
                // startActivity(intent);


                Intent intent = new Intent(getApplicationContext(),AddFriendToDB.class);
                startActivity(intent);

            }
        });


        btngetCurrentFriends.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                // Intent intent=new Intent(getApplicationContext(),SignInActivity.class);
                // startActivity(intent);


                Intent intent = new Intent(getApplicationContext(),GetCurrentFriends.class);
                startActivity(intent);

            }
        });

        btnTagFriend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                // Intent intent=new Intent(getApplicationContext(),SignInActivity.class);
                // startActivity(intent);


                Intent intent = new Intent(getApplicationContext(),TagFriendActivity.class);
                startActivity(intent);

            }
        });

        btnUnTagFriend.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                // Intent intent=new Intent(getApplicationContext(),SignInActivity.class);
                // startActivity(intent);


                Intent intent = new Intent(getApplicationContext(),UnTagMeActivity.class);
                startActivity(intent);

            }
        });
        
    }

}
