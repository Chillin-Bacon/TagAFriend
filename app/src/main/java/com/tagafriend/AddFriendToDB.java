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


public class AddFriendToDB extends AppCompatActivity {

    private static final String TAG = "AddFriendToDB";

    private EditText mFriendToBeAdded;

    //add Firebase Database stuff
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    private Button addNewFriendsToDB;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_friend_to_db);
        //declare variables in oncreate
        addNewFriendsToDB = (Button) findViewById(R.id.addFriendButton);

        //declare the database reference object. This is what we use to access the database.
        //NOTE: Unless you are signed in, this will not be useable.
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();

        final DatabaseReference tripsReference = myRef.child("friends");

        mFriendToBeAdded = (EditText) findViewById(R.id.friendCodeET);



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
        String userID = user.getUid();


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