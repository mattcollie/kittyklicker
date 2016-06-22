package com.kitty.kittyklicker.interfaces;

/**
 * Created by Mark on 22/06/2016.
 */
public interface IKitty {
    public long getCount();
    public void incremementCount();
    public void buyUpgrade(int upgradeCost, double upgradePerSec);
}
