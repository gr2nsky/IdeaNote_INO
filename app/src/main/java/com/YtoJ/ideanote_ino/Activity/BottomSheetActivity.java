package com.YtoJ.ideanote_ino.Activity;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.YtoJ.ideanote_ino.Adapter.ListViewAdapter;
import com.YtoJ.ideanote_ino.Dialog.DeleteIdaDialog;
import com.YtoJ.ideanote_ino.Dialog.UpdateIdeaDialog;
import com.YtoJ.ideanote_ino.R;
import com.YtoJ.ideanote_ino.SQLite.IdeaDto;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import static android.content.Context.CLIPBOARD_SERVICE;

public class BottomSheetActivity extends BottomSheetDialogFragment {

    TextView tv_bottom_sheet_idea_copy;
    TextView tv_bottom_sheet_idea_update;
    TextView tv_bottom_sheet_idea_delete;

    MainActivity mainActivity = null;
    AllListActivity allListActivity = null;

    IdeaDto idea;
    ListViewAdapter adapter = null;


    public BottomSheetActivity(MainActivity parent, IdeaDto idea) {
        this.idea = idea;
        this.mainActivity = parent;
    }

    public BottomSheetActivity(AllListActivity parent, IdeaDto idea, ListViewAdapter adapter) {
        this.idea = idea;
        this.allListActivity = parent;
        this.adapter = adapter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_bottom_sheet, container, false);

        tv_bottom_sheet_idea_copy = view.findViewById(R.id.tv_bottom_sheet_idea_copy);
        tv_bottom_sheet_idea_update = view.findViewById(R.id.tv_bottom_sheet_idea_update);
        tv_bottom_sheet_idea_delete = view.findViewById(R.id.tv_bottom_sheet_idea_delete);

        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        getView().findViewById(R.id.tv_bottom_sheet_idea_copy).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ClipboardManager clipboardManager = (ClipboardManager) getContext().getSystemService(CLIPBOARD_SERVICE);
                ClipData clipData = ClipData.newPlainText("IDEA", idea.getIno_idea());
                clipboardManager.setPrimaryClip(clipData);
                Toast.makeText(getActivity(), "?????? ???????????????.", Toast.LENGTH_SHORT).show();
                dismiss();
            }
        });

        getView().findViewById(R.id.tv_bottom_sheet_idea_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // update
                UpdateIdeaDialog updateIdeaDialog = null;
                if(mainActivity != null){
                    updateIdeaDialog = new UpdateIdeaDialog(getActivity(), idea, 1);
                } else {
                    updateIdeaDialog = new UpdateIdeaDialog(getActivity(), idea);
                }
                updateIdeaDialog.updateShow();
                dismiss();
            }
        });

        getView().findViewById(R.id.tv_bottom_sheet_idea_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DeleteIdaDialog deleteIdaDialog;
                // delete
                if(mainActivity != null){
                    deleteIdaDialog = new DeleteIdaDialog(getActivity(), idea, 1);
                } else {
                    deleteIdaDialog = new DeleteIdaDialog(getActivity(), idea);
                }
                deleteIdaDialog.deleteShow();
                dismiss();
            }
        });

    }

    public Activity getContext(){
        if (mainActivity == null) {
            return allListActivity;
        }
        return mainActivity;
    }
}