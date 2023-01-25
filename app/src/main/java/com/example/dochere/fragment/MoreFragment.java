package com.example.dochere.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.dochere.R;
import com.example.dochere.adapter.AdapterCategory;
import com.example.dochere.adapter.AdapterContact;
import com.example.dochere.databinding.FragmentHomeBinding;
import com.example.dochere.databinding.FragmentMoreBinding;
import com.example.dochere.model.ModelContact;
import com.example.dochere.network.ApiClient;
import com.example.dochere.network.ApiInterface;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MoreFragment extends Fragment {
    ApiInterface apiInterface;
    FragmentMoreBinding binding;
    ArrayList<ModelContact>contacts=new ArrayList<>();
    AdapterContact adapterContact;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentMoreBinding.inflate(inflater,container,false);
        View view = binding.getRoot();
        Retrofit instance = ApiClient.instance();
        apiInterface = instance.create(ApiInterface.class);

        adapterContact = new AdapterContact(contacts, getContext());
        LinearLayoutManager horizontalLayoutManagaer = new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
        binding.recyclerView.setLayoutManager(horizontalLayoutManagaer);
        binding.recyclerView.setAdapter(adapterContact);



        apiInterface.getContacts().enqueue(new Callback<List<ModelContact>>() {
            @Override
            public void onResponse(Call<List<ModelContact>> call, Response<List<ModelContact>> response) {

                contacts.addAll(response.body());
                binding.recyclerView.setAdapter(adapterContact);
                adapterContact.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<ModelContact>> call, Throwable t) {

                Toast.makeText(getContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });




        return view;
    }
}