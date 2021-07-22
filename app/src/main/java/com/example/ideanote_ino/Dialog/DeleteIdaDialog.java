package com.example.ideanote_ino.Dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.ideanote_ino.Activity.AllListActivity;
import com.example.ideanote_ino.Activity.MainActivity;
import com.example.ideanote_ino.R;
import com.example.ideanote_ino.SQLite.IdeaDto;
import com.example.ideanote_ino.SQLite.QueryForMain;

import java.util.ArrayList;

public class DeleteIdaDialog extends Dialog {
    String TAG = "#DeleteIdeaDialog";
    private Context con;

    TextView tv_delete_idea_dialog_cancel;
    TextView tv_delete_idea_dialog_ok;

    IdeaDto ideaDto;
    int type = 0;

    public DeleteIdaDialog(@NonNull Context context, IdeaDto ideaDto, int type) {
        super(context);
        this.con = context;
        this.ideaDto = ideaDto;
        this.type = type;
    }
    public DeleteIdaDialog(@NonNull Context context, IdeaDto ideaDto) {
        super(context);
        this.con = context;
        this.ideaDto = ideaDto;
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

            Log.v(TAG, "touched tv_update_idea_dialog_ok");

            QueryForMain queries = new QueryForMain(con);
            if (queries.deleteIdea(ideaDto.getIno_num())){
                QueryForMain queryForMain = new QueryForMain(con);
                queryForMain.selectAllList();
                Toast.makeText(con, "아이디어가 삭제되었습니다.", Toast.LENGTH_SHORT).show();
                if(type == 1){
                    MainActivity main = (MainActivity) con;
                    main.setRandomIdea();
                } else {
                    AllListActivity al = (AllListActivity) con;
                    al.getAllList();
                }
            } else {
                Toast.makeText(con, "아이디어 삭제실패했습니다.", Toast.LENGTH_SHORT).show();
            }
            dismiss();
        }
    };

    public void deleteShow(){
        setCanceledOnTouchOutside(false);
        setCancelable(true);
        show();
    }


}
