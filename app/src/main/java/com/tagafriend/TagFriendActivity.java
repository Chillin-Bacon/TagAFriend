package com.tagafriend;

import android.content.pm.PackageManager;
import android.location.Location;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class TagFriendActivity extends AppCompatActivity {

    private static final String TAG = "GetCurrentFriendsFromDB";
    private static final String TAG2 = "FriendsThatAreTagged";

    private Button tagFriendButton;

    private EditText mFriendToBeAdded;

    //add Firebase Database stuff
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    private Button addNewFriendsToDB;

    private Button viewFriendsFromDB;

    Button tagMyselfButton;

    EditText friendToBeTagged;

    final ArrayList<String> friendsList = new ArrayList<> ();
    final ArrayList<String> taggedFriendsList = new ArrayList<>();

    private FusedLocationProviderClient mFusedLocationClient;

    private static final int MY_PERMISSION_ACCESS_COARSE_LOCATION = 11;

    double userLat, userLong;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_friend);

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(this);


        if (ContextCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_COARSE_LOCATION}, MY_PERMISSION_ACCESS_COARSE_LOCATION);
        }

        mFusedLocationClient.getLastLocation()
                .addOnSuccessListener(this, new OnSuccessListener<Location>() {
                    @Override
                    public void onSuccess(Location location) {
                        // Got last known location. In some rare situations this can be null.
                        if (location != null) {
                            // Logic to handle location object
                            // TextView latitudeTV = findViewById(R.id.tvLatitude);
                            userLat = location.getLatitude();
                            // String latitudeString = Double.toString(latitude);
                            // latitudeTV.setText(latitudeString);

                            // TextView longitudeTV = findViewById(R.id.tvLongitude);
                            userLong = location.getLongitude();
                            // startActivity(newIntent);
                            Log.d("TagFriendsActivity", "The value of the longitude: " + userLat + userLong);
                        }
                    }
                });
        //declare variables in oncreate
        tagFriendButton = (Button) findViewById(R.id.tagFriendButtonInTagActivity);
        tagMyselfButton = (Button) findViewById(R.id.tagMyselfButton);
        friendToBeTagged = (EditText) findViewById(R.id.taggedFriendCode);

        //declare the database reference object. This is what we use to access the database.
        //NOTE: Unless you are signed in, this will not be useable.
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        final DatabaseReference tripsReference = myRef.child("friends");
        final DatabaseReference taggedReference = myRef.child("isTagged");

        mFriendToBeAdded = (EditText) findViewById(R.id.addFriendEditText);

        // String joinedFriendList;

        // ArrayAdapter<String> friendsAdapter = new ArrayAdapter<String>(this, android.R.layout.get)



        // myRef.setValue("Hello, World!");

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d(TAG, "onAuthStateChanged:signed_in:" + user.getUid());
                    toastMessage("Successfully signed in with: " + user.getEmail());

                } else {
                    // User is signed out
                    Log.d(TAG, "onAuthStateChanged:signed_out");
                    toastMessage("Successfully signed out.");
                }
                // ...
            }
        };


        FirebaseUser user = mAuth.getCurrentUser();
        String userId = user.getUid();

        taggedReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String key = snapshot.getKey();
                    taggedFriendsList.add(key);
                    Log.d(TAG2, "HERE ARE THE DATA OF THE TAGGED USERS: " + key);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        tripsReference.child(userId).child("userFriends").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Object value = dataSnapshot.getValue();

                // Log.d(TAG, "Value is: " + value);
                //
                // Log.d(TAG, "Value is: " + (dataSnapshot.getValue()).toString());

                // String dataView = dataSnapshot.getValue().toString();
                //
                // dataSnapshot.getKey();
                //
                // String friendID = "12345";
                //
                // Log.d(TAG, "User friend elements are: " + dataSnapshot.getValue());

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String key = snapshot.getKey();
                    friendsList.add(key);
                    Log.d(TAG, "THE KEYS OF THE USER ARE: " + key);
                }

            }


            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


        tagFriendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String friendID = friendToBeTagged.getText().toString();

                if (friendsList.contains(friendID) && !(taggedFriendsList.contains(friendID)))
                {
                    tagFriend(friendID, userLat, userLong);
                    toastMessage("SUCCESSFULLY TAGGED " + friendID);
                }
                else if (friendsList.contains(friendID) && taggedFriendsList.contains(friendID))
                {
                    toastMessage(friendID + " already tagged!");
                }
                else
                {
                    toastMessage(friendID + " not your friend!");
                }
            }

        });

        tagMyselfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                FirebaseUser user = mAuth.getCurrentUser();
                String userId = user.getUid();

                // String friendID = friendToBeTagged.getText().toString();

                // if (friendsList.contains(userId) && !(taggedFriendsList.contains(userId)))
                // {
                tagFriend(userId, userLat, userLong);
                toastMessage("SUCCESSFULLY TAGGED " + userId);
                    toastMessage("SUCCESSFULLY TAGGED " + userId);
                // }
                // else if (friendsList.contains(friendID) && taggedFriendsList.contains(friendID))
                // {
                //     toastMessage(userId + " already tagged!");
                // }
                // else
                // {
                //     toastMessage(userId + " not your friend!");
                // }
            }

        });

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    //add a toast to show when successfully signed in
    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }

    private void writeNewUser(String userId, String name, String email) {
        User user = new User(name, email);
        myRef.child("users").child(userId).setValue(user);
    }
    private void addNewFriend(String userId, String friendID) {
        // User user = new User(name, email);
        myRef.child("friends").child(userId).child("userFriends").child(friendID).setValue(true);
    }
    private void tagFriend(String friendID, double mUserLat, double mUserLong) {
        // User user = new User(name, email);
        Log.d("TagFriendsActivity", "We are inside of tagFriendFunction" + mUserLat + mUserLong);
        myRef.child("isTagged").child(friendID).setValue(true);
        myRef.child("userLat").child(friendID).setValue(mUserLat);
        myRef.child("userLong").child(friendID).setValue(mUserLong);
        // myRef.child("isTagged").child(friendID).setValue(mUserLat);
        // myRef.child("isTagged").child(friendID).setValue(mUserLong);
    }
    private void userIsTagged(String friendID) {
        // User user = new User(name, email);
        myRef.child("isTagged");
        // child(userId).child("userFriends").child(friendID).setValue(true);
    }

}
