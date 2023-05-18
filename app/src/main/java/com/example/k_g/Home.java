package com.example.k_g;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

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


    @Override
    public void onStart() {
        super.onStart();
        if(firebaseRecyclerAdapter != null)
        {
            firebaseRecyclerAdapter.startListening();
        }

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


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


            if(Mail.indexOf("bit-bangalore.edu.in")==-1 && !Mail.equalsIgnoreCase("intonanalytics@gmail.com") )
            {
                Toast.makeText(this, "Not an authorised user", Toast.LENGTH_SHORT).show();
                SignOut();
            }
            else
            {


                Intent intent=new Intent(getApplicationContext(), MainPage.class);
                intent.putExtra("mail",Mail);
                intent.putExtra("name",Name);
                intent.putExtra("dp",url);
                startActivity(intent);
                finish();
            }

        }

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

