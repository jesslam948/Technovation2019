package com.example.technovation2019;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UserDatabaseHandler extends SQLiteOpenHelper{
    public static final String DATABASE_NAME = "userDatabase.db";
    public static final String TABLE_NAME = "myInformation";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    
public UserDatabaseHandler(Context context){
        super(context, DATABASE_NAME , null, 1);
        }//make constructor private

@Override
public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        db.execSQL("create table myInformation " +
        "(email text primary key, password text)"
        );
        }

@Override
public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        db.execSQL("DROP TABLE IF EXISTS myInformation");
        onCreate(db);
        }

public boolean insertUserInfo (String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        db.insert(TABLE_NAME, null, contentValues);
        return true;
        }
public boolean verifyUserInfo (String email, String password) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from myInformation where email='"+email+"' and password= '"+password+"'", null );
        if (res.getCount() > 0) {
        return true;
        } else {
        return false;
        }
        }
public boolean isUserExist(String email) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res =  db.rawQuery( "select * from myInformation where email='"+email+"'", null );
        if (res.getCount() > 0) {
        return true;
        } else {
        return false;
        }
        }

public boolean updateUserInfo (String email, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("email", email);
        contentValues.put("password", password);
        db.update(TABLE_NAME, contentValues, "email = ? ", new String[]{email} );
        return true;
        }

public Integer deleteUserInfo (String email) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,
        "email = ? ",
        new String[]{email});
        }

        }


