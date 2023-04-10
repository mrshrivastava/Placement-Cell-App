package com.example.k_g;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ClipData;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Home extends AppCompatActivity {

    ArrayList<String> list=new ArrayList<>();

    RecyclerView recyclerView;

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    FirebaseRecyclerAdapter<MainModel,ViewHolder> firebaseRecyclerAdapter;
    FirebaseRecyclerOptions<MainModel> options;
    LinearLayoutManager mLinearLayoutManager;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    private String Name;

    @Override
    protected void onStart() {
        super.onStart();
        if(firebaseRecyclerAdapter != null)
        {
            firebaseRecyclerAdapter.startListening();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_list,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case R.id.logout: SignOut();
                return true;
            default:return super.onOptionsItemSelected(item);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //Toolbar toolbar=findViewById(R.id.toolbar22);
        //getSupportActionBar(toolbar);
        //getSupportActionBar().hide();

        FloatingActionButton upload=findViewById(R.id.floatingActionButton);
        upload.setOnClickListener(view -> {
            Intent intent=new Intent(Home.this,Upload.class);
            intent.putExtra("keyname",Name);
            startActivity(intent);

        });

        gso=new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        gsc= GoogleSignIn.getClient(this,gso);
        GoogleSignInAccount account=GoogleSignIn.getLastSignedInAccount(this);
        if(account != null)
        {
            Name=account.getDisplayName();
            String Mail= account.getEmail();

            if(Mail.indexOf("bit-bangalore.edu.in")==-1 && !Mail.equalsIgnoreCase("intonanalytics@gmail.com"))
            {
                Toast.makeText(this, "Not an authorised user", Toast.LENGTH_SHORT).show();
                SignOut();
            }

        }







        recyclerView=(RecyclerView) findViewById(R.id.rv);
        mLinearLayoutManager=new LinearLayoutManager(this);
        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference =mFirebaseDatabase.getReference("kiit");
        showkiit();
        /*TextView des=findViewById(R.id.desc);
        des.setOnClickListener(view -> {
            String link=des.getText().toString();
            if(link!=null)
            {
                gotoUrl(link);
            }
        });*/
    }



    private void SignOut() {
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finish();
                startActivity(new Intent(getApplicationContext(),SignIn.class));
            }
        });
    }

    private void showkiit() {
        options=new FirebaseRecyclerOptions.Builder<MainModel>().setQuery(mDatabaseReference,MainModel.class).build();
        firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<MainModel, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull MainModel model) {
                holder.setDetails(getApplicationContext(),model.getDesc(), model.getImg(),model.getLink(),model.getSource());
                holder.itemView.findViewById(R.id.link).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String accesslink=model.getLink();
                        gotoUrl(accesslink);
                    }
                });


            }

            @NonNull
            @Override
            public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.main_item,parent,false);
                ViewHolder viewHolder=new ViewHolder(itemView);
                viewHolder.setOnClickListener(new ViewHolder.ClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {



                        Toast.makeText(Home.this,"Hello",Toast.LENGTH_SHORT);
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        Toast.makeText(Home.this,"Long Click",Toast.LENGTH_SHORT);
                    }

                });
                return viewHolder;
            }
        };

        recyclerView.setLayoutManager(mLinearLayoutManager);
        firebaseRecyclerAdapter.startListening();
        recyclerView.setAdapter(firebaseRecyclerAdapter);
    }
    private void gotoUrl(String s) {
        Uri uri= Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW,uri));
    }
}