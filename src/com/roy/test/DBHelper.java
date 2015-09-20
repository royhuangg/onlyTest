/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roy.test;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 * @author ROY
 */
public class DBHelper extends SQLiteOpenHelper {

    private final static int _DBVersion = 1;
    private final static String _DBName = "testDataBase.db";
    private final static String _TableName = "test";

    public DBHelper(Context context) {
        super(context, _DBName, null, _DBVersion);
        // TODO Auto-generated constructor stub		
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        final String SQL = "CREATE TABLE IF NOT EXISTS " + _TableName + "( "
                + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "name INTEGER, "
                + "address TEXT,"
                + "time INTEGER(10)"
                + ");";
        db.execSQL(SQL);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        final String SQL = "DROP TABLE " + _TableName;
        db.execSQL(SQL);
    }

}
