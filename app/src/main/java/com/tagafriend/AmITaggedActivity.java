package com.tagafriend;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
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
import java.util.Objects;
import java.util.Vector;

public class AmITaggedActivity extends AppCompatActivity {

    private static final String TAG = "UnTagMeActivity";
    private static final String TAG2 = "FriendsThatAreTagged";

    private Button checkedIfTaggedButton;

    boolean userTaggedButton;

    boolean isFriendInKey;

    private EditText mFriendToBeAdded;

    //add Firebase Database stuff
    private FirebaseDatabase mFirebaseDatabase;
    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private DatabaseReference myRef;

    private Button addNewFriendsToDB;

    private Button viewFriendsFromDB;

    EditText friendToBeTagged;

    final ArrayList<String> friendsList = new ArrayList<> ();
    final ArrayList<String> taggedFriendsList = new ArrayList<>();
    final Vector<Boolean> getTrueOrFalse = new Vector<>();
    String userId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_am_itagged);
        //declare variables in oncreate
        // untagFriendButton = (Button) findViewById(R.id.unTaggedButton);
        //     friendToBeTagged = (EditText) findViewById(R.id.taggedFriendCode);
        //
        //declare the database reference object. This is what we use to access the database.
        //NOTE: Unless you are signed in, this will not be useable.
        checkedIfTaggedButton = findViewById(R.id.checkedIfTaggedButton);
        mAuth = FirebaseAuth.getInstance();
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        myRef = mFirebaseDatabase.getReference();
        //
        //     final DatabaseReference tripsReference = myRef.child("friends");
        final DatabaseReference taggedReference = myRef.child("isTagged");
        //
        //     mFriendToBeAdded = (EditText) findViewById(R.id.addFriendEditText);
        //
        //     // String joinedFriendList;
        //
        //     // ArrayAdapter<String> friendsAdapter = new ArrayAdapter<String>(this, android.R.layout.get)
        //
        //
        //
        //     // myRef.setValue("Hello, World!");
        //
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
        //
        //
        FirebaseUser user = mAuth.getCurrentUser();
        userId = user.getUid();
        //
        taggedReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    String key = snapshot.getKey();
                    Boolean value = (Boolean) snapshot.getValue();
                    taggedFriendsList.add(key);
                    getTrueOrFalse.add(value);
                    Log.d(TAG, "HERE ARE THE DATA OF THE USERS TO BE UNTAGGED: " + key);
                    Log.d(TAG, "HERE ARE THE DATA OF THE USERS TO BE UNTAGGED: " + value);
                }

            }

            @Override
            public void onCancelled(DatabaseError error) {
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        //
        //     tripsReference.child(userId).child("userFriends").addValueEventListener(new ValueEventListener() {
        //         @Override
        //         public void onDataChange(DataSnapshot dataSnapshot) {
        //             // This method is called once with the initial value and again
        //             // whenever data at this location is updated.
        //             Object value = dataSnapshot.getValue();
        //
        //             // Log.d(TAG, "Value is: " + value);
        //             //
        //             // Log.d(TAG, "Value is: " + (dataSnapshot.getValue()).toString());
        //
        //             // String dataView = dataSnapshot.getValue().toString();
        //             //
        //             // dataSnapshot.getKey();
        //             //
        //             // String friendID = "12345";
        //             //
        //             // Log.d(TAG, "User friend elements are: " + dataSnapshot.getValue());
        //
        //             for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
        //                 String key = snapshot.getKey();
        //                 friendsList.add(key);
        //                 Log.d(TAG, "THE KEYS OF THE USER ARE: " + key);
        //             }
        //
        //         }
        //
        //
        //         @Override
        //         public void onCancelled(DatabaseError error) {
        //             // Failed to read value
        //             Log.w(TAG, "Failed to read value.", error.toException());
        //         }
        //     });
        //
        //
        checkedIfTaggedButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (userTaggedButton)
                    toastMessage("USER " + userId + " IS CURRENTLY TAGGED");
                else
                    toastMessage("USER " + userId + " IS NOT CURRENTLY TAGGED");

            }

        });
        //
    }
    //
    // @Override
    // public void onStart() {
    //     super.onStart();
    //     mAuth.addAuthStateListener(mAuthListener);
    // }
    //
    // @Override
    // public void onStop() {
    //     super.onStop();
    //     if (mAuthListener != null) {
    //         mAuth.removeAuthStateListener(mAuthListener);
    //     }
    // }
    //
    // //add a toast to show when successfully signed in
    // /**
    //  * customizable toast
    //  * @param message
    //  */
    private void toastMessage(String message){
        Toast.makeText(this,message,Toast.LENGTH_SHORT).show();
    }
    //
    // private void writeNewUser(String userId, String name, String email) {
    //     User user = new User(name, email);
    //     myRef.child("users").child(userId).setValue(user);
    // }
    // private void addNewFriend(String userId, String friendID) {
    //     // User user = new User(name, email);
    //     myRef.child("friends").child(userId).child("userFriends").child(friendID).setValue(true);
    // }
    private void unTagMe(String friendID) {
        // User user = new User(name, email);
        myRef.child("isTagged").child(friendID).setValue(false);
    }
    // private void userIsTagged(String friendID) {
    //     // User user = new User(name, email);
    //     myRef.child("isTagged");
    //     // child(userId).child("userFriends").child(friendID).setValue(true);
    // }

}
