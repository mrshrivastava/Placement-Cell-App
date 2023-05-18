package com.example.k_g;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.DragEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.squareup.picasso.Picasso;

public class postview extends AppCompatActivity {

    ImageView post;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_postview);
        String img=getIntent().getStringExtra("imgurl");
        post=(ImageView) findViewById(R.id.postimg);
        Picasso.get().load(img).into(post);


    }
}