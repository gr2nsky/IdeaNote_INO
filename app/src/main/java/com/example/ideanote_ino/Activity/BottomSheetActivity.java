package com.example.ideanote_ino.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ideanote_ino.Dialog.DeleteIdaDialog;
import com.example.ideanote_ino.Dialog.InputIdeaDialog;
import com.example.ideanote_ino.Dialog.UpdateIdeaDialog;
import com.example.ideanote_ino.Interface.AllListResetInterface;
import com.example.ideanote_ino.R;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import static android.content.Context.CLIPBOARD_SERVICE;
import static androidx.core.content.ContextCompat.getSystemService;

public class BottomSheetActivity extends BottomSheetDialogFragment implements AllListResetInterface {

    TextView tv_bottom_sheet_idea_copy;
    TextView tv_bottom_sheet_idea_update;
    TextView tv_bottom_sheet_idea_delete;

    int ino_num;

    public BottomSheetActivity(int num) {
        this.ino_num = num;
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
        getView().findViewById(R.id.tv_bottom_sheet_idea_copy).setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
//                ClipboardManager clipboardManager = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
//                ClipData clipData = ClipData.newPlainText("IDEA", Integer.toString(ino_num));
//                clipboardManager.setPrimaryClip(clipData);
//                Toast.makeText(getActivity(), "복사 되었습니다.", Toast.LENGTH_SHORT).show();
                return false;
            }
        });

        getView().findViewById(R.id.tv_bottom_sheet_idea_update).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // update
                Log.v("바텀 : ino_num", Integer.toString(ino_num));
                UpdateIdeaDialog updateIdeaDialog = new UpdateIdeaDialog(getActivity(), ino_num);
                updateIdeaDialog.updateShow(getActivity(), ino_num);
            }
        });

        getView().findViewById(R.id.tv_bottom_sheet_idea_delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // delete
                DeleteIdaDialog deleteIdaDialog = new DeleteIdaDialog(getActivity(), ino_num);
                deleteIdaDialog.deleteShow(getActivity(), ino_num);
            }
        });

    }

    @Override
    public void onAllListReset(boolean isSelected) {

    }
}