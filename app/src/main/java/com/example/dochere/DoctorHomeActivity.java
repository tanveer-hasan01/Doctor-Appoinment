package com.example.dochere;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.dochere.databinding.ActivityDoctorHomeBinding;
import com.example.dochere.databinding.ActivityLoginBinding;
import com.example.dochere.network.ApiClient;
import com.example.dochere.network.ApiInterface;

import retrofit2.Retrofit;

public class DoctorHomeActivity extends AppCompatActivity {

    ActivityDoctorHomeBinding binding;
    ApiInterface apiInterface;
    MysharedPreferance mysharedPreferance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDoctorHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Patient Appointment");
        Retrofit instance = ApiClient.instance();
        apiInterface = instance.create(ApiInterface.class);
        mysharedPreferance = MysharedPreferance.getPreferences(this);



    }
}