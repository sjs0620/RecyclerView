package com.example.edu.recyclerview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.HashMap;

public class RecyclerViewActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler_view);

        ArrayList<HashMap<String,Object>> arrayList =
                new ArrayList<HashMap<String, Object>>();
        HashMap<String, Object> hashMap=null;
        hashMap = new HashMap<String, Object>();

        //Add Data
        hashMap = new HashMap<String, Object>();
        hashMap.put("title","Chapter One");
        hashMap.put("detail","Item one details");
        hashMap.put("image",R.drawable.android_image_1);
        arrayList.add(hashMap);

        hashMap = new HashMap<String, Object>();
        hashMap.put("title","Chapter Two");
        hashMap.put("detail","Item two details");
        hashMap.put("image",R.drawable.android_image_2);
        arrayList.add(hashMap);
        //Add Data
        hashMap = new HashMap<String, Object>();
        hashMap.put("title","Chapter Three");
        hashMap.put("detail","Item three details");
        hashMap.put("image",R.drawable.android_image_3);
        arrayList.add(hashMap);

        hashMap = new HashMap<String, Object>();
        hashMap.put("title","Chapter Four");
        hashMap.put("detail","Item four details");
        hashMap.put("image",R.drawable.android_image_4);
        arrayList.add(hashMap);

        recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
 //       layoutManager = new LinearLayoutManager(this);
        layoutManager = new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter(arrayList);
        recyclerView.setAdapter(adapter);
    }
}
