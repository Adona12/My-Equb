package com.example.myequb;

import android.database.Cursor;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;



public class Draw extends AppCompatActivity {

    TextView tvWinner;
    TextView tvPrevious;
    Person winner;
    history_holder history;

ConstraintLayout holder;
    history_holder lastHistory;
    history_holder lastCycle;
    Random random;

    ArrayList<history_holder> memberDataList;
    ArrayList<history_holder> HistoryDataList;
    ArrayList<Person> mMemberDataList;
    Person  person;
    DatabaseHelper mDatabaseHelper;
    Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw);
        tvWinner = (TextView) findViewById(R.id.textViewSum);
        tvPrevious = (TextView) findViewById(R.id.previous);
        holder = findViewById(R.id.LinearHolder);
        button=findViewById(R.id.buttonspin);
        mDatabaseHelper = new DatabaseHelper(this);
        Cursor data = mDatabaseHelper.getDataHistory();
        memberDataList = new ArrayList<>();
        HistoryDataList = new ArrayList<>();
        while (data.moveToNext()) {
            //get the value from the database in column 1
            //then add it to the ArrayList
            history = new history_holder(data.getString(2), data.getString(3));
            lastCycle=new history_holder(data.getString(1));
            memberDataList.add(history);
            HistoryDataList.add(lastCycle);


        }
        if (memberDataList.size() != 0) {
            lastHistory = memberDataList.get(memberDataList.size() - 1);
            tvPrevious.setText(lastHistory.getWinner());
        } else {
            tvPrevious.setText("No history");
        }
        random = new Random();
        //mp3Player   = MediaPlayer.create(this, R.raw.beep);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                processGame();
            }
        });
    }

    public void processGame() {
        Cursor data1 = mDatabaseHelper.getNotWinners();
        mMemberDataList = new ArrayList<>();
        while (data1.moveToNext()) {
            //get the value from the database in column 1
            //then add it to the ArrayList
            person = new Person(data1.getString(1), data1.getString(2), data1.getString(3), R.drawable.ic_audiotrack_black_24dp, data1.getInt(4), data1.getInt(5), data1.getInt(6));
            mMemberDataList.add(person);
        }
        if (mMemberDataList.size() == 0) {
            Toast.makeText(this, "everybody has won", Toast.LENGTH_SHORT).show();
        } else if (mMemberDataList.size() == 1) {
            winner = mMemberDataList.get(0);


            String phone = winner.getPhone_num();
            int id2 = 20;
            if (mDatabaseHelper.getItemID(phone) == null) {
                Log.d("check", "null");
            } else {
                Cursor data2 = mDatabaseHelper.getItemID(phone);
                int id = data2.getColumnIndex("ID");
                Log.d("index", "" + id);
                while (data2.moveToNext()) {
                    //get the value from the database in column 1
                    //then add it to the ArrayList
                    id2 = data2.getInt(0);

                }

                Log.d("check", "" + id2);



            }
            tvWinner.setText(winner.getName());
            mDatabaseHelper.updateWin(1, winner.getEmail());

            Log.d("Adona","please");

            Log.d("help","first");
            mDatabaseHelper.updateEverybodyAmount(0);
            Log.d("help","first");
            holder.setBackgroundResource(R.drawable.gradient2);
            SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
            Date todayDate = new Date();
            String thisDate = currentDate.format(todayDate);
            lastCycle=HistoryDataList.get(HistoryDataList.size()-1);

            mDatabaseHelper.addDateHistory(thisDate,"pending");
            mDatabaseHelper.addWinnerHistory("pending",winner.getName());
            addData();
        } else {
            Log.d("size", "" + mMemberDataList.size());

            //Person winner = mMemberDataList.get(0);
            winner = mMemberDataList.get(new Random().nextInt(mMemberDataList.size()));


            String phone = winner.getPhone_num();
            int id2 = 20;
            if (mDatabaseHelper.getItemID(phone) == null) {
                Log.d("check", "null");
            } else {
                Cursor data2 = mDatabaseHelper.getItemID(phone);
                int id = data2.getColumnIndex("ID");
                Log.d("index", "" + id);
                while (data2.moveToNext()) {
                    //get the value from the database in column 1
                    //then add it to the ArrayList
                    id2 = data2.getInt(0);
                    Log.d("Adona","work");

                    mDatabaseHelper.updateWin(1, winner.getEmail());
                    Log.d("Adona","please");

                    Log.d("help","first");
                    mDatabaseHelper.updateEverybodyAmount(0);
                    Log.d("help","first");
                    holder.setBackgroundResource(R.drawable.gradient2);
                    SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
                    Date todayDate = new Date();
                    String thisDate = currentDate.format(todayDate);
                    lastCycle=HistoryDataList.get(HistoryDataList.size()-1);
                    mDatabaseHelper.addDateHistory(thisDate,lastCycle.getCycle());
                    mDatabaseHelper.addWinnerHistory(lastCycle.getCycle(),winner.getName());

                    addData();
                }

                Log.d("check", "" + id2);


            }


        }


        // ... then write the results to the UI.

        button.setEnabled(false);    // Disable the 'Spin Roll'-Button.

        //  mp3Player.start();      // Play the MP3-file.
    }

    public void recreateGame(View v) {
        finish();
        startActivity(getIntent());
    }
    public void addData(){

        ArrayList<Person> MemberDataList;
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 30);  //
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        String output = sdf1.format(c.getTime());
        Cursor data1 = mDatabaseHelper.getNotWinners();
        MemberDataList = new ArrayList<>();
        while (data1.moveToNext()) {
            //get the value from the database in column 1
            //then add it to the ArrayList
            person = new Person(data1.getString(1), data1.getString(2), data1.getString(3), R.drawable.ic_audiotrack_black_24dp, data1.getInt(4), data1.getInt(5), data1.getInt(6));
            MemberDataList.add(person);
        }

        if(MemberDataList.size()>0) {

            mDatabaseHelper.addDataHistory(output);
        }
    }
}