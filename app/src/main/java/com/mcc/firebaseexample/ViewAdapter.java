package com.mcc.firebaseexample;


import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ViewAdapter extends RecyclerView.Adapter<ViewAdapter.MyHoder> {

    List<Model> list;
    Context context;

    public ViewAdapter(List<Model> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public MyHoder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.card, parent, false);
        MyHoder myHoder = new MyHoder(view);


        return myHoder;
    }

    @Override
    public void onBindViewHolder(MyHoder holder, int position) {
        Model mylist = list.get(position);
        holder.name.setText(mylist.getName());
        holder.email.setText(mylist.getEmail());
        holder.phone.setText(mylist.getPhone());
        Picasso.get().load(mylist.getImage()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {

        int arr = 0;

        try {
            if (list.size() == 0) {

                arr = 0;

            } else {

                arr = list.size();
            }


        } catch (Exception e) {


        }

        return arr;

    }

    class MyHoder extends RecyclerView.ViewHolder {
        TextView name, email,phone;
        ImageView imageView;


        public MyHoder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.vname);
            email = itemView.findViewById(R.id.vemail);
            phone =itemView.findViewById(R.id.vphone);
            imageView = itemView.findViewById(R.id.imageView);

        }
    }

}
