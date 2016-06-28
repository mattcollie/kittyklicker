package com.kitty.kittyklicker.interfaces;

import com.kitty.kittyklicker.enums.KittyUpgradeEnum;

/**
 * Created by Mark on 22/06/2016.
 */
public interface IKitty {
    public long getCount();
    public void incremementCount();
    public void buyUpgrade(KittyUpgradeEnum upgrade);
}