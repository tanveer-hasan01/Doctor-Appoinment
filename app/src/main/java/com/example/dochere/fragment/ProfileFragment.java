package com.example.dochere.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dochere.MysharedPreferance;
import com.example.dochere.R;
import com.example.dochere.databinding.FragmentHomeBinding;
import com.example.dochere.databinding.FragmentProfileBinding;


public class ProfileFragment extends Fragment {


    MysharedPreferance mysharedPreferance;
    FragmentProfileBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentProfileBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        mysharedPreferance=MysharedPreferance.getPreferences(getContext());

        binding.name.setText(mysharedPreferance.getName());
        binding.email.setText(mysharedPreferance.getemail());
        binding.phone.setText(mysharedPreferance.getPhone());



        return view;
    }
}