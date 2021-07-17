package com.example.ideanote_ino.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.example.ideanote_ino.R;

public class InputIdeaDialog extends Dialog {

    private Context con;

    public InputIdeaDialog(@NonNull Context context) {
        super(context);
        this.con = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_main_input_idea);
    }


}
