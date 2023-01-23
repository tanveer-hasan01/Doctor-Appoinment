package com.example.dochere;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.dochere.databinding.ActivityDoctorDatilesBinding;

public class DoctorDatilesActivity extends AppCompatActivity {

    public static final int request_call = 1;
    ActivityDoctorDatilesBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDoctorDatilesBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("About Doctor");

        if (getIntent().getStringExtra("gender").equals("male")){
            binding.avater.setImageResource(R.drawable.doctor);
        }else {
            binding.avater.setImageResource(R.drawable.doctor_frmale);
        }
        binding.name.setText(getIntent().getStringExtra("name"));
        binding.category.setText(getIntent().getStringExtra("category"));
        binding.price.setText(getIntent().getStringExtra("visit"));
        binding.time.setText(getIntent().getStringExtra("time"));
        binding.rating.setText(getIntent().getStringExtra("rating"));
        binding.degree.setText(getIntent().getStringExtra("degree"));
        binding.email.setText(getIntent().getStringExtra("email"));
        binding.phone.setText(getIntent().getStringExtra("num"));
        getIntent().getStringExtra("id");

        binding.phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                call(binding.phone.getText().toString());
            }
        });

        binding.email.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mail(binding.email.getText().toString());
            }
        });

        binding.apointment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(DoctorDatilesActivity.this, AppoinmentActivity.class);
                intent.putExtra("name",getIntent().getStringExtra("name"));
                intent.putExtra("category",getIntent().getStringExtra("category"));
                intent.putExtra("visit",getIntent().getStringExtra("visit"));
                intent.putExtra("rating",getIntent().getStringExtra("rating"));
                intent.putExtra("id",getIntent().getStringExtra("id"));
                intent.putExtra("gender",getIntent().getStringExtra("gender"));
                startActivity(intent);
            }
        });

    }

    void call(String number) {

        if (ContextCompat.checkSelfPermission(DoctorDatilesActivity.this,
                Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions((Activity) DoctorDatilesActivity.this, new String[]{
                    Manifest.permission.CALL_PHONE}, request_call);

        } else {

            String dail = "tel:" + number;
            startActivity(new Intent(Intent.ACTION_CALL, Uri.parse(dail)));
        }
    }

    void mail(String myemail) {

        //String myemail =binding.docMail.getText().toString();
        String recipientlist = myemail.toString();
        String[] recipients = recipientlist.split(",");
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_EMAIL, recipients);
        // intent.putExtra(Intent.EXTRA_SUBJECT, get_subject);
        // intent.putExtra(Intent.EXTRA_TEXT, get_message);
        intent.setType("text/plain");
        startActivity(Intent.createChooser(intent, "* Select Gmail *"));

    }
}