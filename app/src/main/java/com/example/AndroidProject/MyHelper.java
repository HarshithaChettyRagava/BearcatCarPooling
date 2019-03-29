package com.example.AndroidProject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class MyHelper extends SQLiteOpenHelper {

    private static final String TABLE_NAME = "Customer_table";
    private static final String COL1 = "name";
    private static final String COL2 = "email";
    private static final String COL3 = "address";
    private static final String COL4 = "phoneNo";
    private static final String COL5 = "password";
    private static final String COL6 = "confirmPwd";
    private static final String TAG = "DatabaseHelper";

    public MyHelper(Context context)
    {
        super(context,TABLE_NAME,null,2);
    }

    @Override
    public void onCreate(SQLiteDatabase db){
        //String createTable = "create table "+ TABLE_NAME +"(_id integer primary key,name,email,address,phoneNo,password,confirm_password)";
//        String createTable = "create table "+ TABLE_NAME +"(name,email,address,phoneNo,password,confirm_password)";
        String createTable = "create table Customer_table(name,email,address,phoneNo,password,confirmPwd);";
        try{
            db.execSQL(createTable);
        }
        catch(SQLException e){
            Log.d("Error message is: ",""+e);
        }
        Log.d("came till here","");
    }

    @Override
    public void onUpgrade(SQLiteDatabase SqLiteDatabase,int i,int i1){
        SqLiteDatabase.execSQL("DROP TABLE IF EXISTS Customer_table");
        onCreate(SqLiteDatabase);
    }

    public boolean addData(String item1, String item2, String item3, String item4, String item5, String item6){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL1, item1);
        contentValues.put(COL2, item2);
        contentValues.put(COL3, item3);
        contentValues.put(COL4, item4);
        contentValues.put(COL5, item5);
        contentValues.put(COL6, item6);
        //Log.d(TAG, "addData: Adding "+ item1 + " to "+TABLE_NAME);
        long result = db.insert(TABLE_NAME, null, contentValues);

        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor allData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("Select * from Customer_table", null);
        return cursor;
    }
}
