package com.example.bellezza;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class Dashboard extends AppCompatActivity {

    String s1[], s2[] , s3[];
    int images[] ={R.drawable.hair,R.drawable.foot,R.drawable.lipstick,R.drawable.mascar};

    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        //add activity
        Button next =(Button)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 =new Intent(Dashboard.this, AdminLogin.class);
                startActivity(intent4);

                //Add Toast
                Toast.makeText(Dashboard.this, "Loading...", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView = findViewById(R.id.recycleView);

        s1=getResources().getStringArray(R.array.face_products);
        s2=getResources().getStringArray(R.array.Brand);
        s3=getResources().getStringArray(R.array.Price);



        MyAdapter myAdapter = new MyAdapter(this,s1,s2,s3,images);
        recyclerView.setAdapter(myAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));



    }

}