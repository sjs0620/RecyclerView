package com.example.edu.recyclerview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TooManyListenersException;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MyViewHolder>
    {
    ArrayList<HashMap<String, Object>> arrayList = null;

    public RecyclerAdapter(ArrayList<HashMap<String, Object>> arrayList){
        this.arrayList = new ArrayList<HashMap<String, Object>>();
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflate = LayoutInflater.from(parent.getContext());
        View view = inflate.inflate(R.layout.item_cardlayout, parent, false);
        MyViewHolder viewHolder = new MyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        HashMap<String, Object> hashMap = arrayList.get(position);
        holder.itemTitle.setText((String)hashMap.get("title"));
        holder.itemTitle.setText((String)hashMap.get("detail"));
        holder.itemImage.setImageResource((Integer) hashMap.get("image"));
        holder.itemCount.setText("0");

        holder.itemImage.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),
                        ((TextView)holder.itemTitle).getText().toString(),
                        Toast.LENGTH_SHORT).show();
                Integer count = Integer.parseInt(((TextView)holder.itemCount).getText().toString())
                        +1;
                ((TextView)holder.itemCount).setText(count.toString());
            }
        });
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    class  MyViewHolder extends RecyclerView.ViewHolder {

        public ImageView itemImage;
        public TextView itemTitle;
        public TextView itemDetail;
        public TextView itemCount;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            itemImage = (ImageView)itemView.findViewById(R.id.item_image);
            itemTitle = (TextView)itemView.findViewById(R.id.item_title);
            itemDetail = (TextView)itemView.findViewById(R.id.item_detail);
            itemCount = (TextView)itemView.findViewById(R.id.item_count);
        }
    }


}


