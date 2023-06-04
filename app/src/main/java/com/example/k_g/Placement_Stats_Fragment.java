package com.example.k_g;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.Calendar;


public class Placement_Stats_Fragment extends Fragment {


    FirebaseDatabase mFirebaseDatabase;
    DatabaseReference mDatabaseReference;


    private String Name;
    private String url;
    private String user,CollegeDatabase;
    private String Mail;
    private int max;
    private Handler customHandler;
    private AutoCompleteTextView autoCompleteTextView;
    private int count;
    private DatabaseReference mref;
    private ArrayList<String> yearlist=new ArrayList<>();
    private ArrayAdapter<String> yearadapter;

private int no_of_companies,no_of_offers,no_of_students_placed;
private String avg_ctc_offered,highest_ctc_offered;
    private Calendar calendar=Calendar.getInstance();

    private String  selectedyear;
    private CircleAnimationView circleAnimationView1,circleAnimationView2,circleAnimationView3,circleAnimationView4,circleAnimationView5;

    private TextView students_placed_textview, companies_came_textview, offers_textview, avg_ctc_textview, highest_ctc_textview;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_placement__stats_, container, false);
        Mail = getArguments().getString("mail");
        user=getArguments().getString("user");
        CollegeDatabase=getArguments().getString("database");
        selectedyear="y"+(calendar.get(Calendar.YEAR)-1);

        students_placed_textview=v.findViewById(R.id.students_placed);
        companies_came_textview=v.findViewById(R.id.companies_came);
        offers_textview=v.findViewById(R.id.offers);
        avg_ctc_textview=v.findViewById(R.id.avg_ctc);
        highest_ctc_textview=v.findViewById(R.id.highest_ctc);
        circleAnimationView1 = v.findViewById(R.id.circleAnimationView1);
        circleAnimationView2 = v.findViewById(R.id.circleAnimationView2);
        circleAnimationView3 = v.findViewById(R.id.circleAnimationView3);
        circleAnimationView4 = v.findViewById(R.id.circleAnimationView4);
        circleAnimationView5=v.findViewById((R.id.circleAnimationView5));

        getData();


         // Animation duration in milliseconds




        mref = FirebaseDatabase.getInstance().getReference(CollegeDatabase).child("placement_stats");

        mref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                String year=snapshot.getKey();
                yearlist.add(year.substring(1));
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


        autoCompleteTextView = v.findViewById(R.id.auto_complete_txt);
        yearadapter=new ArrayAdapter<String>(getContext(),R.layout.dropdownmenu_stats,yearlist);
        autoCompleteTextView.setAdapter(yearadapter);

        autoCompleteTextView.setOnItemClickListener(new AdapterView.OnItemClickListener(){

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedyear="y"+adapterView.getItemAtPosition(i).toString();
                getData();

            }
        });

        return v;
    }











private void setData(int no_of_companies, int no_of_offers,int no_of_students_placed,String avg_ctc_offered,String highest_ctc_offered) {

        int initialValue1 = 0;
        float value=1f;
        long duration = 2000;
        avg_ctc_textview.setText(avg_ctc_offered);
        highest_ctc_textview.setText(highest_ctc_offered);
        ValueAnimator animator1 = ValueAnimator.ofInt(initialValue1, no_of_companies);
        animator1.setDuration(duration);
        animator1.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                companies_came_textview.setText(String.valueOf(animatedValue));
            }
        });
        ValueAnimator animator2 = ValueAnimator.ofInt(initialValue1, no_of_students_placed);
        animator2.setDuration(duration);
        animator2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                students_placed_textview.setText(String.valueOf(animatedValue));
            }
        });
        ValueAnimator animator3 = ValueAnimator.ofInt(initialValue1, no_of_offers);
        animator3.setDuration(duration);
        animator3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                int animatedValue = (int) animation.getAnimatedValue();
                offers_textview.setText(String.valueOf(animatedValue));
            }
        });

        animator1.start();
        animator2.start();
        animator3.start();
        circleAnimationView1.animateCircle(value);
        circleAnimationView2.animateCircle(value);
        circleAnimationView3.animateCircle(value);
        circleAnimationView4.animateCircle(value);
        circleAnimationView5.animateCircle(value);



    }




    private void getData() {
        DatabaseReference ref=FirebaseDatabase.getInstance().getReference(CollegeDatabase).child("placement_stats");
        ref.child(selectedyear).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful())
                {
                    if(task.getResult().exists())
                    {  int no_of_companies,no_of_offers,no_of_students_placed;
                        String avg_ctc_offered,highest_ctc_offered;
                        DataSnapshot dataSnapshot= task.getResult();
                        no_of_companies=Integer.parseInt(String.valueOf(dataSnapshot.child("total_companies").getValue()));
                        no_of_offers=Integer.parseInt(String.valueOf(dataSnapshot.child("total_offers").getValue()));
                        no_of_students_placed=Integer.parseInt(String.valueOf(dataSnapshot.child("total_students_placed").getValue()));
                        avg_ctc_offered=String.valueOf(dataSnapshot.child("avg_ctc").getValue());
                        highest_ctc_offered=String.valueOf(dataSnapshot.child("highest_ctc").getValue());
                        setData(no_of_companies,no_of_offers,no_of_students_placed,avg_ctc_offered,highest_ctc_offered);


                    }
                }
            }
        });
    }

}