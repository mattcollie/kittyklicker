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

    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    private TextView textViewTitle;

    private double _kittyCounterAccurate = 0;
    private long _kittyCounter = 0;
    private double increasePerSec = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get viewpager and action bar and tab adapter
        viewPager = (ViewPager) findViewById(R.id.viewPagerTabs);
        actionBar = getActionBar();
        mAdapter = new TabsPagerAdapter(getSupportFragmentManager());

        // set the view pager adapter and action bar settings
        viewPager.setAdapter(mAdapter);
        actionBar.setHomeButtonEnabled(false);
        actionBar.hide();

        // find text control and set counter to the value of the count
        textViewTitle = (TextView) findViewById(R.id.textViewTitle);
        textViewTitle.append(" " + _kittyCounter);

        //starts the Timer
        timerHandler.postDelayed(timerRunnable, 0);
    }

    @Override
    public long getCount() {
        return _kittyCounter;
    }

    //meme - here come dat kitty; o meow whaddup
    @Override
    public void incremementCount() {
        _kittyCounterAccurate += 1;
        updateCounter();
    }

    public void buyUpgrade(KittyUpgradeEnum upgrade){
        switch(upgrade){
            case basicKitty:
                buyUpgrade(10, 2);
                break;
            case youngKitty:
                break;
            case oldKitty:
                break;
            case mediumKitty:
                break;
            case advancedKitty:
                break;
            case superKitty:
                break;
            case extremeKitty:
                break;
            default:
                break;
        }
    }

    private void buyUpgrade(int upgradeCost, double upgradePerSec) {
        if (_kittyCounterAccurate >= upgradeCost) {
            _kittyCounterAccurate -= upgradeCost;
            increasePerSec += upgradePerSec;

            increasePerSec = Math.round(increasePerSec*10000)/10000.0;  //rounds to 4 d.p

            //upgradeText.setText("Upgrades: " + increasePerSec);
            updateCounter();
        }
    }

    private void updateCounter() {
        _kittyCounter = Math.round(_kittyCounterAccurate);
        textViewTitle.setText("Kitty count: " + _kittyCounter);
    }

    //Timer
    Handler timerHandler = new Handler();
    Runnable timerRunnable = new Runnable() {

        @Override
        public void run() {
            _kittyCounterAccurate += increasePerSec/10;

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
