package com.example.edu.recyclerview;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
    private SQLiteDatabase mdb;
    MyDBOpenHelper dbOpenHelper;

    public RecyclerAdapter(ArrayList<HashMap<String, Object>> arrayList){
        this.arrayList = new ArrayList<HashMap<String, Object>>();
        this.arrayList = arrayList;
    }

    public RecyclerAdapter(SQLiteDatabase db){
        this.mdb = db;
        String query = "select * from count_table;";
        Cursor cursor = mdb.rawQuery(query,null);
        ArrayList<HashMap<String,Object>> arrayListTemp = new ArrayList<>();
        HashMap<String,Object> hashMap = null;
        int rowcnt = 0;


        int[] image={R.drawable.android_image_1,R.drawable.android_image_2,R.drawable.android_image_3,R.drawable.android_image_4,
                R.drawable.android_image_5,R.drawable.android_image_6,R.drawable.android_image_7,R.drawable.android_image_8};

        while (cursor.moveToNext()){
            rowcnt++;
            hashMap.put("title","Card "+ String.valueOf(rowcnt) );
            hashMap = new HashMap<String,Object>();
            hashMap.put("count",cursor.getInt(0));
            hashMap.put("detail",cursor.getString(1));
            hashMap.put("image",image[rowcnt%8]);
            arrayListTemp.add(hashMap);
        }
        this.arrayList = arrayListTemp;
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
        holder.itemCount.setText((Integer) hashMap.get("count"));
        holder.itemTitle.setText((String)hashMap.get("title"));
        holder.itemDetail.setText((String)hashMap.get("detail"));
        holder.itemImage.setImageResource((Integer) hashMap.get("image"));

        holder.itemImage.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),
                        ((TextView)holder.itemTitle).getText().toString(),
                        Toast.LENGTH_SHORT).show();
                Integer count = Integer.parseInt(((TextView)holder.itemCount).getText().toString())
                        +1;
                ((TextView)holder.itemCount).setText(count.toString());
                dbOpenHelper.updateRecord(mdb,count, ((TextView)holder.itemDetail).getText().toString());

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


