package com.example.myequb;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Members extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    Intent intent;
    ArrayList<Person> memberDataList;
    ArrayList<Person> winnerDataList;
    Person person;

    DatabaseHelper mDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_members);
//        Toolbar toolbar = findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        mDatabaseHelper = new DatabaseHelper(this);
        Cursor data = mDatabaseHelper.getData();
        memberDataList = new ArrayList<>();
        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            person=new Person(data.getString(1),data.getString(2),data.getString(3),R.drawable.ic_audiotrack_black_24dp,data.getInt(4),data.getInt(5),data.getInt(6));
            memberDataList.add(person);
        }
        Cursor data1 = mDatabaseHelper.getWinners();
        winnerDataList = new ArrayList<>();
        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            person=new Person(data1.getString(1),data1.getString(2),data1.getString(3),R.drawable.ic_audiotrack_black_24dp,data1.getInt(4),data1.getInt(5),data1.getInt(6));
            winnerDataList.add(person);
        }

        FloatingActionButton fab = findViewById(R.id.fab);
        mRecyclerView=findViewById(R.id.recyclerview_main);
        int postion = memberDataList.size();
        final memberAdapter adapter = new memberAdapter(this, memberDataList);
        mRecyclerView.setAdapter(adapter);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                    check();


            }
        });
    }
    public void check(){

        if(winnerDataList.size()==0) {
            Intent intent = new Intent(this, TestAddMembers.class);
            startActivity(intent);
        }
    else{
            Toast.makeText(this, R.string.invalid, Toast.LENGTH_SHORT).show();
        }

    }


}
