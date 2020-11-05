package com.example.myequb;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;
import android.widget.Toolbar;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private android.support.v7.widget.CardView status;
    private android.support.v7.widget.CardView history;
    private android.support.v7.widget.CardView member1;
    private android.support.v7.widget.CardView settings;
    ArrayList<Person> memberDataList;

    Person person;
    private android.support.v7.widget.CardView draw;
    Intent intent;
    ArrayList<Person > WinnerDataList;
    ArrayList<Person > AllMember;
    ArrayList<Person> notPaidDataList;
    ArrayList<Person> Existance;
    ArrayList<history_holder> historyDataList;
    history_holder historyOne;
    DatabaseHelper mDatabaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String languageToLoad  = "fr"; // your language
        Locale locale = new Locale(languageToLoad);

        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getBaseContext().getResources().updateConfiguration(config,
                getBaseContext().getResources().getDisplayMetrics());
        setContentView(R.layout.activity_main);
        mDatabaseHelper = new DatabaseHelper(this);
        checkStart();
        status=findViewById(R.id.status);
        history=findViewById(R.id.history);
        member1=findViewById(R.id.member);
        settings=findViewById(R.id.setting);
        draw=findViewById(R.id.draw);
        draw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
draw();
            }
        });
        settings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                settings();
            }
        });

        member1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                member();
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                history();
            }
        });
        status.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                check();
            }
        });
    }
    public void check(){
        intent =new Intent(this,Status.class);
        startActivity(intent);
    }
    public void history(){
        intent =new Intent(this,History.class);
        startActivity(intent);
    }
    public void member(){
        intent =new Intent(this,Members
                .class);
        startActivity(intent);
    }
    public void settings(){
        intent =new Intent(this,Settings.class);
        startActivity(intent);
    }
    public void draw(){

        Cursor notWinnerr = mDatabaseHelper.getNotWinners();
        WinnerDataList = new ArrayList<>();
        while(notWinnerr.moveToNext()){
            person=new Person(notWinnerr.getString(1),notWinnerr.getString(2),notWinnerr.getString(3),R.drawable.ic_audiotrack_black_24dp,notWinnerr.getInt(4),notWinnerr.getInt(5),notWinnerr.getInt(6));
            WinnerDataList.add(person);
        }
        Cursor paid = mDatabaseHelper.getUnpaidData();

        notPaidDataList= new ArrayList<>();
        while(paid.moveToNext()){
            person=new Person(paid.getString(1),paid.getString(2),paid.getString(3),R.drawable.ic_audiotrack_black_24dp,paid.getInt(4),paid.getInt(5),paid.getInt(6));
           notPaidDataList.add(person);
        }
        Existance=new ArrayList<>();
        Cursor exist = mDatabaseHelper.getData();

        while (exist.moveToNext()) {
            //get the value from the database in column 1
            //then add it to the ArrayList
            person=new Person(exist.getString(1),exist.getString(2),exist.getString(3),R.drawable.ic_audiotrack_black_24dp,exist.getInt(4),exist.getInt(5),exist.getInt(6));
           Existance.add(person);
        }
        if(!(checkStart()) ){

        }
        else{
        if(Existance.size()==0){
            Toast.makeText(this,getString(R.string.noMember), Toast.LENGTH_SHORT).show();
        }
        else{

           if(notPaidDataList.size()>0){
            Toast.makeText(this,getString(R.string.Everbody), Toast.LENGTH_SHORT).show();
        }
        else {

            intent = new Intent(this, Draw
                    .class);
            startActivity(intent);
        }
    }}}

    public void add(){
        final AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
        alertDialogBuilder.setMessage(R.string.start);
                alertDialogBuilder.setPositiveButton(R.string.yes,

                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface arg0, int arg1) {
                                addData();
                                mDatabaseHelper.updateEverybodyWin(0);
                                mDatabaseHelper.updateEverybodyAmount(0);
                            }
                        });

        alertDialogBuilder.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }
        });

        AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }
    public void addData(){
        Calendar c = Calendar.getInstance();
        c.add(Calendar.DATE, 30);  //
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd/MM/yyyy");
        String output = sdf1.format(c.getTime());

        mDatabaseHelper.addDataHistory(output);
    }
    public boolean  checkStart(){
        Person people;
        boolean check=true;
        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");

        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);

        Cursor data5 = mDatabaseHelper.getNotWinners();
        memberDataList = new ArrayList<>();
        AllMember=new ArrayList<>();
        while(data5.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            people=new Person(data5.getString(1),data5.getString(2),data5.getString(3),R.drawable.ic_audiotrack_black_24dp,data5.getInt(4),data5.getInt(5),data5.getInt(6));
            memberDataList.add(people);
        }
        Cursor data1 = mDatabaseHelper.getDataHistory();
        historyDataList = new ArrayList<>();
        while (data1.moveToNext()) {
            //get the value from the database in column 1
            //then add it to the ArrayList
            historyOne = new history_holder( data1.getString(2), data1.getString(3));
            historyDataList.add(historyOne);
        }


        Cursor member = mDatabaseHelper.getData();

        while (member.moveToNext()) {
            //get the value from the database in column 1
            //then add it to the ArrayList
            person=new Person(member.getString(1),member.getString(2),member.getString(3),R.drawable.ic_audiotrack_black_24dp,member.getInt(4),member.getInt(5),member.getInt(6));
            AllMember.add(person);
        }
        Log.d("historySize",historyDataList.size()+"");

        Log.d("memberdata",AllMember.size()+"");
        Log.d("member",memberDataList.size()+"");
        if (historyDataList.size()==0) {
          add();
          return false;
        }
        else{
            Log.d("history",historyDataList.get(historyDataList.size()-1).getWinner());
        if(!(historyDataList.get(historyDataList.size() - 1).getWinner() .equals("pending")))  {

//            if(memberDataList.size()==0){
//                add();
//                return false;
//            }


add();
return false;
        }}


return check;
    }
}



