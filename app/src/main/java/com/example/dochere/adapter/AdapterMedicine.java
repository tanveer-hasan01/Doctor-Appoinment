package com.example.dochere.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dochere.model.ModelMedicine;

import java.util.ArrayList;

public class AdapterMedicine extends RecyclerView.Adapter<AdapterMedicine.Holder> {


    ArrayList<ModelMedicine>medicines;
    Context context;

    public AdapterMedicine(ArrayList<ModelMedicine> medicines, Context context) {
        this.medicines = medicines;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterMedicine.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMedicine.Holder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class Holder extends RecyclerView.ViewHolder {
        public Holder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
