package com.example.AndroidProject;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyHelper extends SQLiteOpenHelper {
    public MyHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int Version)
    {
        super(context,name,factory,Version);
    }
    @Override
    public void onCreate(SQLiteDatabase SqLiteDatabase){
        SqLiteDatabase.execSQL("create table Tb_User(_id integer primary key,name text, phone text,email text, Address text,password text,confirm_password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase SqLiteDatabase,int i,int i1){


    }
}
