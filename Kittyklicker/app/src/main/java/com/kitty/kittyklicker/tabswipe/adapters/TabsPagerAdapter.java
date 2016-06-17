package com.kitty.kittyklicker.tabswipe.adapters;

import com.kitty.kittyklicker.tabswipe.fragments.MainFragment;
import com.kitty.kittyklicker.tabswipe.fragments.UpgradeFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class TabsPagerAdapter extends FragmentPagerAdapter {

    public TabsPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                // Main fragment activity
                return new MainFragment();
            case 1:
                // Upgrade fragment activity
                return new UpgradeFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        // get item count - equal to number of tabs
        return 2;
    }

}