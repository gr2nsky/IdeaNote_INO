package com.example.ideanote_ino.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.ideanote_ino.Interface.AllListResetInterface;
import com.example.ideanote_ino.R;
import com.example.ideanote_ino.SQLite.QueryForMain;

public class UpdateIdeaDialog extends Dialog {
    String TAG = "#UpdateIdeaDialog";
    private Context con;

    EditText et_update_idea_dialog_idea;
    TextView tv_update_idea_dialog_cancel;
    TextView tv_update_idea_dialog_ok;

    int ino_num;

    private AllListResetInterface allListResetInterface;

    public UpdateIdeaDialog(@NonNull Context context, int ino_num) {
        super(context);
        this.con = context;
        this.ino_num = ino_num;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_all_list_update_idea);

        et_update_idea_dialog_idea = findViewById(R.id.et_update_idea_dialog_idea);
        tv_update_idea_dialog_cancel = findViewById(R.id.tv_update_idea_dialog_cancel);
        tv_update_idea_dialog_ok = findViewById(R.id.tv_update_idea_dialog_ok);

        tv_update_idea_dialog_cancel.setOnClickListener(onClickListener);
        tv_update_idea_dialog_ok.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == tv_update_idea_dialog_cancel){
                Log.v(TAG, "touched tv_update_idea_dialog_cancel");
                dismiss();
                return;
            }
            //SQLite에 입력한 아이디어를 저장하는 부분
            Log.v(TAG, "touched tv_update_idea_dialog_ok");
            String str = et_update_idea_dialog_idea.getText().toString();
            Log.v(TAG, str);
            if( !inputChecker(str) ){
                return;
            }

            QueryForMain queries = new QueryForMain(con);
            if (queries.updateIdea(str, ino_num)){
                allListResetInterface.onAllListReset(true);
                Toast.makeText(con, "아이디어가 수정되었습니다.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(con, "아이디어 수정에 실패했습니다.", Toast.LENGTH_SHORT).show();
            }
            dismiss();

        }
    };

    public boolean inputChecker(String str){
        String check = str.replace(" ", "");
        if (check.equals("") || check == null){
            return false;
        }
        return true;
    }


    public void updateShow(Context con, int num){
        Log.v("다이얼로그 : ino_num", Integer.toString(ino_num));
//        this.ino_num = num;
        UpdateIdeaDialog dialog = new UpdateIdeaDialog(con, num);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        dialog.show();
    }

}
