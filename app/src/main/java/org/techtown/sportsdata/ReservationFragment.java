package org.techtown.sportsdata;

import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by user on 2018-11-26.
 */

public class ReservationFragment extends Fragment {
    Button date, time, send;
    Boolean dateCheck = false, timeCheck = false;
    public static int rYear, rMonth, rDay, rHour, rMinute;
    Intent intent;
    final Calendar c = Calendar.getInstance();
    static public ArrayList<ItemForAlarm> alarmArrayList = new ArrayList<>();
    ItemForAlarm itemForAlarm;
    private String format;

    public ReservationFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_reservation, container, false);
        date = (Button)view.findViewById(R.id.date_btn);
        time = (Button)view.findViewById(R.id.time_btn);
        send = (Button)view.findViewById(R.id.send_btn);
        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                rYear = year;
                                rMonth = monthOfYear;
                                rDay = dayOfMonth;
                            }
                        }, mYear, mMonth, mDay);


                datePickerDialog.show();
                dateCheck = true;

            }
        });
        time.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int mHour = c.get(Calendar.HOUR_OF_DAY);
                int mMinute = c.get(Calendar.MINUTE);

                TimePickerDialog timePickerDialog = new TimePickerDialog(getContext(),
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                                rHour = hourOfDay;
                                rMinute = minute;
                            }
                        }, mHour, mMinute, false);
                timePickerDialog.show();
                timeCheck = true;


            }
        });
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(), AlarmActivity.class);
                if(timeCheck == true && dateCheck == true){
                    Toast.makeText(getActivity(), rYear+"/"+rMonth+"/"+rDay+" "+rHour+":"+rMinute+"에 운동 알림이 설정되었습니다.", Toast.LENGTH_LONG).show();
                    intent.putExtra("Year", rYear);
                    intent.putExtra("Month", rMonth);
                    intent.putExtra("Day", rDay);
                    intent.putExtra("Hour", rHour);
                    intent.putExtra("Minute", rMinute);

                    if(rHour == 0) {
                        rHour += 12;
                        format = "AM";
                    }else if(rHour == 12){
                        format = "PM";
                    }else if(rHour > 12) {
                        rHour -= 12;
                        format = "PM";
                    }else {
                        format = "AM";
                    }


                    String date = rYear+"년 "+ rMonth+"월 "+rDay+"일";
                    String time = format + " " +rHour+" : "+rMinute;
                    itemForAlarm = new ItemForAlarm(date, time);
                    alarmArrayList.add(itemForAlarm);
                    startActivity(intent);
                }
                else
                    Toast.makeText(getActivity(),"운동 날짜/시간을 선택해주세요", Toast.LENGTH_LONG).show();


            }
        });

        return view;
    }

}
