package com.example.dochere.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dochere.R;
import com.example.dochere.model.ModelContact;

import java.util.ArrayList;

public class AdapterContact extends RecyclerView.Adapter<AdapterContact.Holder> {

    ArrayList<ModelContact> contacts;
    Context context;

    public AdapterContact(ArrayList<ModelContact> contacts, Context context) {
        this.contacts = contacts;
        this.context = context;
    }

    @NonNull
    @Override
    public AdapterContact.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_contact, parent, false);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterContact.Holder holder, int position) {

        holder.number.setText(contacts.get(position).getNumber());
        holder.text.setText(contacts.get(position).getText());

    }

    @Override
    public int getItemCount() {
        return contacts.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        TextView text,number;

        public Holder(@NonNull View itemView) {
            super(itemView);
            text=itemView.findViewById(R.id.tt_text);
            number=itemView.findViewById(R.id.number);
        }
    }
}
