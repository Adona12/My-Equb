package com.example.myequb;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;

public class Settings extends AppCompatActivity {
Button reset;
DatabaseHelper mDatabase;
    private static Locale myLocale;
    private static final String Locale_Preference = "Locale Preference";
    private static final String Locale_KeyValue = "Saved Locale";
    private static SharedPreferences sharedPreferences;
    private static SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
//        loadLocale();
        reset = findViewById(R.id.reset);
  mDatabase = new DatabaseHelper(this);
        sharedPreferences = getSharedPreferences(Locale_Preference, Activity.MODE_PRIVATE);
        editor = sharedPreferences.edit();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

       mDatabase.resetMember("d");
//
                mDatabase.resetHistory("one");
            }
        });

        final RadioGroup radio = (RadioGroup) findViewById(R.id.cow);

        radio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
String lang="en";
                View radioButton = radio.findViewById(checkedId);
                int index = radio.indexOfChild(radioButton);

                // Add logic here

                switch (index) {
                    case 0: // first button
//                        LocaleHelper.setLocale(Settings.this, "fr");
//                        Toast.makeText(Settings.this,"to amharic", Toast.LENGTH_SHORT).show();
////                        recreate();
                        String languageToLoad  = "fr"; // your language
                        Locale locale = new Locale(languageToLoad);

                        Locale.setDefault(locale);
                        Configuration config = new Configuration();
                        config.locale = locale;
                        getBaseContext().getResources().updateConfiguration(config,
                                getBaseContext().getResources().getDisplayMetrics());

//                                lang="fr";
                        break;
                    case 1: // secondbutton
//                        Toast.makeText(Settings.this,"to english", Toast.LENGTH_SHORT).show();
//                        LocaleHelper.setLocale(Settings.this, "en");
//
//                        recreate();
                        String language  = "en"; // your language
                        Locale locale1 = new Locale(language);

                        Locale.setDefault(locale1);
                        Configuration config1 = new Configuration();
                        config1.locale = locale1;
                        getBaseContext().getResources().updateConfiguration(config1,
                                getBaseContext().getResources().getDisplayMetrics());

                        break;
                }
                changeLocale(lang);//Change Locale on selection basis

            }
        });
    }
    public void check(){

        Toast.makeText(Settings.this,"to nbnnnnnnnnnnnnnnenglish", Toast.LENGTH_SHORT).show();
        mDatabase = new DatabaseHelper(this);
        mDatabase.resetMember("d");

        mDatabase.resetHistory("one");
    }
    public void changeLocale(String lang) {
        if (lang.equalsIgnoreCase(""))
            return;
        myLocale = new Locale(lang);//Set Selected Locale
        saveLocale(lang);//Save the selected locale
        Locale.setDefault(myLocale);//set new locale as default
        Configuration config = new Configuration();//get Configuration
        config.locale = myLocale;//set config locale as selected locale
        getBaseContext().getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());//Update the config
    }

    //Save locale method in preferences
    public void saveLocale(String lang) {
        editor.putString(Locale_KeyValue, lang);
        editor.commit();
    }

    //Get locale method in preferences
    public void loadLocale() {
        String language = sharedPreferences.getString(Locale_KeyValue, "en");
        changeLocale(language);
    }


}
