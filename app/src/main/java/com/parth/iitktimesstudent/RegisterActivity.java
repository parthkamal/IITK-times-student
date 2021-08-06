package com.parth.iitktimesstudent;

import android.os.Bundle;
import android.widget.FrameLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.parth.iitktimesstudent.Fragments.SignInFragment;

public class RegisterActivity extends AppCompatActivity {
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();
        frameLayout = findViewById(R.id.register_frameLayout);
        setFragment(new SignInFragment());
    }

    private void setFragment(Fragment fragment){
        getSupportFragmentManager().beginTransaction().replace(frameLayout.getId(),fragment).commit();
    }
}