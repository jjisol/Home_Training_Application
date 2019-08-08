package org.techtown.sportsdata;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by user on 2018-12-01.
 */

public class TabPagerAdapter extends FragmentStatePagerAdapter{
    private int tabCount;

    public TabPagerAdapter(FragmentManager fm, int tabCount){
        super(fm);
        this.tabCount = tabCount;
    }

    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                PartFragment partFragment = new PartFragment();
                return partFragment;
            case 1:
                ReservationFragment reservationFragment = new ReservationFragment();
                return reservationFragment;
            case 2:
                RecordFragment recordFragment = new RecordFragment();
                return recordFragment;
            default:
                return null;
        }
    }

    @Override
    public int getCount(){
        return tabCount;
    }
}
