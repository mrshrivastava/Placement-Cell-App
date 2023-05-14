package com.example.k_g;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class PastCompanies_ViewHolder extends RecyclerView.ViewHolder{

    View mview;
    public PastCompanies_ViewHolder(@NonNull View itemView) {
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

    public void setDetails(Context ctx, String batch,String branch,String company,String img,String pkg,String sem)
    {
       TextView mbatch=mview.findViewById(R.id.batch);
       TextView mbranch=mview.findViewById(R.id.branch);
        TextView mcompany=mview.findViewById(R.id.company);
        TextView msem=mview.findViewById(R.id.sem);
        CircleImageView mimg=mview.findViewById(R.id.img);
        TextView mpkg=mview.findViewById(R.id.pkg);


        Picasso.get().load(img).into(mimg);
        mbatch.setText(batch);
        mbranch.setText(branch);
        mcompany.setText(company);
        msem.setText(sem);
        mpkg.setText(pkg);


    }



    private PastCompanies_ViewHolder.ClickListener mClickListener;
    public interface ClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }
    public void setOnClickListener(PastCompanies_ViewHolder.ClickListener clickListener)
    {
        mClickListener=clickListener;
    }

}