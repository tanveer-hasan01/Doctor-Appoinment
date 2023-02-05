package com.example.dochere;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.dochere.databinding.ActivityAppoinmentBinding;
import com.example.dochere.databinding.ActivityDoctorDatilesBinding;
import com.example.dochere.model.ModelAppoitment;
import com.example.dochere.network.ApiClient;
import com.example.dochere.network.ApiInterface;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AppoinmentActivity extends AppCompatActivity {
    DatePickerDialog datePickerDialog;
    MysharedPreferance sharedPreferance;
    ActivityAppoinmentBinding binding;
    ApiInterface apiInterface;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAppoinmentBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setTitle("Patient Details");

        Retrofit instance = ApiClient.instance();
        apiInterface = instance.create(ApiInterface.class);
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


        binding.confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!sharedPreferance.getSession().equals("logged")){

                    Toast.makeText(AppoinmentActivity.this, "Login required", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(AppoinmentActivity.this, LoginActivity.class));

                }
               else if(binding.complain.getText().toString().isEmpty()){
                   binding.complain.setError("required");
               }else if(binding.blood.getText().toString().isEmpty()){
                    binding.blood.setError("required");
                }else if(binding.age.getText().toString().isEmpty()){
                   binding.age.setError("required");
               }else if(binding.patientPhone.getText().toString().isEmpty()){
                   binding.patientPhone.setError("required");
               }else if(binding.weight.getText().toString().isEmpty()){
                   binding.weight.setError("required");
               }else if(binding.date.getText().toString().isEmpty()){
                   binding.date.setError("required");
               }
               else {

                   ProgressDialog dialog = ProgressDialog.show(AppoinmentActivity.this, "Loading", "Please wait...", true);
                   ModelAppoitment modelAppoitment=new ModelAppoitment();
                   modelAppoitment.setName(binding.patientName.getText().toString());
                   modelAppoitment.setAge(binding.age.getText().toString());
                   modelAppoitment.setWeight(binding.weight.getText().toString());
                   modelAppoitment.setBlood(binding.blood.getText().toString());
                   modelAppoitment.setDocId(getIntent().getStringExtra("id"));
                   modelAppoitment.setPhone(binding.patientPhone.getText().toString());
                   modelAppoitment.setComment(binding.complain.getText().toString());
                   modelAppoitment.setDate(binding.date.getText().toString());
                   modelAppoitment.setDocName(getIntent().getStringExtra("name"));
                   modelAppoitment.setUserID(sharedPreferance.getUserID());
                   modelAppoitment.setEmail(sharedPreferance.getemail());
                   modelAppoitment.setPrescription("none");
                   modelAppoitment.setFood("none");

                   apiInterface.insertAppointment(modelAppoitment).enqueue(new Callback<ModelAppoitment>() {
                       @Override
                       public void onResponse(Call<ModelAppoitment> call, Response<ModelAppoitment> response) {
                           Toast.makeText(AppoinmentActivity.this, "Appointment Successful", Toast.LENGTH_LONG).show();
                           startActivity(new Intent(AppoinmentActivity.this, MainActivity.class));
                           finish();
                       }

                       @Override
                       public void onFailure(Call<ModelAppoitment> call, Throwable t) {
                           dialog.dismiss();
                           Toast.makeText(AppoinmentActivity.this, "Failed, Try again", Toast.LENGTH_SHORT).show();

                       }
                   });

               }



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
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis() - 1000);
        datePickerDialog.show();
    }
}