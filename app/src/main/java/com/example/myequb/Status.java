package com.example.myequb;

import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;



import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Locale;
import java.util.concurrent.TimeUnit;

import mobi.gspd.segmentedbarview.Segment;
import mobi.gspd.segmentedbarview.SegmentedBarView;



public class Status extends AppCompatActivity {
    ArrayList<Person> statusMemberDataList;
    ArrayList<history_holder> historyDataList;
    DatabaseHelper mDatabaseHelper;
    TextView days;
    history_holder lastWinner;
    ArrayList<history_holder> winners;
    TextView amount;
    String start="#80DB1957";
    String pend="#80DB1957";
    String finish="#80DB1957";
    TextView winner;
    history_holder lastCycle;
    int total=0;
    Person person;
    private RecyclerView statusRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status);
        statusMemberDataList = new ArrayList<>();
        days=findViewById(R.id.days);
        SegmentedBarView seekBar=findViewById(R.id.listener);


        mDatabaseHelper = new DatabaseHelper(this);
        amount=findViewById(R.id.total);
        winner=findViewById(R.id.winner);
        Cursor data = mDatabaseHelper.getData();
        Cursor data1 = mDatabaseHelper.getDataHistory();
        historyDataList = new ArrayList<>();
        winners=new ArrayList<>();
        while (data1.moveToNext()) {
            //get the value from the database in column 1
            //then add it to the ArrayList
            lastWinner=new history_holder(data1.getString(2),data1.getString(3));
            lastCycle=new history_holder(data1.getString(1));
            winners.add(lastWinner);
            historyDataList.add(lastCycle);


        }
        if(winners.size()>1) {
            winner.setText(winners.get(winners.size() - 2).getWinner());
        }
        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            person=new Person(data.getString(1),data.getString(2),data.getString(3),R.drawable.ic_audiotrack_black_24dp,data.getInt(4),data.getInt(5),data.getInt(6));
            statusMemberDataList.add(person);
            total+=person.getAmount();

        }
        Log.d("total",total+"");
        amount.setText(total+"");

        SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
        Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);
        Status mine=new Status();
        if(historyDataList.size()!=0) {
            float check = mine.Daybetween(thisDate, historyDataList.get(historyDataList.size() - 1).getCycle(), "dd/MM/yyyy");
            if (check == 0) {
                float negative = mine.Daybetween(historyDataList.get(historyDataList.size() - 1).getCycle(), "dd/MM/yyyy", thisDate);
                Log.d("days", check + "");

                days.setText(check + getString(R.string.elapsed));
            } else {
                Log.d("days", check + "");

                days.setText(check + getString(R.string.left));
            }

        }
        else{
            days.setText("Not started");
        }
        if (statusMemberDataList.size()==0 && winners.size()==0){
            start="#DB1957";
        }
        else{
            if(winners.get(winners.size()-1).getWinner().equals("pending"))
            {
                pend="#DB1957";
            }
            else{
                finish="#DB1957";
            }
        }
        List<Segment> segments = new ArrayList<>();
        Segment segment = new Segment("Start","Start", Color.parseColor(start));

        segments.add(segment);
        Segment segment2 = new Segment("Pending","Pending" ,Color.parseColor(pend));
        segments.add(segment2);
        Segment segment3 = new Segment("Finished","Finished", Color.parseColor(finish));
        segments.add(segment3);
        seekBar.setShowDescriptionText(false);
        seekBar.setSegments(segments);
        statusRecyclerView=findViewById(R.id.statusRecyclerView);
        final statusAdapter adapter = new statusAdapter(this, statusMemberDataList);
        statusRecyclerView.setAdapter(adapter);
        statusRecyclerView.setLayoutManager(new LinearLayoutManager(this));
    }


    public long Daybetween(String date1,String date2,String pattern)
    {
        Log.d("date1",date1);
        Log.d("date2",date2);
        SimpleDateFormat sdf = new SimpleDateFormat(pattern,Locale.ENGLISH);
        Date Date1 = null,

                Date2 = null;
        try{
            Date1 = sdf.parse(date1);
            Date2 = sdf.parse(date2);
            Log.d("date3",Date1.toString());
            Log.d("date4",Date2.toString());
        }catch(Exception e)
        {
            e.printStackTrace();
        }
        return (Date2.getTime() - Date1.getTime())/(24*60*60*1000);
    }



    public float Daybetween(String dateBeforeString,String dateAfterString){
    float daysBetween=0;


        SimpleDateFormat myFormat = new SimpleDateFormat("dd MM yyyy");



        try {
            Date dateBefore = myFormat.parse(dateBeforeString);
            Date dateAfter = myFormat.parse(dateAfterString);
            long difference = dateAfter.getTime() - dateBefore.getTime();
            daysBetween = (difference / (1000*60*60*24));
            /* You can also convert the milliseconds to days using this method
             * float daysBetween =
             *         TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS)
             */
            System.out.println("Number of Days between dates: "+daysBetween);
        } catch (Exception e) {
            e.printStackTrace();
        }
    return  daysBetween;

    }


}
