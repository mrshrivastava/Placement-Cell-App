package com.example.k_g;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class Placed_Student_Postview extends AppCompatActivity {

    private String img, branch, company,batch,contact,pkg,name;
    private CircleImageView mimg;
    private TextView mbranch,mcompany,mbatch,mname,mpkg,mcontact;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_placed_student_postview);

        mbranch=findViewById(R.id.branch);
        mbatch=findViewById(R.id.batch);
        mcompany=findViewById(R.id.company);
        mname=findViewById(R.id.name);
        mpkg=findViewById(R.id.pkg);
        mcontact=findViewById(R.id.contact);
        mimg=findViewById(R.id.img);

        img= getIntent().getStringExtra("img");
        batch= getIntent().getStringExtra("batch");
        branch= getIntent().getStringExtra("branch");
        company= getIntent().getStringExtra("company");
        name= getIntent().getStringExtra("name");
        pkg= getIntent().getStringExtra("pkg");
        contact= getIntent().getStringExtra("contact");



        mbranch.setText(branch);
        mbatch.setText(batch);
        mcompany.setText(company);
        mname.setText(name);
        mpkg.setText(pkg);
        mcontact.setText("LinkedIn/redirect");

        Picasso.get().load(img).into(mimg);
        mcontact.setOnClickListener(view -> {
            gotoUrl(contact);
        });
    }
    private void gotoUrl(String s) {
        Uri uri= Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}