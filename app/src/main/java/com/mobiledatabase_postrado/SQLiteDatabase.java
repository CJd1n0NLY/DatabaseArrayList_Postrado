package com.mobiledatabase_postrado;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class SQLiteDatabase extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "records.db";
    public static final String PROFILE = "profile";
    public static final String PROF_ID = "id",PROF_FNAME = "fname",PROF_MNAME = "mname",PROF_LNAME = "lname";
    public static String sql = "";
    public static Cursor rs;
    public ContentValues Values;
    public ArrayList<String> Records;
    public ArrayList<String> FullNames;
    public SQLiteDatabase(@Nullable Context context) {
        super(context,DATABASE_NAME,null,1);
        FullNames = new ArrayList<>();
        addFullName();

    }

    @Override
    public void onCreate(android.database.sqlite.SQLiteDatabase conn) {
        sql = "CREATE TABLE " + PROFILE + "(" + PROF_ID + " Integer PRIMARY KEY AUTOINCREMENT, " + PROF_FNAME + " text, " + PROF_MNAME + " text, " + PROF_LNAME + " text)";
        conn.execSQL(sql);
    }

    @Override
    public void onUpgrade(android.database.sqlite.SQLiteDatabase conn, int OldVersion, int NewVersion) {
        sql = "DROP TABLE IF EXIST " + PROFILE;
        conn.execSQL(sql);
        onCreate(conn);

    }

    public boolean AddRecords(String fname, String mname, String lname){
        String fullName = fname + " " + mname + " " + lname;
        if (FullNames.contains(fullName)) {
            return false;
        }

        android.database.sqlite.SQLiteDatabase conn = this.getWritableDatabase();

        Values = new ContentValues();
        Values.put(PROF_FNAME, fname);
        Values.put(PROF_MNAME, mname);
        Values.put(PROF_LNAME, lname);


        long result = conn.insert(PROFILE, null, Values);
        if (result != -1) {
            FullNames.add(fullName);
            return true;
        }
        return false;

    }

    @SuppressLint("Range")
    public ArrayList<String> GetAllData(){
        android.database.sqlite.SQLiteDatabase conn = this.getReadableDatabase();
        Records = new ArrayList<String>();
        sql = "SELECT * FROM " + PROFILE;
        rs = conn.rawQuery(sql,null);
        rs.moveToFirst();

        while(rs.isAfterLast() == false){
            Records.add(rs.getString(rs.getColumnIndex(PROF_FNAME)) + " " + rs.getString(rs.getColumnIndex(PROF_LNAME)));
            rs.moveToNext();
        }

        return Records;
    }

    private void addFullName() {
        android.database.sqlite.SQLiteDatabase conn = this.getReadableDatabase();
        sql = "SELECT * FROM " + PROFILE;

        try (Cursor rs = conn.rawQuery(sql, null)) {
            while (rs.moveToNext()) {
                String fullName = rs.getString(rs.getColumnIndexOrThrow(PROF_FNAME)) + " " +
                        rs.getString(rs.getColumnIndexOrThrow(PROF_MNAME)) + " " +
                        rs.getString(rs.getColumnIndexOrThrow(PROF_LNAME));

                FullNames.add(fullName);
            }
        }
    }


}
