package com.example.ideanote_ino.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ideanote_ino.Dialog.InputIdeaDialog;
import com.example.ideanote_ino.R;
import com.example.ideanote_ino.SQLite.QueryForMain;

public class MainActivity extends AppCompatActivity {

    ImageView iv_main_search;
    ImageView iv_main_see_all;
    TextView tv_main_idea;
    TextView tv_main_idea_date;
    ImageView iv_main_show_before_idea;
    ImageView iv_main_add_idea;
    ImageView iv_main_show_next_idea;

    QueryForMain queryForMain = new QueryForMain(MainActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv_main_search = findViewById(R.id.iv_main_search);
        iv_main_see_all = findViewById(R.id.iv_main_see_all);
        tv_main_idea = findViewById(R.id.tv_main_idea);
        tv_main_idea_date = findViewById(R.id.tv_main_idea_date);
        iv_main_show_before_idea = findViewById(R.id.iv_main_show_before_idea);
        iv_main_add_idea = findViewById(R.id.iv_main_add_idea);
        iv_main_show_next_idea = findViewById(R.id.iv_main_show_next_idea);

        iv_main_add_idea.setOnClickListener(clickedAddIdea);

        if (!queryForMain.selectAllIdea()){
            Toast.makeText(this, "아이디어 목록을 불러오는데 실패했습니다. 앱을 다시 시작해주세요.", Toast.LENGTH_SHORT).show();
        }
    }

    View.OnClickListener clickedAddIdea = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            InputIdeaDialog.show(MainActivity.this);
        }
    };


}