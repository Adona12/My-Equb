package com.example.myequb;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.ArrayList;

public class TestAddMembers extends AppCompatActivity {

    private static final String TAG = "MainActivity";
RecyclerView.Adapter adapter;
    DatabaseHelper mDatabaseHelper;
    private Button btnAdd, btnViewData;
    private EditText name;
    int size2=0;
    Intent intent;
    private EditText phone;
    private EditText email;
    ArrayList<Person> memberDataList;
    ImageView back;
    Person person;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_add_members);
        name = (EditText) findViewById(R.id.NameText);
        phone = (EditText) findViewById(R.id.PhoneText);
        email = (EditText) findViewById(R.id.EmailText);
back=findViewById(R.id.back1);
        btnAdd = (Button) findViewById(R.id.btnAdd);

        mDatabaseHelper = new DatabaseHelper(this);
        Cursor data = mDatabaseHelper.getData();
        memberDataList = new ArrayList<>();
        while(data.moveToNext()){
            //get the value from the database in column 1
            //then add it to the ArrayList
            person=new Person(data.getString(1),data.getString(2),data.getString(3),R.drawable.ic_audiotrack_black_24dp,data.getInt(4),data.getInt(5),data.getInt(6));
            memberDataList.add(person);
        }
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(TestAddMembers.this, Members
                        .class);
                startActivity(intent);
            }
        });
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newEntry = name.getText().toString();
                String newEntry1 = phone.getText().toString();
                String newEntry2= email.getText().toString();

                if (name.length() != 0 && phone.length()!=0 && email.length()!=0) {
                    AddData(newEntry,newEntry1,newEntry2);
                    name.setText("");
                    phone.setText("");
               email.setText("");
                     } else {
                    toastMessage("You must put something in the text field!");
                }

            }
        });

    }

    public void AddData(String newEntry,String newEntry1,String newEntry2) {
        memberDataList.add(new Person(newEntry,newEntry1,newEntry2,0,0,0));
        boolean insertData = mDatabaseHelper.addData(newEntry,newEntry1,newEntry2,0,0,0);

        adapter=new memberAdapter(this,memberDataList);
        if (insertData) {
            toastMessage("Data Successfully Inserted!");
            adapter.notifyItemInserted(memberDataList.size());

        } else {
            toastMessage("Something went wrong");
        }
    }

    /**
     * customizable toast
     * @param message
     */
    private void toastMessage(String message){
        Toast.makeText(this,message, Toast.LENGTH_SHORT).show();
    }
}
