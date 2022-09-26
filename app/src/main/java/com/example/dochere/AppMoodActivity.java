package com.example.dochere;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.dochere.databinding.ActivityAppMoodBinding;

public class AppMoodActivity extends AppCompatActivity {

    ActivityAppMoodBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAppMoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.patient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AppMoodActivity.this, MainActivity.class));
                finish();
            }
        });

        binding.doctor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AppMoodActivity.this, DoctorLoginActivity.class));
                finish();
            }
        });
    }
}