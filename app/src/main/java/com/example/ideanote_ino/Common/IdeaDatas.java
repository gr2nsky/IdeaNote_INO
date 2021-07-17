package com.example.ideanote_ino.Common;

import com.example.ideanote_ino.SQLite.IdeaDto;

import java.util.ArrayList;

public class IdeaDatas {

    public static IdeaDatas ideaDatas = null;
    ArrayList<IdeaDto> ideas = new ArrayList<>();

    private IdeaDatas(){}
    public static IdeaDatas getIdeaDatas(){
        if (ideaDatas == null ){
            ideaDatas = new IdeaDatas();
        }
        return ideaDatas;
    }

    public void appand(IdeaDto dto){
        ideas.add(dto);
    }

    public void clear() {
        ideas.clear();
    }

    public ArrayList<IdeaDto> get(){
        return ideas;
    }

}
