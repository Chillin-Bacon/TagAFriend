package com.tagafriend;

import com.google.firebase.database.IgnoreExtraProperties;
import java.util.Vector;

@IgnoreExtraProperties
public class User {

    public String username;
    public String email;
    public long time;
    public Vector<String> userFriends;

    public User() {
        // Default constructor required for calls to DataSnapshot.getValue(User.class)
    }

    public User(String username, String email) {
        this.username = username;
        this.email = email;
        this.time = System.currentTimeMillis();
    }

    public void addFriend(String friendCode)
    {
        userFriends.add(friendCode);
    }

}
