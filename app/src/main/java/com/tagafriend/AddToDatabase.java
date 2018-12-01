package com.tagafriend;

import android.os.Bundle;
import android.provider.SyncStateContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;


public class AddToDatabase extends AppCompatActivity {

    private static final String TAG = "AddToDatabase";

    private Button mAddToDB;

    private EditText mNewFood, mFriendToBeAdded;

    //add Firebase Database stuff
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    private Button addNewFriendsToDB;

    private Button viewFriendsFromDB;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_to_database_layout);
        //declare variables in oncreate
        mAddToDB = (Button) findViewById(R.id.btnAddNewFood);
        addNewFriendsToDB = (Button) findViewById(R.id.addFriendToDB);
        viewFriendsFromDB = (Button) findViewById(R.id.viewFriendsButton);

        //declare the database reference object. This is what we use to access the database.
        //NOTE: Unless you are signed in, this will not be useable.
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        final DatabaseReference tripsReference = myRef.child("friends");

        mFriendToBeAdded = (EditText) findViewById(R.id.addFriendEditText);



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

        final ArrayList<String> friendsList = new ArrayList<> ();

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Object value = dataSnapshot.getValue();

                Log.d(TAG, "Value is: " + value);

            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        FirebaseUser user = mAuth.getCurrentUser();
        String userId = user.getUid();

        tripsReference.child(userId).child("userFriends").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                Object value = dataSnapshot.getValue();

                Log.d(TAG, "Value is: " + value);

                Log.d(TAG, "Value is: " + (dataSnapshot.getValue()).toString());

                String dataView = dataSnapshot.getValue().toString();

                dataSnapshot.getKey();

                String friendID = "12345";

                Log.d(TAG, "User friend elements are: " + dataSnapshot.getValue());

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String key = snapshot.getKey();
                    Log.d(TAG, "THE KEYS OF THE USER ARE: " + key);
                }

                // for (int i = 0; i < list.size(); ++i) {
                //     Log.d(TAG, "String contains ID is: " + list.get(i));
                // }

                // FirebaseUser user = mAuth.getCurrentUser();
                // String userId = user.getUid();
                //
                // List<String> list = new ArrayList<>();
                // for(DataSnapshot ds : dataSnapshot.getChildren()) {
                //     boolean state = tripsReference.child("friends").child(userId).child("userFriends").child("12345").;
                //     Toast.makeText(getApplicationContext(), "The value of arrival is" + arrival, Toast.LENGTH_SHORT).show();
                // }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        mAddToDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log.d(TAG, "onClick: Attempting to add object to database.");
                // String newFood = mNewFood.getText().toString();
                // if(!newFood.equals("")){
                    FirebaseUser user = mAuth.getCurrentUser();
                    String userID = user.getUid();
                    toastMessage("User ID IS " + userID);
                    writeNewUser(userID, user.getDisplayName(), user.getEmail());
                //     myRef.child(userID).child("Food").child(newFood).setValue("true");
                //     // toastMessage("Adding " + newFood + " to database...");
                //     //reset the text
                //     mNewFood.setText("");
                }

        });

        addNewFriendsToDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log.d(TAG, "onClick: Attempting to add object to database.");
                // String newFood = mNewFood.getText().toString();
                // if(!newFood.equals("")){
                // FirebaseUser user = mAuth.getCurrentUser();
                // String userID = user.getUid();
                FirebaseUser user = mAuth.getCurrentUser();
                String userID = user.getUid();

                toastMessage("NEW FRIEND ID IS" + mFriendToBeAdded.getText().toString());
                addNewFriend(userID, mFriendToBeAdded.getText().toString());
                // writeNewUser(userID, user.getDisplayName(), user.getEmail());
                //     myRef.child(userID).child("Food").child(newFood).setValue("true");
                //     // toastMessage("Adding " + newFood + " to database...");
                //     //reset the text
                //     mNewFood.setText("");
            }

        });

        viewFriendsFromDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Log.d(TAG, "onClick: Attempting to add object to database.");
                // String newFood = mNewFood.getText().toString();
                // if(!newFood.equals("")){
                // FirebaseUser user = mAuth.getCurrentUser();
                // String userID = user.getUid();
                // FirebaseUser user = mAuth.getCurrentUser();
                //
                // myRef.child("friends");
                // List<String> cities = new ArrayList<String>();
                //
                // for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                //     cities.add(postSnapshot.getValue().toString());
                // }
                // System.out.println(cities);

                // String userID = user.getUid();

                // toastMessage("NEW FRIEND ID IS" + mFriendToBeAdded.getText().toString());
                // addNewFriend(userID, mFriendToBeAdded.getText().toString());
                // writeNewUser(userID, user.getDisplayName(), user.getEmail());
                //     myRef.child(userID).child("Food").child(newFood).setValue("true");
                //     // toastMessage("Adding " + newFood + " to database...");
                //     //reset the text
                //     mNewFood.setText("");
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

}
