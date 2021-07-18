package com.example.ideanote_ino.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.ideanote_ino.Adapter.ListViewAdapter;
import com.example.ideanote_ino.Common.IdeaDatas;
import com.example.ideanote_ino.Interface.AllListResetInterface;
import com.example.ideanote_ino.R;
import com.example.ideanote_ino.SQLite.IdeaDto;
import com.example.ideanote_ino.SQLite.QueryForMain;

import java.util.ArrayList;

import static androidx.core.content.ContextCompat.getSystemService;

public class AllListActivity extends AppCompatActivity implements AllListResetInterface {

    ListView lv_all_list;
    BottomSheetActivity bottomSheet;

    IdeaDatas ideaDatas = null;
    ArrayList<IdeaDto> list = null;

    QueryForMain queryForMain = new QueryForMain(AllListActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_list);

        lv_all_list = findViewById(R.id.lv_all_list);
        lv_all_list.setOnItemClickListener(clickListItem);

        if (!queryForMain.selectAllList()){
            Toast.makeText(this, "아이디어 목록을 불러오는데 실패했습니다. 앱을 다시 시작해주세요.", Toast.LENGTH_SHORT).show();
        }


    }

    @Override
    public void onResume() {
        super.onResume();
        getAllList();
    }

    @Override
    protected void onPause() {
        super.onPause();
        getAllList();
    }

    public void getAllList() {
        ideaDatas = IdeaDatas.getIdeaDatas();
        list = ideaDatas.get();


        ListViewAdapter adapter = new ListViewAdapter(AllListActivity.this, R.layout.layout_all_list_listlayout, list);

        lv_all_list.setAdapter(adapter);

    }

    AdapterView.OnItemClickListener clickListItem = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            int ino_num = list.get(position).getIno_num();
            Log.v("선택한 번호", Integer.toString(list.get(position).getIno_num()));

            bottomSheet = new BottomSheetActivity(ino_num);
            bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
        }
    };

    @Override
    public void onAllListReset(boolean isSelected) {
        if (isSelected) {
            getAllList();
        }
    }
}