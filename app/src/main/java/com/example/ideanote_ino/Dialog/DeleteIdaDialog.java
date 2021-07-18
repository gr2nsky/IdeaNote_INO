package com.example.ideanote_ino.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.ideanote_ino.R;
import com.example.ideanote_ino.SQLite.QueryForMain;

public class DeleteIdaDialog extends Dialog {
    String TAG = "#DeleteIdeaDialog";
    private Context con;

    TextView tv_delete_idea_dialog_cancel;
    TextView tv_delete_idea_dialog_ok;

    int ino_num;

    public DeleteIdaDialog(@NonNull Context context, int ino_num) {
        super(context);
        this.con = context;
        this.ino_num = ino_num;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_all_list_delete_idea);

        tv_delete_idea_dialog_cancel = findViewById(R.id.tv_delete_idea_dialog_cancel);
        tv_delete_idea_dialog_ok = findViewById(R.id.tv_delete_idea_dialog_ok);

        tv_delete_idea_dialog_cancel.setOnClickListener(onClickListener);
        tv_delete_idea_dialog_ok.setOnClickListener(onClickListener);
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == tv_delete_idea_dialog_cancel){
                Log.v(TAG, "touched tv_delete_idea_dialog_cancel");
                dismiss();
                return;
            }
            //SQLite에 입력한 아이디어를 저장하는 부분
            Log.v(TAG, "touched tv_update_idea_dialog_ok");
//            if( !inputChecker(str) ){
//                return;
//            }

            QueryForMain queries = new QueryForMain(con);
            if (queries.deleteIdea(ino_num)){
                Toast.makeText(con, "아이디어가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(con, "아이디어 삭제실패했습니다.", Toast.LENGTH_SHORT).show();
            }
            dismiss();
        }
    };

    public void deleteShow(Context con, int num){
        Log.v("다이얼로그 : ino_num", Integer.toString(ino_num));
        DeleteIdaDialog dialog = new DeleteIdaDialog(con, num);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(true);
        dialog.show();
    }


}
