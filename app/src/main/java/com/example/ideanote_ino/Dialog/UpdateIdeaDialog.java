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

import com.example.ideanote_ino.Activity.MainActivity;
import com.example.ideanote_ino.Adapter.ListViewAdapter;
import com.example.ideanote_ino.Common.IdeaDatas;
import com.example.ideanote_ino.R;
import com.example.ideanote_ino.SQLite.IdeaDto;
import com.example.ideanote_ino.SQLite.QueryForMain;

public class UpdateIdeaDialog extends Dialog {
    String TAG = "#UpdateIdeaDialog";
    private Context con;

    EditText et_update_idea_dialog_idea;
    TextView tv_update_idea_dialog_cancel;
    TextView tv_update_idea_dialog_ok;

    IdeaDto idea;
    ListViewAdapter adapter = null;
    int type = 0;

    public UpdateIdeaDialog(@NonNull Context context, IdeaDto idea, int type) {
        super(context);
        this.con = context;
        this.idea = idea;
        this.type = type;
    }

    public UpdateIdeaDialog(@NonNull Context context, IdeaDto idea, ListViewAdapter adapter) {
        super(context);
        this.con = context;
        this.idea = idea;
        this.adapter = adapter;
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

        et_update_idea_dialog_idea.setText(idea.getIno_idea());
    }

    View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            if (v == tv_update_idea_dialog_cancel){
                Log.v(TAG, "touched tv_update_idea_dialog_cancel");
                dismiss();
                return;
            }

            Log.v(TAG, "touched tv_update_idea_dialog_ok");
            String str = et_update_idea_dialog_idea.getText().toString();
            Log.v(TAG, str);
            if( !inputChecker(str) ){
                return;
            }

            QueryForMain queries = new QueryForMain(con);
            if (queries.updateIdea(str, idea.getIno_num())){
                queries.selectAllList();
                Toast.makeText(con, "아이디어가 수정되었습니다.", Toast.LENGTH_SHORT).show();

                //메인에서 업데이트 작업시 tv 갱신
                if (type == 1){
                    MainActivity main = (MainActivity) con;
                    IdeaDto dto = queries.nowIdeaDto;
                    main.tv_main_idea.setText(dto.getIno_idea());
                    main.tv_main_idea_date.setText(dto.getIno_date());
                }
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

    public void updateShow(){
        setCanceledOnTouchOutside(false);
        setCancelable(true);
        show();
    }

}
