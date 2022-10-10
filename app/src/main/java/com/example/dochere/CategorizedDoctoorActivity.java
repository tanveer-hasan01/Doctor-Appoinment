package com.example.dochere;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.dochere.databinding.ActivityCategorizedDoctoorBinding;
import com.example.dochere.databinding.ActivityEditProfileBinding;

public class CategorizedDoctoorActivity extends AppCompatActivity {

    ActivityCategorizedDoctoorBinding binding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCategorizedDoctoorBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle(getIntent().getStringExtra("category"));


    }
}