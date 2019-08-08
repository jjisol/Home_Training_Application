package org.techtown.sportsdata;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;


/**
 * Created by user on 2018-12-03.
 */

public class AlarmListViewAdapter extends BaseAdapter{
    private ArrayList<ItemForAlarm> alarmViewItemList = new ArrayList<ItemForAlarm>() ;
    private Context context;

    public AlarmListViewAdapter(Context context){
        this.context = context;

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return alarmViewItemList.size();
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item_for_alarm, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView dateTextView = (TextView) convertView.findViewById(R.id.date_for_alarm) ;
        TextView timeTextView = (TextView) convertView.findViewById(R.id.time_for_alarm) ;
        Button alarmDeleteButton = (Button) convertView.findViewById(R.id.alarm_delete_btn);
        alarmDeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
                alertDialogBuilder.setMessage("알람을 삭제하시겠습니까?");
                alertDialogBuilder.setPositiveButton("yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        alarmViewItemList.remove(pos);
                        AlarmListViewAdapter.this.notifyDataSetChanged();
                    }
                });
                alertDialogBuilder.setNegativeButton("no", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //finish();
                    }
                });
                AlertDialog alertDialog = alertDialogBuilder.create();
                alertDialog.show();
            }
        });

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ItemForAlarm alarmViewItem = alarmViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        dateTextView.setText(alarmViewItem.getDate());
        timeTextView.setText(alarmViewItem.getTime());


        return convertView;
    }

    // 지정한 위치(position)에 있는 데이터와 관계된 아이템(row)의 ID를 리턴. : 필수 구현
    @Override
    public long getItemId(int position) {
        return position ;
    }

    // 지정한 위치(position)에 있는 데이터 리턴 : 필수 구현
    @Override
    public Object getItem(int position) {
        return alarmViewItemList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(String date, String time) {
        ItemForAlarm item = new ItemForAlarm();

        item.setDate(date);
        item.setTime(time);

        alarmViewItemList.add(item);
    }

}

