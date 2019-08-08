package org.techtown.sportsdata;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import static org.techtown.sportsdata.ReservationFragment.alarmArrayList;

/**
 * Created by user on 2018-12-04.
 */

public class AlarmListActivity extends AppCompatActivity{
    private ListView alarmListView;
    private AlarmListViewAdapter alarmListViewAdapter;
    ItemForAlarm itemForAlarm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.listview_alarm_item);
        getSupportActionBar().setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM);
        getSupportActionBar().setCustomView(R.layout.actionbar);

        alarmListView = (ListView)findViewById(R.id.alarm_listview);
        alarmListViewAdapter = new AlarmListViewAdapter(this);
        alarmListViewAdapter.notifyDataSetChanged();

        for (int i = 0; i < alarmArrayList.size(); i++) {
            itemForAlarm = alarmArrayList.get(i);
            alarmListViewAdapter.addItem(itemForAlarm.getDate(), itemForAlarm.getTime());
        }

        alarmListView.setAdapter(alarmListViewAdapter);



    }


}
