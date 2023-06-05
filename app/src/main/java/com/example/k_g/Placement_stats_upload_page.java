package com.example.k_g;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Placement_stats_upload_page extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST=1;

    private AppCompatButton mButtonChooseImage;
    private AppCompatButton mButtonUpload;

    private EditText mEditTextDescription;
    private EditText mEditTextLink;
    private ImageView mImageView;
    private ProgressBar mProgressBar;

    private Uri img;

    private StorageReference mStorageRef;
    private DatabaseReference mDatabaseRef;

    private EditText mEditTextyear, mEditTextno_of_students_placed, mEditTextno_of_companies, mEditTextno_of_offers, mEditTextavg_ctc, mEditTexthighest_ctc;
    private int check=0;
    private int s=0;
    private String mdpimg;
    // private String description,link;
    private String description="No description";
    private String link="no link";

    private String CollegeDatabase;
    private String source;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placed_student_upload_page);

        CollegeDatabase=getIntent().getStringExtra("database");


        mDatabaseRef= FirebaseDatabase.getInstance().getReference(CollegeDatabase).child("placedstudents");


        mButtonUpload=findViewById(R.id.upload);


        mEditTextyear=findViewById(R.id.year);
        mEditTextno_of_companies=findViewById(R.id.total_companies);
        mEditTextno_of_offers=findViewById(R.id.total_offers);
        mEditTextno_of_students_placed=findViewById(R.id.total_students_placed);
        mEditTextavg_ctc=findViewById(R.id.avg_ctc);
        mEditTexthighest_ctc=findViewById(R.id.highest_ctc);


        mButtonUpload.setOnClickListener(view -> {
            if(mEditTextyear.getText().toString().trim().equalsIgnoreCase("") ||
                    mEditTextno_of_students_placed.getText().toString().trim().equalsIgnoreCase("") ||
                    mEditTextno_of_offers.getText().toString().trim().equalsIgnoreCase("") ||
                    mEditTextno_of_companies.getText().toString().trim().equalsIgnoreCase("") ||
                    mEditTextavg_ctc.getText().toString().trim().equalsIgnoreCase("") ||
                    mEditTexthighest_ctc.getText().toString().trim().equalsIgnoreCase(""))
            {
                Toast.makeText(this, "Details Missing", Toast.LENGTH_SHORT).show();
            }
            else
            {

                    ProgressBar bar=findViewById(R.id.progressbar);
                    bar.setVisibility(View.VISIBLE);
                    uploadFile();
            }

        });



    }


    private void uploadFile() {

                        Handler handler=new Handler();
                        handler.postDelayed(new Runnable() {
                            @Override
                            public void run() {

                            }
                        },1000);
                        Toast.makeText(Placement_stats_upload_page.this, "Upload Successful", Toast.LENGTH_SHORT).show();

                        Placement_Stats_Uploadmodel uploadmodel=new Placement_Stats_Uploadmodel(mEditTextavg_ctc.getText().toString().trim(),
                                mEditTexthighest_ctc.getText().toString().trim(),
                                mEditTextno_of_companies.getText().toString().trim(),
                                mEditTextno_of_offers.getText().toString().trim(),
                                mEditTextno_of_students_placed.getText().toString().trim());
                        mDatabaseRef.child(mEditTextyear.getText().toString().trim()).setValue(uploadmodel);
                        startActivity(new Intent(getApplicationContext(),placement_fragment.class));
                        finish();
                    }









}