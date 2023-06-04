package com.example.k_g;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class SignIn extends AppCompatActivity {
    AppCompatButton google_img;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;

    private ArrayList<String> collegelist=new ArrayList<>();
    DatabaseReference mref;

    private String StudentMailFormat;
    private String adminmail;

    private RadioGroup radioGroup;

    private RadioButton radioButton;

    private ArrayAdapter<String> collegeadapter;

    String selectedCollege;

    String selectedUser;

    private DatabaseReference databaseReference;

    private String CollegeDatabase;

    private AutoCompleteTextView autoCompleteTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        // Fetching college list from database to display in dropdown box to select respective college


        mref= FirebaseDatabase.getInstance().getReference("colleges");

        mref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String collegename=snapshot.getKey();
                collegelist.add(collegename);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });







        autoCompleteTextView=findViewById(R.id.auto_complete_txt);
        collegeadapter=new ArrayAdapter<String>(this,R.layout.dropdownmenu,collegelist);
        autoCompleteTextView.setAdapter(collegeadapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedCollege=adapterView.getItemAtPosition(i).toString();

            }
        });

        //End of fetching college list




        //-------------------------------------------------------------------------------------------------------------------


        // Checking admin or student has logged in-----------------

        radioGroup=findViewById(R.id.radiogroup);

        //----------------------------------------------------------------------------------------------------------------


        // Now sign in thing is being done------------------------------------


        google_img=(AppCompatButton) findViewById(R.id.signin);
        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        gsc= GoogleSignIn.getClient(this,gso);



        google_img.setOnClickListener(view -> {
            if(selectedCollege.equalsIgnoreCase(""))
            {
                Toast.makeText(this, "Select College", Toast.LENGTH_SHORT).show();
            }
            else
            {
                int radioId=radioGroup.getCheckedRadioButtonId();
                radioButton=findViewById(radioId);
                selectedUser=radioButton.getText().toString();

                mref= FirebaseDatabase.getInstance().getReference("colleges").child(selectedCollege);

                mref.child("studentmail").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            StudentMailFormat= dataSnapshot.getValue(String.class);
                            // Do something with the fetched value
                            // For example, log it or display it in a TextView
                            Log.d("Firebase", "\n\n\n\n st mail="+StudentMailFormat);

                        } else {
                            // Key doesn't exist
                            Log.d("Firebase", "Key doesn't exist");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Error occurred while fetching the data
                        Log.e("Firebase", "Error fetching value: " + databaseError.getMessage());
                    }
                });

                mref.child("database").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            CollegeDatabase= dataSnapshot.getValue(String.class);
                            // Do something with the fetched value
                            // For example, log it or display it in a TextView

                        } else {
                            // Key doesn't exist
                            Log.d("Firebase", "Key doesn't exist");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Error occurred while fetching the data
                        Log.e("Firebase", "Error fetching value: " + databaseError.getMessage());
                    }
                });

                mref.child("adminmail").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        if (dataSnapshot.exists()) {
                            adminmail= dataSnapshot.getValue(String.class);
                            // Do something with the fetched value
                            // For example, log it or display it in a TextView

                        } else {
                            // Key doesn't exist
                            Log.d("Firebase", "Key doesn't exist");
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {
                        // Error occurred while fetching the data
                        Log.e("Firebase", "Error fetching value: " + databaseError.getMessage());
                    }
                });

                signinfunc();
            }

        });

    }

    private void signinfunc() {

        Intent intent=gsc.getSignInIntent();
        startActivityForResult(intent,100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==100)
        {
            Task<GoogleSignInAccount> task=GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                Home();
            }
            catch(ApiException e)
            {
                Toast.makeText(this, "Error", Toast.LENGTH_SHORT).show();
            }
        }

    }

    private void Home() {

        Intent intent=new Intent(getApplicationContext(),Home.class);
        intent.putExtra("college",selectedCollege);
        intent.putExtra("user",selectedUser);
        intent.putExtra("studentmail",StudentMailFormat);
        intent.putExtra("collegedatabase",CollegeDatabase);
        intent.putExtra("adminmail",adminmail);
        startActivity(intent);
        finish();
    }

    public void checkButton(View v)
    {
        int radioId=radioGroup.getCheckedRadioButtonId();
        radioButton=findViewById(radioId);
        selectedUser=radioButton.getText().toString();

    }
}