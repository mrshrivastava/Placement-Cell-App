package com.example.k_g;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

public class Payment extends AppCompatActivity {

    private Button pay;
    String GOOGLE_PAY_PACKAGE_NAME = "com.google.android.apps.nbu.paisa.user";
    int GOOGLE_PAY_REQUEST_CODE = 123;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==123 && resultCode==RESULT_OK && data.getData()!=null)
        {
            Toast.makeText(this, "payment successful. Account created", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),Home.class));
            finish();
        }
    }

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        pay=findViewById(R.id.paybutton);
        pay.setOnClickListener(view -> {
            Uri uri =
                    new Uri.Builder()
                            .scheme("upi")
                            .authority("pay")
                            .appendQueryParameter("pa", "q04105884@ybl")
                            .appendQueryParameter("pn", "Abdul Naser K")
                            .appendQueryParameter("mc", "22377")
                            .appendQueryParameter("tr", "")
                            .appendQueryParameter("tn", "bit placement login")
                            .appendQueryParameter("am", "2")
                            .appendQueryParameter("cu", "INR")
                           // .appendQueryParameter("url", "your-transaction-url")
                            .build();
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(uri);
            intent.setPackage(GOOGLE_PAY_PACKAGE_NAME);
            startActivityForResult(intent, GOOGLE_PAY_REQUEST_CODE);
        });

    }
}