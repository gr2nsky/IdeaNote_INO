package com.example.ideanote_ino.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.ideanote_ino.R;

public class InputIdeaDialog extends Dialog {
    String TAG = "#InputIdeaDialog";
    private Context con;

    EditText et_input_idea_dialog_idea;
    TextView tv_input_idea_dialog_negative;
    TextView tv_input_idea_dialog_positive;

    public InputIdeaDialog(@NonNull Context context) {
        super(context);
        this.con = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_main_input_idea);

        et_input_idea_dialog_idea = findViewById(R.id.et_input_idea_dialog_idea);
        tv_input_idea_dialog_negative = findViewById(R.id.tv_input_idea_dialog_negative);
        tv_input_idea_dialog_positive = findViewById(R.id.tv_input_idea_dialog_positive);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == tv_input_idea_dialog_negative){
                Log.v(TAG, "touched tv_input_idea_dialog_negative");
                dismiss();
                return;
            }
            //SQLite에 입력한 아이디어를 저장하는 부분
            Log.v(TAG, "touched tv_input_idea_dialog_positive");
        }
    };


    public static void show(Context con){
        InputIdeaDialog dialog = new InputIdeaDialog(con);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        dialog.show();
    }
}
