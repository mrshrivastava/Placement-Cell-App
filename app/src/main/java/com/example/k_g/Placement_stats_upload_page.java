package com.example.k_g;


import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Placement_stats_upload_page extends AppCompatActivity {
    private AppCompatButton mButtonUpload;
    private DatabaseReference mDatabaseRef;

    private EditText mEditTextyear, mEditTextno_of_students_placed, mEditTextno_of_companies, mEditTextno_of_offers, mEditTextavg_ctc, mEditTexthighest_ctc;
    private String CollegeDatabase;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placement_stats_upload_page);

        CollegeDatabase=getIntent().getStringExtra("database");


        mDatabaseRef= FirebaseDatabase.getInstance().getReference(CollegeDatabase).child("placement_stats");


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

                        String avg_ctc=mEditTextavg_ctc.getText().toString().trim();
                        String highest_ctc=mEditTexthighest_ctc.getText().toString().trim();
                        if(!checkLPA(avg_ctc))
                        {
                            avg_ctc=avg_ctc+" Lpa";
                        }
                        if(!checkLPA(highest_ctc))
                        {
                            highest_ctc=highest_ctc+" Lpa";
                        }

                        Placement_Stats_Uploadmodel uploadmodel=new Placement_Stats_Uploadmodel(avg_ctc,
                                highest_ctc,
                                mEditTextno_of_companies.getText().toString().trim(),
                                mEditTextno_of_offers.getText().toString().trim(),
                                mEditTextno_of_students_placed.getText().toString().trim());
                        String key="y"+mEditTextyear.getText().toString().trim();
                        mDatabaseRef.child(key).setValue(uploadmodel);
                        startActivity(new Intent(getApplicationContext(),placement_fragment.class));
                        finish();
                    }

    private boolean checkLPA(String s) {
        s=s.toLowerCase();
        if(s.indexOf("lpa")!=-1)
            return true;
        else
            return false;
    }
}