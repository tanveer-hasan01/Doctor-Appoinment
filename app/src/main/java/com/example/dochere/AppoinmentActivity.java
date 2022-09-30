package com.example.dochere;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;

import com.example.dochere.databinding.ActivityAppoinmentBinding;
import com.example.dochere.databinding.ActivityDoctorDatilesBinding;

import java.util.Calendar;

public class AppoinmentActivity extends AppCompatActivity {
    DatePickerDialog datePickerDialog;
    MysharedPreferance sharedPreferance;
    ActivityAppoinmentBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAppoinmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Patient Details");

        sharedPreferance = MysharedPreferance.getPreferences(this);
        if (getIntent().getStringExtra("gender").equals("male")) {
            binding.avater.setImageResource(R.drawable.doctor);
        } else {
            binding.avater.setImageResource(R.drawable.doctor_frmale);
        }
        binding.name.setText(getIntent().getStringExtra("name"));
        binding.category.setText(getIntent().getStringExtra("category"));
        binding.price.setText(getIntent().getStringExtra("visit"));
        binding.rating.setText(getIntent().getStringExtra("rating"));

        binding.patientName.setText(sharedPreferance.getName());
        binding.patientPhone.setText(sharedPreferance.getPhone());

        binding.date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                pickDate();
            }
        });

    }

    private void pickDate() {
        final Calendar c = Calendar.getInstance();
        int mYear = c.get(Calendar.YEAR); // current year
        int mMonth = c.get(Calendar.MONTH); // current month
        int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
        // date picker dialog
        datePickerDialog = new DatePickerDialog(AppoinmentActivity.this,
                new DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                        // set day of month , month and year value in the edit text
                        binding.date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                    }
                }, mYear, mMonth, mDay);
        datePickerDialog.show();
    }
}