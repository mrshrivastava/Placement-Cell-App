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
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class placement_fragment extends Fragment {

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



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.placement_fragment, container, false);


        assert getArguments() != null;
        String Mail = getArguments().getString("mail");

        FloatingActionButton upload=v.findViewById(R.id.uploadbutton);

        if(Mail.equalsIgnoreCase("intonanalytics@gmail.com"))
        {
            upload.setVisibility(View.VISIBLE);
        }
        upload.setOnClickListener(view -> {
            startActivity(new Intent(getContext(), Upload.class));
        });








        recyclerView=(RecyclerView) v.findViewById(R.id.rv);
        mLinearLayoutManager=new LinearLayoutManager(getContext());
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



        return v;
    }




    private void showkiit() {
        options=new FirebaseRecyclerOptions.Builder<MainModel>().setQuery(mDatabaseReference,MainModel.class).build();
        firebaseRecyclerAdapter=new FirebaseRecyclerAdapter<MainModel, ViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull ViewHolder holder, int position, @NonNull MainModel model) {
                String desc=model.getDesc();
                String link= model.getLink();
                String img=model.getImg();
                if(desc.equalsIgnoreCase("no description"))
                    desc=" ";
                if(link.equalsIgnoreCase("no link"))
                    link=" ";

                holder.setDetails(getContext(),desc,model.getDpimg(), img,link,model.getPdate(),model.getPtime(),model.getSource());
                holder.itemView.findViewById(R.id.link).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String accesslink=model.getLink();
                        if(!accesslink.equalsIgnoreCase("no link"))
                        {
                            gotoUrl(accesslink);
                        }

                    }
                });
                holder.itemView.findViewById(R.id.img1).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent=new Intent(getContext(),postview.class);
                        intent.putExtra("imgurl",model.getImg());
                        startActivity(intent);
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