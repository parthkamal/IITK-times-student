package com.parth.iitktimesstudent;

import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        getSupportActionBar().hide();
        firebaseAuth = FirebaseAuth.getInstance();
        SystemClock.sleep(3000);
        FirebaseUser firebaseUser = firebaseAuth.getCurrentUser();
        if(firebaseUser== null){
            Intent loginInTENT = new Intent(SplashActivity.this,RegisterActivity.class);
            startActivity(loginInTENT);
        }else{
            Intent mainIntent = new Intent(SplashActivity.this,MainActivity.class);
            startActivity(mainIntent);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();

    }
}