package com.example.dochere;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.dochere.databinding.ActivityAppMoodBinding;
import com.example.dochere.databinding.ActivityMainBinding;
import com.example.dochere.fragment.AppointmentFragment;
import com.example.dochere.fragment.HomeFragment;
import com.example.dochere.fragment.ProfileFragment;
import com.example.dochere.fragment.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {


    MysharedPreferance mysharedPreferance;
    ActivityMainBinding binding;
    HomeFragment homeFragment = new HomeFragment();
    ProfileFragment profileFragment = new ProfileFragment();
    AppointmentFragment appointmentFragment = new AppointmentFragment();
    SearchFragment searchFragment = new SearchFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        mysharedPreferance = MysharedPreferance.getPreferences(this);

        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
        binding.bottomNev.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment).commit();
                        return true;
                    case R.id.appoinment:
                        if (mysharedPreferance.getSession().equals("none")) {
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        } else {
                            getSupportFragmentManager().beginTransaction().replace(R.id.container, appointmentFragment).commit();
                        }
                        return true;
                    case R.id.search:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container, searchFragment).commit();
                        return true;
                        case R.id.profile:
                        if (mysharedPreferance.getSession().equals("none")) {
                            startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        } else {
                            getSupportFragmentManager().beginTransaction().replace(R.id.container, profileFragment).commit();
                        }
                            return true;
                }

                return false;
            }
        });

    }
}