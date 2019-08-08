package org.techtown.sportsdata;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import java.util.ArrayList;

/**
 * Created by user on 2018-11-25.
 */

public class PartFragment extends Fragment {
    Button abs_btn, quads_btn, arm_btn, hip_btn, chest_btn, back_btn;
    static public ArrayList<ItemForSending> planList;

    public PartFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_part, container, false);
        Intent in = getActivity().getIntent();
        if(in.getBooleanExtra("initialize", true) == true)
            planList = new ArrayList<ItemForSending>();

        abs_btn = (Button)view.findViewById(R.id.abs_btn);
        quads_btn = (Button)view.findViewById(R.id.quads_btn);
        arm_btn = (Button)view.findViewById(R.id.arm_btn);
        hip_btn = (Button)view.findViewById(R.id.hip_btn);
        chest_btn = (Button)view.findViewById(R.id.chest_btn);
        back_btn = (Button)view.findViewById(R.id.back_btn);
        abs_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("part", "Abs");
                startActivity(intent);
            }
        });
        quads_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("part", "Quads");
                startActivity(intent);
            }
        });
        arm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("part", "Arm");
                startActivity(intent);
            }
        });
        hip_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("part", "Glutes");
                startActivity(intent);
            }
        });
        chest_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("part", "Chest");
                startActivity(intent);
            }
        });
        back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), MainActivity.class);
                intent.putExtra("part", "Back");
                startActivity(intent);
            }
        });
        return view;
        //return inflater.inflate(R.layout.activity_part, container, false);
    }




}
