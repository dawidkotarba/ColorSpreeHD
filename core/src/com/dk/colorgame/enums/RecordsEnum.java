package com.dk.colorgame.enums;

/**
 * Created by Dawid Kotarba on 2015-06-11.
 */
public enum RecordsEnum {

    POINTS_RECORD("Max swipe score"),
    SELECTION_RECORD("Max blocks connected"),
    POINTS_SELECTION_RECORD("Max swipe blocks & score");

    private final String record;

    RecordsEnum(String record) {
        this.record = record;
    }

    public String getRecord() {
        return record;
    }
}
