package com.example.dochere.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dochere.R;
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
        View view = LayoutInflater.from(context).inflate(R.layout.item_medicine, parent, false);
        return new AdapterMedicine.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterMedicine.Holder holder, int position) {

        holder.name.setText(medicines.get(position).getName());
        if (medicines.get(position).getMorning().equals("ok")){
            holder.morning.setChecked(true);
        }
        if (medicines.get(position).getDay().equals("ok")){
            holder.day.setChecked(true);
        }
        if (medicines.get(position).getNight().equals("ok")){
            holder.night.setChecked(true);
        }

    }

    @Override
    public int getItemCount() {
        return medicines.size();
    }


    public class Holder extends RecyclerView.ViewHolder {

        TextView name;
        CheckBox morning,day,night;

        public Holder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.medicine);
            morning=itemView.findViewById(R.id.morning);
            day=itemView.findViewById(R.id.day);
            night=itemView.findViewById(R.id.night);
        }
    }
}
