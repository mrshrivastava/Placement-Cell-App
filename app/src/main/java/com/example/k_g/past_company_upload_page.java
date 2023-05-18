package com.example.k_g;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;

public class past_company_upload_page extends AppCompatActivity {
//    AutoCompleteTextView autoCompleteTextView =findViewById(R.id.autoCompleteTextView);

//    String[] languages = getResources().getStringArray(R.array.programming_languages);
//    ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, R.layout.dropdown_menu, getResources().getStringArray(R.array.programming_languages));
//    AutoCompleteTextView autocompleteTV = findViewById(R.id.autoCompleteTextView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_past_company_upload_page);
//        autocompleteTV.setAdapter(arrayAdapter);
    }
}