package com.example.ideanote_ino.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.ideanote_ino.R;
import com.example.ideanote_ino.SQLite.MyIno;


public class LogoActivity extends AppCompatActivity {

    MyIno myIno;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logo);

        myIno = new MyIno(LogoActivity.this);

        /*로고 띄운 후 다른 액티비티로 이동*/
        logoView();

    }

    private void logoView(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                /*수정필요!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                  intent 될 액티비티로 수정해야함*/

//                Intent intent = new Intent(LogoActivity.this, LogoActivity.class);
//                startActivity(intent);
                finish();
            }
        }, 1000);
    }

}