package com.example.ideanote_ino.SQLite;

public class IdeaDto {

    int ino_num;
    String ino_idea;
    String ino_date;
    String ino_update;
    String ino_dalete;

    public IdeaDto(int ino_num, String ino_idea, String ino_date, String ino_update, String ino_dalete) {
        this.ino_num = ino_num;
        this.ino_idea = ino_idea;
        this.ino_date = ino_date;
        this.ino_update = ino_update;
        this.ino_dalete = ino_dalete;
    }

    public IdeaDto(int ino_num, String ino_idea, String ino_date) {
        this.ino_num = ino_num;
        this.ino_idea = ino_idea;
        this.ino_date = ino_date;
    }

    public int getIno_num() {
        return ino_num;
    }

    public void setIno_num(int ino_num) {
        this.ino_num = ino_num;
    }

    public String getIno_idea() {
        return ino_idea;
    }

    public void setIno_idea(String ino_idea) {
        this.ino_idea = ino_idea;
    }

    public String getIno_date() {
        return ino_date;
    }

    public void setIno_date(String ino_date) {
        this.ino_date = ino_date;
    }

    public String getIno_update() {
        return ino_update;
    }

    public void setIno_update(String ino_update) {
        this.ino_update = ino_update;
    }

    public String getIno_dalete() {
        return ino_dalete;
    }

    public void setIno_dalete(String ino_dalete) {
        this.ino_dalete = ino_dalete;
    }

    public String printAll(){
        return "ino_num : " + ino_num + ", ino_idea : "  + ino_idea + ", ino_date : " + ino_date +
                ", ino_update : " + ino_update + ", ino_dalete : "+ ino_dalete;
    }
}
