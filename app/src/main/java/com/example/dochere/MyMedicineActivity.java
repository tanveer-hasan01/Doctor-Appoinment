package com.example.dochere;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.example.dochere.adapter.AdapterDoc;
import com.example.dochere.adapter.AdapterMedicine;
import com.example.dochere.databinding.ActivityMainBinding;
import com.example.dochere.databinding.ActivityMyMedicineBinding;
import com.example.dochere.model.ModelMedicine;
import com.example.dochere.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MyMedicineActivity extends AppCompatActivity {

    MysharedPreferance mysharedPreferance;
    ActivityMyMedicineBinding binding;
    ApiInterface apiInterface;

    ArrayList<ModelMedicine> medicines=new ArrayList();
    AdapterMedicine adapterMedicine;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMyMedicineBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("My Medicine");
        mysharedPreferance = MysharedPreferance.getPreferences(this);


        binding.recyclerView.setLayoutManager(new GridLayoutManager(this,2));
        adapterMedicine = new AdapterMedicine(medicines,this);


        ModelMedicine modelMedicine=new ModelMedicine();
        modelMedicine.setUserId(mysharedPreferance.getUserID());
        apiInterface.myMedicine(modelMedicine).enqueue(new Callback<List<ModelMedicine>>() {
            @Override
            public void onResponse(Call<List<ModelMedicine>> call, Response<List<ModelMedicine>> response) {

            }

            @Override
            public void onFailure(Call<List<ModelMedicine>> call, Throwable t) {

            }
        });

    }
}