package com.kitty.kittyklicker;

import android.os.Bundle;
import android.os.Handler;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.kitty.kittyklicker.enums.KittyUpgradeEnum;
import com.kitty.kittyklicker.interfaces.IKitty;
import com.kitty.kittyklicker.tabswipe.adapters.TabsPagerAdapter;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener, IKitty {

    private TextView textViewTitle;
    private TextView textViewIncrease;

    private double increasePerSec = 0;
    private double kittyCounterAccurate = 0;
    private long kittyCounter = 0;

    private int[] upgradeAmounts = new int[KittyUpgradeEnum.values().length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Get viewpager and action bar and tab adapter
        ActionBar actionBar;
        TabsPagerAdapter mAdapter;
        ViewPager viewPager;

        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());
        viewPager = (ViewPager) findViewById(R.id.viewPagerTabs);


        // Set the view pager adapter and action bar settings
        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.hide();

        // Find text controls of counter and increase
        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        textViewIncrease = (TextView) findViewById(R.id.textViewIncrease);

        textViewTitle.setText(getString(R.string.kitty_count, kittyCounter));
        textViewIncrease.setText(getString(R.string.increase_count, increasePerSec));

        // Initialise upgradeAmounts
        for(int i = 0; i < upgradeAmounts.length; i++) {
            upgradeAmounts[i] = 0;
        }

        // Start the Timer
        timerHandler.postDelayed(timerRunnable, 0);
    }

    public int getUpgradeAmount(int upgradeIndex) {
        return upgradeAmounts[upgradeIndex];
    }

    @Override
    public void incrementCount() {
        kittyCounterAccurate += Math.max(1, (int) (increasePerSec/10));
        updateCounter();
    }

    public void buyUpgrade(KittyUpgradeEnum upgrade, int upgradeIndex) {
        int upgradeCost = (int) (upgrade.getCost() * Math.pow(1.15, upgradeAmounts[upgradeIndex]));
        double upgradePerSec = upgrade.getAmountPerSec();

        if (kittyCounterAccurate >= upgradeCost) {
            kittyCounterAccurate -= upgradeCost;

            upgradeAmounts[upgradeIndex]++;

            increasePerSec += upgradePerSec;

            increasePerSec = Math.round(increasePerSec*10000)/10000.0;  //rounds to 4 d.p to remove double inaccuracies

            textViewIncrease.setText(getString(R.string.increase_count, increasePerSec));
            updateCounter();
        }
    }

    private void updateCounter() {
        kittyCounter = Math.round(kittyCounterAccurate);
        textViewTitle.setText(getString(R.string.kitty_count, kittyCounter));
    }

    //Timer
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            kittyCounterAccurate += increasePerSec/10;

            updateCounter();

            timerHandler.postDelayed(this, 100);
        }
    };


    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }
}
