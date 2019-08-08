package org.techtown.sportsdata;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class PlanLIstViewAdapter extends BaseAdapter {
    private ArrayList<ItemForSending> listViewItemList = new ArrayList<ItemForSending>() ;

    // ListViewAdapter의 생성자
    public PlanLIstViewAdapter() {

    }

    // Adapter에 사용되는 데이터의 개수를 리턴. : 필수 구현
    @Override
    public int getCount() {
        return listViewItemList.size() ;
    }

    // position에 위치한 데이터를 화면에 출력하는데 사용될 View를 리턴. : 필수 구현
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final int pos = position;
        final Context context = parent.getContext();

        // "listview_item" Layout을 inflate하여 convertView 참조 획득.
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.listview_item_for_sending, parent, false);
        }

        // 화면에 표시될 View(Layout이 inflate된)으로부터 위젯에 대한 참조 획득
        TextView nameTextView = (TextView) convertView.findViewById(R.id.name_for_sending) ;
        TextView setTextView = (TextView) convertView.findViewById(R.id.set_for_sending) ;
        TextView countTextView = (TextView) convertView.findViewById(R.id.count_for_sending) ;

        // Data Set(listViewItemList)에서 position에 위치한 데이터 참조 획득
        ItemForSending listViewItem = listViewItemList.get(position);

        // 아이템 내 각 위젯에 데이터 반영
        nameTextView.setText(listViewItem.getS_name());
        setTextView.setText(listViewItem.getS_set());
        countTextView.setText(listViewItem.getS_count());

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
        return listViewItemList.get(position) ;
    }

    // 아이템 데이터 추가를 위한 함수. 개발자가 원하는대로 작성 가능.
    public void addItem(String part, String name, String set, String count) {
        ItemForSending item = new ItemForSending();

        item.setS_part(part);
        item.setS_name(name);
        item.setS_set(set);
        item.setS_count(count);

        listViewItemList.add(item);
    }

}
