package com.greenteam.otty;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Windows on 5/19/2018.
 */

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String DB_NAME = "dbtryagain8";
    private static final int DB_VERSION = 1;

    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    // CREATE DATABASE
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {


        String cSample = "CREATE TABLE entries(profile_id INT, date INT, tantrums INT, sib INT, ssb INT, hl INT, pw INT, notes TEXT);";
        sqLiteDatabase.execSQL(cSample);
        String profiles = "CREATE TABLE profiles(profile_id INTEGER PRIMARY KEY AUTOINCREMENT, name VARCHAR NOT NULL);";
        sqLiteDatabase.execSQL(profiles);

    }

    // ADD RECORD
    public boolean addRecord(int profile_id, int Date, int tantrums, int sib, int ssb, int hl, int pw, String notes) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("profile_id", profile_id);
        contentValues.put("date", Date);
        contentValues.put("tantrums", tantrums);
        contentValues.put("sib", sib);
        contentValues.put("ssb", ssb);
        contentValues.put("hl", hl);
        contentValues.put("pw", pw);
        contentValues.put("notes", notes);
        int rows = db.update("entries", contentValues, "date = '" + Date + "' and profile_id= " + profile_id,null);

        if (rows == 0) {
            contentValues.put("profile_id", profile_id);
            contentValues.put("date", Date);
            contentValues.put("tantrums", tantrums);
            contentValues.put("sib", sib);
            contentValues.put("ssb", ssb);
            contentValues.put("hl", hl);
            contentValues.put("pw", pw);
            contentValues.put("notes", notes);
            db.insert("entries", null, contentValues);
        }

        db.close();
        return true;
    }

    // ADD NEW PROFILE
    public boolean addprofile (String name) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        db.insert("profiles", null, contentValues);
        db.close();
        return true;
    }

    // SELECT RECORD
    public Cursor getData(int date, int profile_id){
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT tantrums, sib, ssb, hl, pw, notes FROM entries where date ='" + date + "' and profile_id = " + profile_id + ";";
        Cursor record = db.rawQuery(query, null);
        return record;

    }

    // SELECT RECORD
    public Cursor getAnalyis(int edate, int sdate, int profile_id){
        SQLiteDatabase db = getWritableDatabase();
        String query = "Select (Select COUNT(tantrums) * 1 from entries where tantrums = 1 and date between " + sdate + " and " + edate + " and profile_id = " + profile_id + ") as tantrums_1, (Select COUNT(tantrums) * 2 from entries where tantrums = 2 and date between " + sdate + " and " + edate + " and profile_id = " + profile_id + ") as tantrums_2, (Select COUNT(tantrums) * 3 from entries where tantrums = 3 and date between " + sdate + " and " + edate + " and profile_id = " + profile_id + ") as tantrums_3, (Select COUNT(tantrums) * 4 from entries where tantrums = 4 and date between " + sdate + " and " + edate + " and profile_id = " + profile_id + ") as tantrums_4, (Select COUNT(sib) * 1 from entries where sib = 1 and date between " + sdate + " and " + edate + " and profile_id = " + profile_id + ") as sib_1, (Select COUNT(sib) * 2 from entries where sib = 2  and date between " + sdate + " and " + edate + " and profile_id = " + profile_id + ") as sib_2, (Select COUNT(sib) * 3 from entries where sib = 3 and date between " + sdate + " and " + edate + " and profile_id = " + profile_id + ") as sib_3, (Select COUNT(sib) * 4 from entries where sib = 4 and date between " + sdate + " and " + edate + " and profile_id = " + profile_id + ") as sib_4, (Select COUNT(ssb) * 1 from entries where ssb = 1 and date between " + sdate + " and " + edate + " and profile_id = " + profile_id + ") as ssb_1, (Select COUNT(ssb) * 2 from entries where ssb = 2 and date between " + sdate + " and " + edate + " and profile_id = " + profile_id + ") as ssb_2, (Select COUNT(ssb) * 3 from entries where ssb = 3 and date between " + sdate + " and " + edate + " and profile_id = " + profile_id + ") as ssb_3, (Select COUNT(ssb) * 4 from entries where ssb = 4 and date between " + sdate + " and " + edate + " and profile_id = " + profile_id + ") as ssb_4, (Select COUNT(hl) * 1 from entries where hl = 1 and date between " + sdate + " and " + edate + " and profile_id = " + profile_id + ") as hl_1, (Select COUNT(hl) * 2 from entries where hl = 2 and date between " + sdate + " and " + edate + " and profile_id = " + profile_id + ") as hl_2, (Select COUNT(hl) * 3 from entries where hl = 3 and date between " + sdate + " and " + edate + " and profile_id = " + profile_id + ") as hl_3, (Select COUNT(hl) * 4 from entries where hl = 4 and date between " + sdate + " and " + edate + " and profile_id = " + profile_id + ") as hl_4, (Select COUNT(pw) * 1 from entries where pw = 1 and date between " + sdate + " and " + edate + " and profile_id = " + profile_id + ") as pw_1, (Select COUNT(pw) * 2 from entries where pw = 2 and date between " + sdate + " and " + edate + " and profile_id = " + profile_id + ") as pw_2, (Select COUNT(pw) * 3 from entries where pw = 3 and date between " + sdate + " and " + edate + " and profile_id = " + profile_id + ") as pw_3, (Select COUNT(pw) * 4 from entries where hl = 4 and date between " + sdate + " and " + edate + " and profile_id = " + profile_id + ") as pw_4,  (Select Count(*) from entries where date between " + sdate + " and " + edate + " and profile_id = " + profile_id + ") as total_entries;";
        Cursor analysis = db.rawQuery(query, null);
        return analysis;

    }


    //LIST PROFILES
    public ArrayList<String> getProfile() {

        ArrayList<String> list = new ArrayList<String>();
        SQLiteDatabase db = this.getReadableDatabase();
        db.beginTransaction();

        try {
            String selectQuery = "SELECT NAME FROM profiles;";
            Cursor cursor = db.rawQuery(selectQuery, null);
            if (cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    // Add province name to arraylist
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    list.add(name);
                }
            }
            db.setTransactionSuccessful();

        } catch (SQLiteException e) {
            e.printStackTrace();

        } finally {
            db.endTransaction();
            // End the transaction.
        }
        return list;
    }
    // IF TABLE EXISTS..
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String cSample = "DROP TABLE IF EXISTS entries;";
        sqLiteDatabase.execSQL(cSample);
        String cprofiles = "DROP TABLE IF EXISTS profiles;";
        sqLiteDatabase.execSQL(cprofiles);
        onCreate(sqLiteDatabase);
    }
}