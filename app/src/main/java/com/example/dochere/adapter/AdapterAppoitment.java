package com.example.dochere.adapter;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.pdf.PdfDocument;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.FileProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dochere.BuildConfig;
import com.example.dochere.R;
import com.example.dochere.RatingActivity;
import com.example.dochere.model.ModelAppoitment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

public class AdapterAppoitment extends RecyclerView.Adapter<AdapterAppoitment.Holder> {

    private static final String TAG ="dff" ;
    ArrayList<ModelAppoitment>appoitments;
    Context context;
     AlertDialog dialog_condition;

    private static final int REQUEST_EXTERNAL_STORAGE = 1;
    private static String[] PERMISSIONS_STORAGE = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };


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


        hasPermission();

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
            holder.imageView.setImageResource(R.drawable.ic_unsyck);
            holder.rating.setVisibility(View.GONE);
            holder.makePayment.setVisibility(View.GONE);
            holder.show_prescription.setVisibility(View.GONE);
        }


        holder.show_prescription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ConstraintLayout constraintLayout;
                EditText prescription,food;
                TextView p_name,d_name,p_age,date;
                Button submit;

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                final View mview = LayoutInflater.from(context).inflate(R.layout.write_prescription, null);
                builder.setView(mview);
                dialog_condition = builder.create();
                // dialog_condition.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                dialog_condition.show();

                prescription = mview.findViewById(R.id.et_prescription);
                food = mview.findViewById(R.id.food);
                constraintLayout = mview.findViewById(R.id.layout);
                p_age = mview.findViewById(R.id.p_age);
                p_name = mview.findViewById(R.id.p_name);
                date = mview.findViewById(R.id.date);
                d_name = mview.findViewById(R.id.doc_name);
                submit = mview.findViewById(R.id.submit);
                submit.setText("Download");
                submit.setVisibility(View.VISIBLE);
                prescription.setEnabled(false);
                food.setEnabled(false);
                p_name.setVisibility(View.VISIBLE);
                p_age.setVisibility(View.VISIBLE);
                d_name.setVisibility(View.VISIBLE);
                date.setVisibility(View.VISIBLE);

                p_name.setText("Patient: "+appoitments.get(position).getName());
                p_age.setText("Age: "+appoitments.get(position).getAge());
                d_name.setText("Doc : "+appoitments.get(position).getDocName());
                date.setText("Date : "+appoitments.get(position).getDate());
                food.setText(appoitments.get(position).getFood());
                prescription.setText(appoitments.get(position).getPrescription());

                submit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        submit.setVisibility(View.GONE);
                        getBitmapFromView(constraintLayout,appoitments.get(position).getId());

                    }
                });
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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public Bitmap getBitmapFromView(View view, String f_id) {
        view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
        Bitmap bitmap = Bitmap.createBitmap(view.getMeasuredWidth(), view.getMeasuredHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        view.draw(canvas);
        bitmapToPdf(bitmap, f_id);
        return bitmap;
    }


    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void bitmapToPdf(Bitmap bitmap, String f_id) {

        PdfDocument pdfDocument = new PdfDocument();
        PdfDocument.PageInfo pi = new PdfDocument.PageInfo.Builder(bitmap.getWidth(), bitmap.getHeight(), 1).create();
        PdfDocument.Page page = pdfDocument.startPage(pi);
        Canvas canvas = page.getCanvas();
        Paint paint = new Paint();
        paint.setColor(Color.parseColor("#FFFFFF"));
        canvas.drawPaint(paint);
        bitmap = Bitmap.createScaledBitmap(bitmap, bitmap.getWidth(), bitmap.getHeight(), true);
        paint.setColor(Color.BLUE);
        canvas.drawBitmap(bitmap, 0, 0, null);
        pdfDocument.finishPage(page);

        File root = new File(Environment.getExternalStorageDirectory(), "Download");
        if (!root.exists()) {
            root.mkdir();
        }
        File file = new File(root, f_id + ".pdf");
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            try {
                pdfDocument.writeTo(fileOutputStream);
                Toast.makeText(context, "ডাউনলোড সফল, মোবাইলের Download ফোল্ডের চেক করুন ", Toast.LENGTH_SHORT).show();

            } catch (IOException e) {
                Log.d(TAG, "bitmapToPdf1: "+e.getMessage());
                e.printStackTrace();

            }
        } catch (FileNotFoundException e) {
            Log.d(TAG, "bitmapToPdf2: "+e.getMessage());
            e.printStackTrace();
        }
        pdfDocument.close();



        dialog_condition.dismiss();

        //open pdf
      /*  Intent intent = new Intent(Intent.ACTION_VIEW);
        Uri uri = FileProvider.getUriForFile(context, BuildConfig.APPLICATION_ID + ".fileprovider", file);
        intent.setDataAndType(uri, "application/pdf");
        intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        context.startActivity(Intent.createChooser(intent, "Open with..."));*/

    }


    public void hasPermission() {
        int permission = ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if (permission != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    (Activity) context,
                    PERMISSIONS_STORAGE,
                    REQUEST_EXTERNAL_STORAGE
            );
        }
    }
}

