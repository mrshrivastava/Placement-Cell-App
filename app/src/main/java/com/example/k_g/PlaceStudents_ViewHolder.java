package com.example.k_g;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class PlaceStudents_ViewHolder extends RecyclerView.ViewHolder{

    View mview;
    public PlaceStudents_ViewHolder(@NonNull View itemView) {
        super(itemView);
        mview=itemView;
        TextView btn=itemView.findViewById(R.id.desc);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mClickListener.onItemClick(view,getAbsoluteAdapterPosition());

            }
        });

        itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                mClickListener.onItemLongClick(view,getAdapterPosition());
                return true;
            }
        });

    }

    public void setDetails(Context ctx, String batch,String branch,String company,String contact,String img,String name,String pkg)
    {
        TextView mbatch=mview.findViewById(R.id.batch);
        TextView mbranch=mview.findViewById(R.id.Branch);
        TextView mcompany=mview.findViewById(R.id.company);
        TextView mcontact=mview.findViewById(R.id.contact);
        CircleImageView mimg=mview.findViewById(R.id.dpimg);
        TextView mname=mview.findViewById(R.id.name);
        TextView mpkg=mview.findViewById(R.id.pkg);
        Picasso.get().load(img).into(mimg);
        mbatch.setText(batch);
        mbranch.setText(branch);
        mcompany.setText(company);


        mcontact.setText(contact);
        mname.setText(name);
        mpkg.setText(pkg);


    }



    private PlaceStudents_ViewHolder.ClickListener mClickListener;
    public interface ClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }
    public void setOnClickListener(PlaceStudents_ViewHolder.ClickListener clickListener)
    {
        mClickListener=clickListener;
    }

}