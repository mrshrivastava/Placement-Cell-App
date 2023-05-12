package com.example.k_g;

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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Placed_Students_Fragment extends Fragment {

    RecyclerView recyclerView;

    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;
    FirebaseRecyclerAdapter<Placed_Student_Model,PlaceStudents_ViewHolder> firebaseRecyclerAdapter;
    FirebaseRecyclerOptions<Placed_Student_Model> options;
    LinearLayoutManager mLinearLayoutManager;

    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    private String Name;
    private String url;

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
        View v=inflater.inflate(R.layout.fragment_placed__students_, container, false);










        recyclerView=(RecyclerView) v.findViewById(R.id.rv);
        mLinearLayoutManager=new LinearLayoutManager(getContext());
        mLinearLayoutManager.setReverseLayout(true);
        mLinearLayoutManager.setStackFromEnd(true);
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mDatabaseReference =mFirebaseDatabase.getReference("placedstudents");
        showkiit();
        /*TextView des=findViewById(R.id.desc);
        des.setOnClickListener(view -> {
            String link=des.getText().toString();
            if(link!=null)
            {
                gotoUrl(link);
            }
        });*/



        return v;
    }




    private void showkiit() {
        options=new FirebaseRecyclerOptions.Builder<Placed_Student_Model>().setQuery(mDatabaseReference,Placed_Student_Model.class).build();
        firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<Placed_Student_Model, PlaceStudents_ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull PlaceStudents_ViewHolder holder, int position, @NonNull Placed_Student_Model model) {


                holder.setDetails(getContext(),model.getBatch(),model.getBranch(),model.getCompany(),model.getContact(),model.getImg(),model.getName(), model.getPkg());
                holder.itemView.findViewById(R.id.contact).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String accesslink=model.getContact();
                        if(!accesslink.equalsIgnoreCase("no link"))
                        {
                            gotoUrl(accesslink);
                        }

                    }
                });
//                holder.itemView.findViewById(R.id.img1).setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View view) {
//                        Intent intent=new Intent(getContext(),postview.class);
//                        intent.putExtra("imgurl",model.getImg());
//                        startActivity(intent);
//                    }
//                });


            }

            @NonNull
            @Override
            public PlaceStudents_ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
                View itemView= LayoutInflater.from(parent.getContext()).inflate(R.layout.placed_student_cardview,parent,false);
                PlaceStudents_ViewHolder viewHolder=new PlaceStudents_ViewHolder(itemView);
                viewHolder.setOnClickListener(new PlaceStudents_ViewHolder.ClickListener() {

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