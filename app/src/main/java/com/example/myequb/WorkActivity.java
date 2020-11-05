package com.example.myequb;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


public class WorkActivity extends AppCompatActivity {
    Intent intent;
    String name;
    String phone;
ImageView call;
    ImageView text;
    ImageView back;
    ImageView emailme;
    TextView phoneHolder;

    TextView emailHolder;
    String email;
    DatabaseHelper databaseHelper;
    String money;
    ImageView delete;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TextView nameHolder;
        setContentView(R.layout.activity_work);
//        Toolbar toolbar = findViewById(R.id.tooltoolbar);
//        setSupportActionBar(toolbar);
        intent = getIntent();
nameHolder=findViewById(R.id.nameShow);
        name = intent.getStringExtra("name");
        phone = intent.getStringExtra("phone");
        email = intent.getStringExtra("email");
        delete=findViewById(R.id.remove);
//        back=findViewById(R.id.back);
        phoneHolder = findViewById(R.id.phone);
        call=findViewById(R.id.callMe);
        text=findViewById(R.id.messageMe);
        emailme=findViewById(R.id.emailMe);
        phoneHolder.setText(phone);
        nameHolder.setText(name);
        emailHolder = findViewById(R.id.email);
        emailHolder.setText(email);
        databaseHelper = new DatabaseHelper(this);

        phone = intent.getStringExtra("phone");
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteName(email);
            }
        });
//        back.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                intent = new Intent(WorkActivity.this, Members
//                        .class);
//                startActivity(intent);
//            }
//        });

call.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", phone, null));
        startActivity(intent);
    }
});
text.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

    }
});
emailme.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"+email));
        startActivity(emailIntent);
    }
});
    }
}

