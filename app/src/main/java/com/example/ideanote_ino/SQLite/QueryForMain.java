package com.example.ideanote_ino.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class QueryForMain {
    String TAG = "QueryForMain";
    MyIno myIno;
    Context con;
    SQLiteDatabase db;

    public QueryForMain(Context con){
        this.con = con;
        myIno = new MyIno(con);
    }

    public boolean insertIdea(String str){
        try{
            db = myIno.getWritableDatabase();
            String query = "INSERT INTO myino(ino_idea, ino_date) VALUES ('" + str + "', '" + getTime() + "')";
            db.execSQL(query);
            myIno.close();

            return true;
        }catch (Exception e){
            Log.v(TAG, "###################");
            e.printStackTrace();
        }
        return false;
    }

    private String getTime() {
        long mNow = System.currentTimeMillis();
        Date mDate = new Date(mNow);
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String stringDate = mFormat.format(mDate);
        return stringDate;
    }
}
