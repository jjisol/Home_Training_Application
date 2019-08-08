package org.techtown.sportsdata;

public class ItemForSending {
    private String s_part;

    public ItemForSending(String part, String name, String set, String count){
        this.s_part = part;
        this.s_name = name;
        this.s_set = set;
        this.s_count = count;
    }

    public String getS_part() {
        return s_part;
    }

    public void setS_part(String s_part) {
        this.s_part = s_part;
    }

    public String getS_name() {
        return s_name;
    }

    public void setS_name(String s_name) {
        this.s_name = s_name;
    }

    public String getS_set() {
        return s_set;
    }

    public void setS_set(String s_set) {
        this.s_set = s_set;
    }

    public String getS_count() {
        return s_count;
    }

    public void setS_count(String s_count) {
        this.s_count = s_count;
    }

    private String s_name;
    private String s_set;
    private String s_count;

    public ItemForSending(){}

}
