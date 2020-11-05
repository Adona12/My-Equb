package com.example.myequb;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by User on 2/28/2017.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";
    private static final String TABLE_NAME2 = "History_table8";
    private static final String COLh1 = "ID";
    private static final String COLh2 = "Cycle";
    private static final String COLh3 = "Date";
    private static final String COLh4 = "Winner";

    private static final String TABLE_NAME = "member_table8";
    private static final String COL1 = "ID";
    private static final String COL2 = "name";
    private static final String COL3 = "Phone";
    private static final String COL4 = "Email";
    private static final String COL5 = "Amount";
    private static final String COL6 = "Status";
    private static final String COL7 = "removed";





    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL2 +" TEXT," +
                COL3 +" TEXT," +
                COL4 +" TEXT,"+
                COL5 +" INTEGER,"+
                COL6 +" INTEGER,"+
                COL7 +" INTEGER)";
        db.execSQL(createTable);
        String createTable2 = "CREATE TABLE " + TABLE_NAME2 + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLh2 +" TEXT," +
                COLh3 +" TEXT," +
                COLh4 +" TEXT)";
        db.execSQL(createTable2);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
        onCreate(db);
    }

    public boolean addData(String Name,String email,String phone,int amount,int stat,int remove) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL2, Name);
        contentValues.put(COL3, email);
        contentValues.put(COL4, phone);
        contentValues.put(COL5, amount);
        contentValues.put(COL6, stat);
        contentValues.put(COL7, remove);



        Log.d(TAG, "addData: Adding " + Name + " to " + TABLE_NAME);

        long result = db.insert(TABLE_NAME, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public boolean addDataHistory(String cycle) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLh2, cycle);
        contentValues.put(COLh3,"pending");
        contentValues.put(COLh4,"pending");





        Log.d(TAG, "addData: Adding " +cycle + " to " + TABLE_NAME2);

        long result = db.insert(TABLE_NAME2, null, contentValues);

        //if date as inserted incorrectly it will return -1
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }
    public void addDateHistory(String Date, String id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME2 + " SET " + COLh3 +
                " = '" + Date + "' WHERE " + COLh3 + " = '" + id + "'";

        Log.d(TAG, "updateDate: query: " + query);
        Log.d(TAG, "updateDate: Setting name to " + Date);
        db.execSQL(query);

    }
    public void addWinnerHistory( String id,String Winner){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME2 + " SET " + COLh4 +
                " = '" + Winner + "' WHERE " + COLh4 + " = '" + id + "'";

        Log.d(TAG, "updateWinner: query: " + query);
        Log.d(TAG, "updateWinenr: Setting name to " + Winner);
        db.execSQL(query);

    }

    /**
     * Returns all the data from database
     * @return
     */
    public Cursor getData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor getDataSelection(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME + " WHERE " + COL6 + " != '" + 0 + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor getDataHistory(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME2;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor getPaidData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME +
                " WHERE " + COL5 + " != '" + 0 + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor getUnpaidData(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME +
                " WHERE " + COL5 + " = '" + 0 + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor getNotWinners(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME +
                " WHERE " + COL7 + " = '" + 0 + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public Cursor getWinners(){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME +
                " WHERE " + COL7 + " != '" + 0 + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    public void updateAmount(int newAmount, String identfier){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL5 +
                " = '" + newAmount + "' WHERE " + COL3 + " = '" + identfier + "'";

        Log.d(TAG, "updateAmount: query: " + query);
        Log.d(TAG, "updateAmount: Setting name to " + newAmount);
        db.execSQL(query);

    }
    public void updateEverybodyWin(int newAmount){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL7 +
                " = '" + newAmount + "' WHERE " + COL7 + " != '" + 0 + "'";

        Log.d(TAG, "updateAmount: query: " + query);
        Log.d(TAG, "updateAmount: Setting name to " + newAmount);
        db.execSQL(query);

    }

    public void updateEverybodyAmount(int newAmount){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL5 +
                " = '" + newAmount + "' WHERE " + COL5 + " != '" + 0 + "'";

        Log.d(TAG, "updateAmount: query: " + query);
        Log.d(TAG, "updateAmount: Setting name to " + newAmount);
        db.execSQL(query);

    }

    public void updateWin(int newValue, String id) {

        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL7 +
                " = '" + newValue + "' WHERE " + COL4 + " = '" + id + "'";

        Log.d(TAG, "updateAmount: query: " + query);
        Log.d(TAG, "updateAmount: Setting name to " + newValue);
        db.execSQL(query);

    }
    /**
     * Returns only the ID that matches the name passed in
     * @param phone
     * @return
     */
    public Cursor getItemID(String phone){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL1 + " FROM " + TABLE_NAME +
                " WHERE " + COL3 + " = '" + phone + "'";
        Cursor data = db.rawQuery(query, null);
        return data;
    }

    /**
     * Updates the name field
     * @param newName
     * @param id
     * @param oldName
     */
    public void updateName(String newName, int id, String oldName){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL2 +
                " = '" + newName + "' WHERE " + COL1 + " = '" + id + "'" +
                " AND " + COL2 + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }

    /**
     * Delete from database

     * @param email
     */
    public void deleteName( String email){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL4 + " = '" + email + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + email + " from database.");
        db.execSQL(query);

}
    public void resetMember( String check) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL4 + " != '" + check + "'";
        db.execSQL(query);
    }
    public void resetHistory(String check ) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME2 + " WHERE "
                + COLh2 + " != '" + check + "'";
        db.execSQL(query);
    }

}
























