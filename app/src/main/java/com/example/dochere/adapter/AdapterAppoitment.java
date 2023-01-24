package com.example.dochere.adapter;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dochere.R;
import com.example.dochere.RatingActivity;
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
    public void onBindViewHolder(@NonNull AdapterAppoitment.Holder holder, @SuppressLint("RecyclerView") int position) {

        holder.name.setText(appoitments.get(position).getName());
        holder.docname.setText(appoitments.get(position).getDocName());
        holder.age.setText("Age: "+appoitments.get(position).getAge());
        holder.blood.setText("Blood: "+appoitments.get(position).getBlood());
        holder.weight.setText("Weight: "+appoitments.get(position).getWeight());
        holder.status.setText(appoitments.get(position).getStatus());
        holder.comment.setText("Complain : "+appoitments.get(position).getComment());
        holder.date.setText("Date : "+appoitments.get(position).getDate());
        
        if (appoitments.get(position).getStatus().equals("approved")){
            holder.imageView.setImageResource(R.drawable.ic_ok);
            holder.makePayment.setVisibility(View.VISIBLE);
            holder.rating.setVisibility(View.VISIBLE);
            holder.show_prescription.setVisibility(View.VISIBLE);
        }else {
            holder.rating.setVisibility(View.GONE);
            holder.makePayment.setVisibility(View.GONE);
            holder.show_prescription.setVisibility(View.GONE);
        }


        holder.show_prescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                EditText prescription,food;
                Button submit;

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                final View mview = LayoutInflater.from(context).inflate(R.layout.write_prescription, null);
                builder.setView(mview);
                final AlertDialog dialog_condition = builder.create();
                // dialog_condition.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog_condition.show();

                prescription = mview.findViewById(R.id.et_prescription);
                food = mview.findViewById(R.id.food);
                submit = mview.findViewById(R.id.submit);
                submit.setVisibility(View.GONE);
                prescription.setEnabled(false);
                food.setEnabled(false);
                food.setText(appoitments.get(position).getFood());
                prescription.setText(appoitments.get(position).getPrescription());
            }
        });

        
        holder.makePayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Toast.makeText(context, "SSL Commerce account required for Payment Gateway", Toast.LENGTH_SHORT).show();
            }
        });

        holder.rating.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, RatingActivity.class);
                intent.putExtra("id",appoitments.get(position).getDocId());
                intent.putExtra("name",appoitments.get(position).getDocName());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return appoitments.size();
    }



    public class Holder extends RecyclerView.ViewHolder {
        TextView name,age,weight,blood,status,docname,comment,date,rating,show_prescription;
        Button makePayment;

        ImageView imageView;
        public Holder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            imageView=itemView.findViewById(R.id.status_img);
            age=itemView.findViewById(R.id.age);
            weight=itemView.findViewById(R.id.weight);
            blood=itemView.findViewById(R.id.blood);
            docname=itemView.findViewById(R.id.doctor);
            date=itemView.findViewById(R.id.date);
            status=itemView.findViewById(R.id.status);
            comment=itemView.findViewById(R.id.complain);
            rating=itemView.findViewById(R.id.rating);
            makePayment=itemView.findViewById(R.id.make_payment);
            show_prescription=itemView.findViewById(R.id.show_prescription);

        }
    }
}
