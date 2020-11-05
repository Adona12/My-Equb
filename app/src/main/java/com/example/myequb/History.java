package com.example.myequb;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class History extends AppCompatActivity {
    ArrayList<history_holder> historyDataList;
    private RecyclerView historyRecyclerView;
    history_holder history;
DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
        historyDataList = new ArrayList<>();
        mDatabaseHelper = new DatabaseHelper(this);
        Cursor data1 = mDatabaseHelper.getDataHistory();
        historyDataList = new ArrayList<>();
        while (data1.moveToNext()) {
            //get the value from the database in column 1
            //then add it to the ArrayList
            history = new history_holder( data1.getString(2), data1.getString(3));
            historyDataList.add(history);
        }

        historyRecyclerView=findViewById(R.id.historyRecyclerView);
        final HistoryAdapter adapter = new HistoryAdapter(this, historyDataList);
        historyRecyclerView.setAdapter(adapter);
       historyRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
