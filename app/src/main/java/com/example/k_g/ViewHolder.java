package com.example.k_g;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import de.hdodenhof.circleimageview.CircleImageView;

public class ViewHolder extends RecyclerView.ViewHolder{

    View mview;
    public ViewHolder(@NonNull View itemView) {
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

    public void setDetails(Context ctx, String desc,String dpimg,String img,String link,String pdate,String ptime,String source)
    {
        TextView mdesc=mview.findViewById(R.id.desc);
        TextView msource=mview.findViewById(R.id.source);
        TextView mlink=mview.findViewById(R.id.link);
        ImageView mimg=mview.findViewById(R.id.img1);
        CircleImageView mdpimg=mview.findViewById(R.id.dpimg);
        TextView mpdate=mview.findViewById(R.id.pdate);
        TextView mptime=mview.findViewById(R.id.ptime);
        Picasso.get().load(dpimg).into(mdpimg);
        mpdate.setText(pdate);
        mptime.setText(ptime);
        mdesc.setText(desc);
        if(!img.equalsIgnoreCase("no img"))
        {
            mimg.setVisibility(View.VISIBLE);
            Picasso.get().load(img).into(mimg);
        }

        msource.setText(source);
        mlink.setText(link);


    }



    private ViewHolder.ClickListener mClickListener;
    public interface ClickListener{
        void onItemClick(View view,int position);
        void onItemLongClick(View view,int position);
    }
    public void setOnClickListener(ViewHolder.ClickListener clickListener)
    {
        mClickListener=clickListener;
    }

}