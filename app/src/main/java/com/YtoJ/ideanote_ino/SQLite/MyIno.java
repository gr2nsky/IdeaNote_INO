package com.YtoJ.ideanote_ino.SQLite;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MyIno extends SQLiteOpenHelper {

    public MyIno(Context context){
        super(context, "MyIno.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE myino(ino_num INTEGER PRIMARY KEY AUTOINCREMENT," +
                " ino_idea TEXT NOT NULL, ino_date TEXT NOT NULL, ino_update TEXT, ino_dalete TEXT)";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String query = "DROP TABLE IF EXISTS myino";
        db.execSQL(query);
        onCreate(db);
    }

}
