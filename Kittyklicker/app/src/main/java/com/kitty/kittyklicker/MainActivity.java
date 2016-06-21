package com.kitty.kittyklicker;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.widget.TextView;

import com.kitty.kittyklicker.interfaces.IKitty;
import com.kitty.kittyklicker.tabswipe.adapters.TabsPagerAdapter;


public class MainActivity extends FragmentActivity implements ActionBar.TabListener, IKitty {

    private ViewPager viewPager;
    private TabsPagerAdapter mAdapter;
    private ActionBar actionBar;
    private TextView textViewTitle;

    private int _kittyCounter = 0;

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

    }

    @Override
    public int getCount() {
        return _kittyCounter;
    }

    //meme - here come dat kitty; o meow whaddup
    @Override
    public void incremementCount() {
        _kittyCounter++;
        updateCounter();
    }

    private void updateCounter() {
        textViewTitle.setText("Kitty count: " + _kittyCounter);
    }

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
