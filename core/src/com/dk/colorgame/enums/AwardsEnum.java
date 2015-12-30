package com.dk.colorgame.enums;

/**
 * Created by Dawid Kotarba on 2015-05-12.
 */
public enum AwardsEnum {

    NICE("Nice!"),
    GOOD("Good!"),
    GREAT("Great!"),
    WOW("Wow!"),
    COLOR_SPREE("Color Spree!");

    private final String award;

    AwardsEnum(String award) {
        this.award = award;
    }

    public String getAward() {
        return award;
    }
}
