/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roy.test;

import android.util.Log;

/**
 *
 * @author ROY
 */
public class Config {

    public static int SCREEN_WIDTH = 640;
    public static int TEXT_SIZE = 24;
    public static int MAX_LENGTH = 5;
    public static String DATABASE_NAME = "testDataBase";
    public static String TABLE_NAME = "test";

    public static void logi(String log) {
        Log.i("onlyTest", log);

    }

}
