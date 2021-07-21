package com.example.ideanote_ino.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import com.example.ideanote_ino.Adapter.ListViewAdapter;
import com.example.ideanote_ino.Common.IdeaDatas;
import com.example.ideanote_ino.R;
import com.example.ideanote_ino.SQLite.IdeaDto;
import com.example.ideanote_ino.SQLite.QueryForMain;

import java.util.ArrayList;

public class AllListActivity extends AppCompatActivity {
    String TAG = "AllListActivity";

    ListView lv_all_list;
    BottomSheetActivity bottomSheet;

    IdeaDatas ideaDatas = null;
    ArrayList<IdeaDto> list = null;
    ListViewAdapter adapter;

    SearchView search_bar_all_list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_list);

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
        list = ideaDatas.get();
        if (adapter == null){
            adapter = new ListViewAdapter(AllListActivity.this, R.layout.layout_all_list_listlayout, list);
            lv_all_list.setAdapter(adapter);
        } else {
            adapter.setList(list);
            adapter.notifyDataSetChanged();
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