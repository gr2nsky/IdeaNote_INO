package com.example.ideanote_ino.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ideanote_ino.Dialog.InputIdeaDialog;
import com.example.ideanote_ino.R;

public class MainActivity extends AppCompatActivity {

    ImageView iv_main_search;
    ImageView iv_main_see_all;
    TextView tv_main_idea;
    TextView tv_main_idea_date;
    ImageView iv_main_show_before_idea;
    ImageView iv_main_add_idea;
    ImageView iv_main_show_next_idea;

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
    }

    View.OnClickListener clickedAddIdea = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            InputIdeaDialog.show(MainActivity.this);
        }
    };


}