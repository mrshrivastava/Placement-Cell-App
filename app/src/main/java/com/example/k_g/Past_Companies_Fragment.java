package com.example.k_g;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.net.Uri;
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


public class Past_Companies_Fragment extends Fragment {

    RecyclerView recyclerView;

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    FirebaseRecyclerAdapter<Past_Companies_Model,PastCompanies_ViewHolder> firebaseRecyclerAdapter;
    FirebaseRecyclerOptions<Past_Companies_Model> options;
    LinearLayoutManager mLinearLayoutManager;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    private String Name;
    private String url;
    private String user,CollegeDatabase;
    private String Mail;

    @Override
    public void onStart() {
        super.onStart();
        if(firebaseRecyclerAdapter != null)
        {
            firebaseRecyclerAdapter.startListening();
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_past__companies_, container, false);


        Mail = getArguments().getString("mail");
        user=getArguments().getString("user");
        CollegeDatabase=getArguments().getString("database");

        FloatingActionButton upload=v.findViewById(R.id.uploadbutton);

        if(user.equalsIgnoreCase("admin"))
        {
            upload.setVisibility(View.VISIBLE);
        }
        upload.setOnClickListener(view -> {
            Intent intent=new Intent(getContext(),past_company_upload_page.class);
            intent.putExtra("database",CollegeDatabase);
            startActivity(intent);
        });







        recyclerView=(RecyclerView) v.findViewById(R.id.rv);
        mLinearLayoutManager=new LinearLayoutManager(getContext());
        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference =mFirebaseDatabase.getReference(CollegeDatabase).child("pastcompanies");
        showkiit();


        return v;
    }




    private void showkiit() {
        options=new FirebaseRecyclerOptions.Builder<Past_Companies_Model>().setQuery(mDatabaseReference,Past_Companies_Model.class).build();
        firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Past_Companies_Model, PastCompanies_ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull PastCompanies_ViewHolder holder, int position, @NonNull Past_Companies_Model model) {


                holder.setDetails(getContext(),model.getBatch(),model.getBranch(),model.getCompany(),model.getImg(),model.getPkg(), model.getSem());
                holder.itemView.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        AlertDialog.Builder builder= new AlertDialog.Builder(holder.itemView.getContext());
                        builder.setTitle("Are you sure you want to delete?");
                        builder.setMessage("Deleted data can't be undo");
                        builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                FirebaseDatabase.getInstance().getReference(CollegeDatabase).child("pastcompanies")
                                        .child(getRef(holder.getAdapterPosition()).getKey()).removeValue();
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                Toast.makeText(holder.itemView.getContext(), "Cancelled",Toast.LENGTH_SHORT).show();
                            }
                        });
                        builder.show();
                    }
                });
                holder.itemView.setOnClickListener(view -> {
                    Intent intent=new Intent(getContext(),Past_Company_Visited_Postview.class);
                    intent.putExtra("batch",model.getBatch());
                    intent.putExtra("branch",model.getBranch());
                    intent.putExtra("company",model.getCompany());
                    intent.putExtra("sem",model.getSem());
                    intent.putExtra("hired",model.getHired());
                    intent.putExtra("pkg",model.getPkg());
                    intent.putExtra("desc",model.getDesc());
                    intent.putExtra("img",model.getImg());
                    startActivity(intent);
                });


            }

            @NonNull
            @Override
            public PastCompanies_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.past_company_visited_cardview,parent,false);
                if(user.equalsIgnoreCase("admin"))
                {
                    itemView.findViewById(R.id.delete).setVisibility(View.VISIBLE);
                }
                PastCompanies_ViewHolder viewHolder=new PastCompanies_ViewHolder(itemView);
                viewHolder.setOnClickListener(new PastCompanies_ViewHolder.ClickListener() {

                    @Override
                    public void onItemClick(View view, int position) {
                        Toast.makeText(getContext(),"Hello",Toast.LENGTH_SHORT);
                    }

                    @Override
                    public void onItemLongClick(View view, int position) {
                        Toast.makeText(getContext(),"Long Click",Toast.LENGTH_SHORT);
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