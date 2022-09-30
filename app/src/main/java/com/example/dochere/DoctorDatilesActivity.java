package com.example.dochere;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.dochere.databinding.ActivityDoctorDatilesBinding;

public class DoctorDatilesActivity extends AppCompatActivity {

    ActivityDoctorDatilesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDoctorDatilesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.name.setText(getIntent().getStringExtra("name"));
        binding.category.setText(getIntent().getStringExtra("category"));
        binding.price.setText(getIntent().getStringExtra("visit"));
        binding.time.setText(getIntent().getStringExtra("time"));
        binding.rating.setText(getIntent().getStringExtra("rating"));
        binding.degree.setText(getIntent().getStringExtra("degree"));
        getIntent().getStringExtra("id");

        binding.apointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(DoctorDatilesActivity.this, "Comming soon...", Toast.LENGTH_SHORT).show();
            }
        });

    }
}