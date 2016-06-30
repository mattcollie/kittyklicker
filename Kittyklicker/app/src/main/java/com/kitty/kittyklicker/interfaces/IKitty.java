package com.kitty.kittyklicker.interfaces;

import com.kitty.kittyklicker.enums.KittyUpgradeEnum;

/**
 * Created by Mark on 22/06/2016.
 */
public interface IKitty {
    void buyUpgrade(KittyUpgradeEnum upgrade, int upgradeIndex);
    void incrementCount();
    int getUpgradeAmount(int upgradeIndex);
}