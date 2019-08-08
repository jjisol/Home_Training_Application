package org.techtown.sportsdata;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

public class ListViewItem {
    private String iconDrawable ;
    private String titleStr ;
    private String descStr ;
    private String wayStr;

    public ListViewItem(){}

    public ListViewItem(String icon, String title, String desc, String way){
        this.iconDrawable = icon;
        this.titleStr = title;
        this.descStr = desc;
        this.wayStr = way;
    }

    public void setIcon(String icon) {
        iconDrawable = icon ;
    }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }
    public void setWay(String way) { wayStr = way;}

    public String getIcon() {
        return this.iconDrawable ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }
    public String getWay() { return this.wayStr; }
}
