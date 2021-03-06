package com.autochip.myvehicle;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;


class SectionsPagerAdapter extends FragmentStatePagerAdapter {

    //integer to count number of tabs
    private int tabCount;
    private FragmentManager fragmentManager;
    private int viewHeight;

    SectionsPagerAdapter(FragmentManager fragmentManager, int tabCount, int viewHeight) {
        super(fragmentManager);
        this.fragmentManager = fragmentManager;
        //Initializing tab count
        this.tabCount = tabCount;
        this.viewHeight = viewHeight;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        //Returning the current tabs
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = RegisterVehicleFragment.newInstance("", "");
                break;
            case 1:
                fragment = InsuranceFragment.newInstance(String.valueOf(viewHeight), "");
                break;
            case 2:
                fragment = EmissionFragment.newInstance(String.valueOf(viewHeight), "");
                break;
            case 3:
                fragment = RCFCFragment.newInstance(String.valueOf(viewHeight), "");
                break;
            case 4:
                fragment = ServiceHistoryFragment.newInstance(String.valueOf(viewHeight), "");
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return tabCount;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return "Vehicle Info";
            case 1:
                return "Insurance";
            case 2:
                return "Emission";
            case 3:
                return "RC/FC";
            case 4:
                return "Service History";
        }
        return null;
    }
}
