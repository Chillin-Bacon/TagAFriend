package com.tagafriend;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainActivity extends AppCompatActivity {

    private Button mSignInButton;
    private Button mSignUpButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mSignInButton = findViewById(R.id.button_sign_in);
        mSignUpButton = findViewById(R.id.button_sign_up);
        mSignInButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                // Intent intent=new Intent(getApplicationContext(),SignInActivity.class);
                // startActivity(intent);
                Intent intent=new Intent(getApplicationContext(),SignInActivity.class);
                startActivity(intent);
            }
        });
        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                // Code here executes on main thread after user presses button
                Intent intent=new Intent(getApplicationContext(),SignUp.class);
                startActivity(intent);
            }
        });
    }
    // public void Signin(View view)
    // {
    //     Intent intent=new Intent(getApplicationContext(),SignInActivity.class);
    //     startActivity(intent);
    //
    // }

    // public void Signup(View view)
    // {
    //     Intent intent=new Intent(getApplicationContext(),SignUp.class);
    //     startActivity(intent);
    //
    // }
}
