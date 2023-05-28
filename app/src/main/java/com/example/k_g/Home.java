package com.example.k_g;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class Home extends AppCompatActivity {
    RecyclerView recyclerView;

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    FirebaseRecyclerAdapter<MainModel,ViewHolder> firebaseRecyclerAdapter;
    FirebaseRecyclerOptions<MainModel> options;
    LinearLayoutManager mLinearLayoutManager;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    private String Name;
    private String url;
    private String Mail;
    private Uri url_link;
    private String selectedCollege;
    private String selectedUser;

    private String StudentMailFormat;

    private DatabaseReference mref;
    private DatabaseReference databaseReference;

    private String CollegeDatabase;

    private ArrayList<String> credentials=new ArrayList<>();

    FirebaseFirestore fstore;
    private String adminmail;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        selectedCollege=getIntent().getStringExtra("college");
        selectedUser=getIntent().getStringExtra("user");
        StudentMailFormat=getIntent().getStringExtra("studentmail");
        CollegeDatabase=getIntent().getStringExtra("collegedatabase");
        adminmail=getIntent().getStringExtra("adminmail");


        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        gsc= GoogleSignIn.getClient(this,gso);
        GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(this);






        if(account != null)
        {
                Name=account.getDisplayName();
                Mail= account.getEmail();



                url_link=account.getPhotoUrl();
                try {
                    url=url_link.toString();
                }
                catch (NullPointerException e)
                {
                    url="https://firebasestorage.googleapis.com/v0/b/kiit-app-93db4.appspot.com/o/uploads%2Fuser%20image.jpg?alt=media&token=dd3b3a30-e4ab-425a-b45c-ee965251ae69";
                }


            if(selectedUser.equalsIgnoreCase("student"))
            {

                if(Mail.indexOf(StudentMailFormat)!=-1)
                {
                    mainpage();
                }
                else
                {
                    Toast.makeText(this, "Not an authorised user", Toast.LENGTH_SHORT).show();
                    SignOut();
                }

            }


                else if(selectedUser.equalsIgnoreCase("admin"))
                {

                    if(Mail.equalsIgnoreCase(adminmail))
                    {
                        mainpage();
                    }
                    else
                    {
                        Toast.makeText(this, "Not an authorised user", Toast.LENGTH_SHORT).show();
                        SignOut();
                    }

                }

        }

    }

    private void mainpage()
    {
        Intent intent=new Intent(getApplicationContext(), MainPage.class);
        intent.putExtra("mail",Mail);
        intent.putExtra("name",Name);
        intent.putExtra("dp",url);
        intent.putExtra("user",selectedUser);
        intent.putExtra("database",CollegeDatabase);
        startActivity(intent);
        finish();
    }
    private void SignOut() {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Intent intent=new Intent(getApplicationContext(), SignIn.class);
                startActivity(intent);
                finish();

            }
        });
    }

}

