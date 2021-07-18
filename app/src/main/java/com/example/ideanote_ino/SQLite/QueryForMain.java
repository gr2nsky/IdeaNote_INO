package com.example.ideanote_ino.SQLite;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
import android.widget.Toast;

import com.example.ideanote_ino.Common.IdeaDatas;

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

    public boolean selectAllList(){
        IdeaDatas ideaDatas = IdeaDatas.getIdeaDatas();
        ideaDatas.clear();

        try{
            db = myIno.getReadableDatabase();
            String query = "SELECT ino_num, ino_idea, ino_date FROM myino WHERE ino_dalete IS NULL";

            Cursor cursor = db.rawQuery(query, null);
            while(cursor.moveToNext()){
                int ino_num = cursor.getInt(0);
                String ino_idea = cursor.getString(1);
                String ino_date = cursor.getString(2);

                IdeaDto dto = new IdeaDto(ino_num, ino_idea, ino_date);
                Log.v(TAG, dto.printAll());
                ideaDatas.appand(dto);
            }
            cursor.close();
            myIno.close();
            return true;
        }catch (Exception e){
            Log.v(TAG, "###################");
            e.printStackTrace();
        }
        return false;
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

    public boolean updateIdea(String str, int ino_num){
        try{
            db = myIno.getWritableDatabase();
            String query = "UPDATE myino SET ino_idea = '" + str + "', ino_update = '" + getTime() + "' WHERE ino_num = " + ino_num;
            Log.v("query", query);

            db.execSQL(query);
            myIno.close();

            return true;
        }catch (Exception e){
            Log.v(TAG, "###################");
            e.printStackTrace();
        }
        return false;
    }

    public boolean deleteIdea(int ino_num){
        try{
            db = myIno.getWritableDatabase();
            String query = "UPDATE myino SET ino_dalete = '" + getTime() + "' WHERE ino_num = " + ino_num;
            Log.v("query", query);

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
