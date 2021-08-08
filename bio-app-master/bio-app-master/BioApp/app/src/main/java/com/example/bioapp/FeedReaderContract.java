package com.example.bioapp;

import android.provider.BaseColumns;

public final class FeedReaderContract {
    private FeedReaderContract(){}

    public class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "tree2";
        public static final String _ID = "Indx";
        public static final String COLUMN_NAME_1 = "PlantName";
        public static final String COLUMN_NAME_2 = "Description";
        public static final String COLUMN_NAME_3 = "Good";
        public static final String COLUMN_NAME_4 = "Bad";

        public static final String TABLE_NAME_USER = "user";
        public static final String _ID_USER = "Indx";
        public static final String COLUMN_NAME_1_USER = "Name";
        public static final String COLUMN_NAME_2_USER = "Pass";
    }
}
