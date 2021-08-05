package com.parth.iitktimesstudent;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.parth.iitktimesstudent.Fragments.BookFragment;
import com.parth.iitktimesstudent.Fragments.DeveloperFragment;
import com.parth.iitktimesstudent.Fragments.EventFragment;
import com.parth.iitktimesstudent.Fragments.NoticeFragment;

public class MainActivity extends AppCompatActivity {
    private BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bottomNavigationView = findViewById(R.id.bottomNavigationView);

        //replacing the frame layout with the notice fragment by default
        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new NoticeFragment()).commit();

        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull @org.jetbrains.annotations.NotNull MenuItem item) {
                Fragment SelectedFragment = null;
                switch (item.getItemId()){
                    case R.id.notice_item:
                        SelectedFragment = new NoticeFragment();
                        break;
                    case R.id.event_item:
                        SelectedFragment = new EventFragment();
                        break;
                    case R.id.book_item:
                        SelectedFragment = new BookFragment();
                        break;
                    case R.id.developer_item:
                        SelectedFragment = new DeveloperFragment();
                        break;
                }
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,SelectedFragment).commit();
                return true;
            }
        });

    }

}