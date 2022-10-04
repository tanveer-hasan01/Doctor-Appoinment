package com.example.dochere.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dochere.R;
import com.example.dochere.model.ModelAppoitment;

import java.util.ArrayList;

public class AdapterAppoitment extends RecyclerView.Adapter<AdapterAppoitment.Holder> {

    ArrayList<ModelAppoitment>appoitments;
    Context context;

    public AdapterAppoitment(ArrayList<ModelAppoitment> appoitments, Context context) {
        this.appoitments = appoitments;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterAppoitment.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_appointment, parent, false);
        return new AdapterAppoitment.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterAppoitment.Holder holder, int position) {

        holder.name.setText(appoitments.get(position).getName());
        holder.docname.setText(appoitments.get(position).getDocName());
        holder.age.setText(appoitments.get(position).getAge());
        holder.blood.setText(appoitments.get(position).getBlood());
        holder.weight.setText(appoitments.get(position).getWeight());
        holder.status.setText(appoitments.get(position).getStatus());

    }

    @Override
    public int getItemCount() {
        return 0;
    }



    public class Holder extends RecyclerView.ViewHolder {
        TextView name,age,weight,blood,status,docname;

        public Holder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            age=itemView.findViewById(R.id.age);
            weight=itemView.findViewById(R.id.weight);
            blood=itemView.findViewById(R.id.blood);
            docname=itemView.findViewById(R.id.doctor);
            status=itemView.findViewById(R.id.status);

        }
    }
}
