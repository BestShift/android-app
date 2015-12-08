package com.example.bestshift_as;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.example.bestshift_as.Kommfort.Kommfort;
import com.example.bestshift_as.Shift.Shift;
import com.example.bestshift_as.Verbrauch.Verbrauch;

/**
 * Created by Fitim on 23.11.2015.
 */
public class FragementPageAdapter extends FragmentPagerAdapter{
    public FragementPageAdapter(FragmentManager fm) {
        super(fm);
    }



    @Override
    public Fragment getItem(int arg0) {
        switch (arg0) {
            case 0:
                return new Verbrauch();
            case 1:
                return new Kommfort();
            case 2:
                return new Shift();
            default:
                break;
        }
            return null;

    }

    @Override
    public int getCount() {
        return 3;
    }
}
