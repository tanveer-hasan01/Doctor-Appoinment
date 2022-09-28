package com.example.dochere.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dochere.R;
import com.example.dochere.adapter.AdapterCategory;
import com.example.dochere.databinding.FragmentHomeBinding;
import com.example.dochere.model.ModelCategory;

import java.util.ArrayList;


public class HomeFragment extends Fragment {

    ArrayList<ModelCategory> categories=new ArrayList<>();
    FragmentHomeBinding binding;

    AdapterCategory adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater,container,false);
        View view = binding.getRoot();


        categories.add(new ModelCategory(R.drawable.img6,"Cardiologist"));
        categories.add(new ModelCategory(R.drawable.image7,"Orthopaedic"));
        categories.add(new ModelCategory(R.drawable.img8,"Dentist"));

        adapter = new AdapterCategory(categories, getContext());
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        binding.recyclerView.setLayoutManager(horizontalLayoutManagaer);

        binding.recyclerView.setAdapter(adapter);


        return view;
    }
}