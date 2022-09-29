package com.example.dochere.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dochere.R;
import com.example.dochere.model.ModelDoc;

import java.util.ArrayList;

public class AdapterDoc extends RecyclerView.Adapter<AdapterDoc.Holder> {

    ArrayList<ModelDoc> docs;
    Context context;

    public AdapterDoc(ArrayList<ModelDoc> docs, Context context) {
        this.docs = docs;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterDoc.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_category, parent, false);
        return new AdapterDoc.Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDoc.Holder holder, int position) {

        if (docs.get(position).getGender().toString().toLowerCase().equals("male")) {

            holder.imageView.setImageResource(R.drawable.doctor);
        } else {
            holder.imageView.setImageResource(R.drawable.doctor_frmale);
        }
        holder.name.setText(docs.get(position).getName());
        holder.category.setText(docs.get(position).getCategory());
        holder.visit.setText(docs.get(position).getVisit());
        holder.rating.setText(docs.get(position).getRating());


    }

    @Override
    public int getItemCount() {
        return docs.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name, category, visit, rating;

        public Holder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.image);
            name = itemView.findViewById(R.id.name);
            visit = itemView.findViewById(R.id.price);
            rating = itemView.findViewById(R.id.rating);
            category = itemView.findViewById(R.id.category);
        }
    }
}
