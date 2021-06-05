package com.example.gu;

import android.content.Context;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class gAdapter extends RecyclerView.Adapter<gAdapter.gViewHolder> {

    ArrayList<data> arrayList;
    //Context context;

    public gAdapter(ArrayList<data> arrayList)
    {
        this.arrayList=arrayList;
       // this.context=context;
    }

    @NonNull
    @Override
    public gViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //LayoutInflater layoutInflater =
        View v =  LayoutInflater.from(parent.getContext()).inflate(R.layout.item_layout,parent,false);
        return new gViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull gViewHolder holder, int position) {

        data obj = arrayList.get(position);
        String ImgUrl = obj.getImgUrl();
        String Name = obj.getName();
        String Login = obj.getLogin();

        holder.mtext1.setText(Name);
        holder.mtext2.setText(Login);
        Picasso.get().load(ImgUrl).into(holder.mImage);
        //Picasso.get().load(R.drawable.ic_android_black_24dp).into(holder.mImage);
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class gViewHolder extends RecyclerView.ViewHolder
    {
        ImageView mImage;
        TextView mtext1;
        TextView mtext2;


        public gViewHolder(@NonNull View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.item_img);
            mtext1=itemView.findViewById(R.id.item_text1);
            mtext2=itemView.findViewById(R.id.item_text2);


        }
    }

}
