package com.YtoJ.ideanote_ino.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import com.YtoJ.ideanote_ino.Adapter.ListViewAdapter;
import com.YtoJ.ideanote_ino.Common.IdeaDatas;
import com.YtoJ.ideanote_ino.R;
import com.YtoJ.ideanote_ino.SQLite.IdeaDto;

import java.util.ArrayList;

public class AllListActivity extends AppCompatActivity {
    String TAG = "AllListActivity";

    ListView lv_all_list;
    BottomSheetActivity bottomSheet;
    TextView tv_all_list_no_data;

    IdeaDatas ideaDatas = null;
    ArrayList<IdeaDto> list = null;
    ListViewAdapter adapter;

    SearchView search_bar_all_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_list);

        tv_all_list_no_data = findViewById(R.id.tv_all_list_no_data);
        lv_all_list = findViewById(R.id.lv_all_list);
        lv_all_list.setOnItemClickListener(clickListItem);

        search_bar_all_list = findViewById(R.id.search_bar_all_list);

        search_bar_all_list.setImeOptions(EditorInfo.IME_ACTION_DONE);
        search_bar_all_list.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });
        getAllList();
    }

    public void getAllList() {
        ideaDatas = IdeaDatas.getIdeaDatas();

        if(ideaDatas.size() == 0){
            tv_all_list_no_data.setVisibility(View.VISIBLE);
            return;
        }

        tv_all_list_no_data.setVisibility(View.GONE);
        list = ideaDatas.get();
        if (adapter == null){
            adapter = new ListViewAdapter(AllListActivity.this, R.layout.layout_all_list_listlayout, list);
            lv_all_list.setAdapter(adapter);
            return;
        } else {
            adapter.setList(list);
            adapter.notifyDataSetChanged();
            adapter.getFilter().filter(search_bar_all_list.getQuery());
        }
    }

    AdapterView.OnItemClickListener clickListItem = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            IdeaDto idea = list.get(position);

            bottomSheet = new BottomSheetActivity(AllListActivity.this, idea, adapter);
            bottomSheet.show(getSupportFragmentManager(), bottomSheet.getTag());
        }
    };
}