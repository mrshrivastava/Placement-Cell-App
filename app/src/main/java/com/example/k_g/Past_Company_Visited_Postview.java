package com.example.k_g;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Past_Company_Visited_Postview extends AppCompatActivity {

    private String img, branch, company,batch,sem,pkg,hired,desc;
    private CircleImageView mimg;
    private TextView mbranch,mcompany,mbatch,msem,mpkg,mhired,mdesc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_company_visited_postview);
        mbranch=findViewById(R.id.branch);
        mbatch=findViewById(R.id.batch);
        mcompany=findViewById(R.id.company);
        msem=findViewById(R.id.sem);
        mpkg=findViewById(R.id.pkg);
        mhired=findViewById(R.id.hired);
        mdesc=findViewById(R.id.desc);
        mimg=findViewById(R.id.img);

        img= getIntent().getStringExtra("img");
        batch= getIntent().getStringExtra("batch");
        branch= getIntent().getStringExtra("branch");
        company= getIntent().getStringExtra("company");
        sem= getIntent().getStringExtra("sem");
        pkg= getIntent().getStringExtra("pkg");
        hired= getIntent().getStringExtra("hired");
        desc= getIntent().getStringExtra("desc");


        mbranch.setText(branch);
        mbatch.setText(batch);
        mcompany.setText(company);
        msem.setText(sem);
        mpkg.setText(pkg);
        mhired.setText(hired);
        mdesc.setText(desc);

        Picasso.get().load(img).into(mimg);





    }
}