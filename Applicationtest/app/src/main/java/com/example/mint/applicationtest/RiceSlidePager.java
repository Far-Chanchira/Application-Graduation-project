package com.example.mint.applicationtest;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by Mint on 8/6/2017.
 */

public class RiceSlidePager extends FragmentStatePagerAdapter {
    public int numPage;

    public RiceSlidePager(FragmentManager fm, int numPage) {
        super(fm);
        this.numPage = numPage;
    }

    // RiceNewsFragment page1=null;
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                RiceNewsFragment page1 = new RiceNewsFragment();
                return page1;
            case 1:
                RiceLightFragment page2 = new RiceLightFragment();
                return page2;

            case 2:
                RiceMiddleFragment page3 = new RiceMiddleFragment();
                return page3;

            case 3:
                RiceHeavyFragment page4 = new RiceHeavyFragment();
                return page4;
            default:
                return null;

        }
        //return null;
    }

    @Override
    public int getCount() {
        return numPage;
    }
}
