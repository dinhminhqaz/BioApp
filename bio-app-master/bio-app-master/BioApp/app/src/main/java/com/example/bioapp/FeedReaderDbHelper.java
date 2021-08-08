package com.example.bioapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class FeedReaderDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "Tienloi1.db";

    public FeedReaderDbHelper(Context context) {
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }


    private static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME + " (" +
                    FeedReaderContract.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_1 + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_2 + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_3 + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_4 + " TEXT)";

    private static final String SQL_CREATE_ENTRIES_USER =
            "CREATE TABLE " + FeedReaderContract.FeedEntry.TABLE_NAME_USER + " (" +
                    FeedReaderContract.FeedEntry._ID_USER + " INTEGER PRIMARY KEY," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_1_USER + " TEXT," +
                    FeedReaderContract.FeedEntry.COLUMN_NAME_2_USER + " TEXT)";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedReaderContract.FeedEntry.TABLE_NAME;



    @Override
    public void onCreate(@org.jetbrains.annotations.NotNull SQLiteDatabase db) {

        //db.execSQL(SQL_CREATE_ENTRIES);
        db.execSQL(SQL_CREATE_ENTRIES_USER);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db,int oldVersion, int newVersion) {
        onUpgrade(db,oldVersion,newVersion);
    }

    public boolean addOne(TreeInfo treeInfo){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_1,treeInfo.getName());
        cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_2,treeInfo.getDescription());
        cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_3,treeInfo.getGood());
        cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_4,treeInfo.getBad());

        long insert = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME, null, cv);
        if (insert == -1){
            return false;
        } else {
            return true;
        }
    }

    public List<TreeInfo> getAllTree(String plantname){
        List<TreeInfo> resultList = new ArrayList<TreeInfo>();


        String queryString = "SELECT * FROM tree2 WHERE PlantName LIKE '%"+plantname+"%'";

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        if (cursor.moveToFirst()) {
            do {
                int TreeId = cursor.getInt(0);
                int imgageId;
                String TreeName = cursor.getString(1);
                String TreeDescription = cursor.getString(2);
                String TreeGood = cursor.getString(3);
                String TreeBad = cursor.getString(4);

                imgageId = 0;

                switch (TreeId) {
                    case 6:
                        imgageId=R.drawable.spi;
                        break;

                    case 7:
                        imgageId=R.drawable.bok;
                        break;

                    case 8:
                        imgageId=R.drawable.brocoli;
                        break;

                    case 9:
                        imgageId=R.drawable.cauliflower;
                        break;

                    case 10:
                        imgageId=R.drawable.cabbage;
                        break;

                    case 11:
                        imgageId=R.drawable.lettuce;
                        break;

                    case 12:
                        imgageId=R.drawable.swiss;
                        break;
                }

                TreeInfo newTree = new TreeInfo(imgageId,TreeId,TreeName,TreeDescription,TreeGood,TreeBad);

                resultList.add(newTree);
            } while (cursor.moveToNext());
        } else {

        }

        cursor.close();
        db.close();
        return resultList;
    }

    public boolean Login(String username, String password){
        String queryString = "SELECT * FROM user WHERE Name = '"+username+"' AND Pass = '"+password+"'";
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(queryString,null);

        if (cursor.moveToFirst()) {
            return true;
        } else {
            return false;
        }
    }

    public boolean Register(String username, String password){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_1_USER,username);
        cv.put(FeedReaderContract.FeedEntry.COLUMN_NAME_2_USER,password);

        long insert = db.insert(FeedReaderContract.FeedEntry.TABLE_NAME_USER, null, cv);
        if (insert == -1){
            return false;
        } else {
            return true;
        }
    }

    public String editTree (TreeInfo treeInfo) {

        String PlantName = treeInfo.getName();
        String Description = treeInfo.getDescription();
        String Good = treeInfo.getGood();
        String Bad = treeInfo.getBad();

        String queryString = "UPDATE tree2 SET PlantName = "+"'"+PlantName
                            +"'"+", Description = "+"'"+Description
                            +"'"+", Good = "+"'"+Good
                            +"'"+", Bad = "+"'"+Bad
                            +"'"+" WHERE Indx ="+"'"+Integer.toString(treeInfo.getId())+"'";

        ContentValues cv = new ContentValues();
        cv.put("PlantName",PlantName);
        cv.put("Description",Description);
        cv.put("Good",Good);
        cv.put("Bad",Bad);

        SQLiteDatabase db = this.getWritableDatabase();
        db.update(FeedReaderContract.FeedEntry.TABLE_NAME,cv,"Indx = "+Integer.toString(treeInfo.getId()),null);

        return queryString;
    }
}
