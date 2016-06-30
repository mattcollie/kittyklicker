package com.kitty.kittyklicker.enums;

public enum KittyUpgradeEnum {

    BASIC_KITTY("Regular kitty", 10, 0.4),

    YOUNG_KITTY("Young kitty", 100, 4.0),

    OLD_KITTY("Old kitty", 1100, 32.0),

    SNEAKY_KITTY("Sneaky kitty", 12000, 188.0),

    SUPER_KITTY("Super kitty", 130000, 1040.0),

    WISE_KITTY("Wise kitty", 1400000, 5600.0),

    MASTER_KITTY("Master kitty", 20000000, 3120.0),

    KITTY_OVERLORD("Kitty overlord", 330000000, 176000.0);

    private final String upgradeName;
    private final int cost;
    private final double amountPerSec;

    KittyUpgradeEnum(String upgradeName, int cost, double amountPerSec) {
        this.upgradeName = upgradeName;
        this.cost = cost;
        this.amountPerSec = amountPerSec;
    }

    public String getName() {
        return upgradeName;
    }

    public int getCost() {
        return cost;
    }

    public double getAmountPerSec() {
        return amountPerSec;
    }
}
