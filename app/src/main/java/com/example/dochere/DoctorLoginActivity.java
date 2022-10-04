package com.example.dochere;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;

import com.example.dochere.databinding.ActivityDoctorDatilesBinding;
import com.example.dochere.databinding.ActivityDoctorLoginBinding;
import com.example.dochere.network.ApiClient;
import com.example.dochere.network.ApiInterface;

import retrofit2.Retrofit;

public class DoctorLoginActivity extends AppCompatActivity {

    ApiInterface apiInterface;
    MysharedPreferance mysharedPreferance;
    ActivityDoctorLoginBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDoctorLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Doctor Login");

        Retrofit instance = ApiClient.instance();
        apiInterface = instance.create(ApiInterface.class);
        mysharedPreferance = MysharedPreferance.getPreferences(this);


        binding.login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!binding.email.getText().toString().isEmpty()|| !binding.password.getText().toString().isEmpty()){


                }
            }
        });

    }
}