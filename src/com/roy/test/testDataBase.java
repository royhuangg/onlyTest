package com.roy.test;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import grandroid.action.ToastAction;
import grandroid.adapter.FaceDataAdapter;
import grandroid.database.FaceData;
import grandroid.database.GenericHelper;
import grandroid.view.LayoutMaker;
import grandroid.view.StyledText;
import com.roy.test.SampleFace;
import grandroid.app.AppInitializer;
import grandroid.database.TypeMapping;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class testDataBase extends SampleFace {

    protected FaceData faceData;
    protected FaceDataAdapter adapter;
    protected GenericHelper<GroupData> helper;
    protected Cursor cursor;
    protected String[][] array = {{"Lisa", "B"}, {"Jack", "A"}, {"Bob", "C"}};
    protected TextView showView, showValueOne, showValueTwo;
    protected AppInitializer initializer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initFromAssets("testDataBase.db");
        DBHelper mHelper = new DBHelper(testDataBase.this);
        SQLiteDatabase mDB = mHelper.getWritableDatabase();
       // mDB.execSQL("select * from test");
        // Query by raw SQL
        mDB = mHelper.getWritableDatabase(); // mDB = mHelper.getReadableDatabase();
        Cursor cursor = mDB.rawQuery("select * from test", null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Log.e("SQLiteDBTestingActivity", "_ID = " + cursor.getString(1));
            //Config.logi("SQLiteDBTestingActivity", "_ID = " + cursor.getInt(0));
          //  Log.e("SQLiteDBTestingActivity", "_DATA = " + cursor.getString(1));
            //Log.e("SQLiteDBTestingActivity", "_DATETIME = " + cursor.getString(2).substring(0, 16));
            cursor.moveToNext();
        }
        
        faceData = new FaceData(this, "testDataBase.db");
        //faceData=(FaceData)db;
        helper = new GenericHelper<GroupData>(faceData, GroupData.class);
        //faceData.addColumn(Config.DATABASE_NAME, "test", TypeMapping.DOUBLE);
        helper.getTableName();
        Config.logi(helper.getTableName().toString());
        ArrayList<GroupData> searchResult = new ArrayList<GroupData>();
       //searchResult.add(helper.select("where name='roy' "));
        //String s= searchResult.get(0).toString();
        /*GroupData a = new GroupData();
        a.set_id(2);
        a.setName("tes");
        a.setAddress("111");*/
       // helper.insert(a);
        helper.select();
        Config.logi("s=" + helper.select().get(0).getAddress().toString());
      //helper.getFaceData().exec("select * from test where name='roy';");
        //helper.getFaceData().
        //Config.logi("select:"+helper.getFaceData().exec("select * from test where name='roy';"));
        maker.disableKeyboardFocus();
        maker.addColLayout(false, maker.layFF());
        {
            //displayComment(maker, this, "use demo", LinearLayout.LayoutParams.WRAP_CONTENT);
            maker.addRowLayout(false, maker.layFW());
            {
                maker.addColLayout(false, maker.layFW(2));
                {

                    maker.add(maker.createButton("Execute"), maker.layFW(1)).setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            showView.setText("Insert into Table values (value1,value2,value3,...valueN)");
                            execEntrance();
                        }
                    });

                    maker.add(maker.createButton("Query"), maker.layFW(1)).setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            showView.setText("Select * from Table where col_name = 'value1'");
                            queryEntrance();
                        }

                    });
                    maker.add(maker.createButton("Clear"), maker.layFW(1)).setOnClickListener(new View.OnClickListener() {

                        public void onClick(View v) {
                            showView.setText("Delete from table_name");
                            truncateData();
                        }
                    });

                    maker.escape();
                }
                maker.addColLayout(false, maker.layFF(1));
                {
                    maker.addRowLayout(false, maker.layFW(1)).setGravity(Gravity.CENTER);
                    {
                        maker.add(maker.createStyledText("Value1: ").color(Color.WHITE).center().get(), maker.layFW(1));
                        showValueOne = maker.add(maker.createStyledEdit("").bgc(Color.WHITE).hint("random").maxLine(1).format(StyledText.Format.English).get(), maker.layFW(1));
                        maker.escape();
                    }
                    maker.addRowLayout(false, maker.layFW(1)).setGravity(Gravity.CENTER);
                    {
                        maker.add(maker.createStyledText("Value2: ").color(Color.WHITE).center().get(), maker.layFW(1));
                        showValueTwo = maker.add(maker.createStyledEdit("").bgc(Color.WHITE).hint("random").maxLine(1).format(StyledText.Format.English).get(), maker.layFW(1));
                        maker.escape();
                    }
                    maker.escape();
                }

                maker.escape();
            }
            maker.addColLayout(false, maker.layAbsolute(0, 0, maker.getDisplayAgent().getWidth(), 50));
            {
                showView = maker.add(maker.createStyledText("").size(14).center().color(Color.GRAY).get(), maker.layFF());
                maker.escape();
            }

            maker.addColLayout(false, maker.layFW(1));
            {

                adapter = new FaceDataAdapter<GroupData>(this, helper) {

                    @Override
                    public View createRowView(int index, GroupData item) {
                        LinearLayout layout_main = new LinearLayout(context);
                        LayoutMaker m = new LayoutMaker(context, layout_main, false);
                        m.add(m.createStyledText("").tag("ID").center().color(Color.WHITE).get(), m.layFW(1));
                        m.add(m.createStyledText("").tag("name").center().color(Color.WHITE).get(), m.layFW(1));
                         m.add(m.createStyledText("").tag("address").center().color(Color.WHITE).get(), m.layFW(1));
                        return layout_main;
                    }

                    @Override
                    public void fillRowView(int index, View cellRenderer, GroupData item) {
                        findView(cellRenderer, "ID", TextView.class).setText(item.get_id().toString());
                        findView(cellRenderer, "name", TextView.class).setText(item.getName());
                        findView(cellRenderer, "address", TextView.class).setText(item.getAddress());
                    }
                };
                maker.addListView(adapter, maker.layFF());
                maker.escape();
            }
            maker.escape();
        }
        /*  maker.addColLayout(false, maker.layFF(0));
         {
         maker.getLastLayout().setBackgroundColor(Color.YELLOW);
         }*/

    }

    public void execData(String one, String two) {
        adapter.requery("");
        faceData.exec("Insert Into GroupData(name,team)Values('" + one + "','" + two + "')");
        adapter.refresh();
        new ToastAction(testDataBase.this).setMessage("Random execute success").execute();

    }

    public void truncateData() {
        adapter.requery("");
        //faceData.truncate("GroupData");
        adapter.refresh();
        new ToastAction(testDataBase.this).setMessage("Clear success").execute();
    }

    public void queryData(String s) {
        adapter.requery("where name = '" + s + "'");
        adapter.refresh();
        new ToastAction(testDataBase.this).setMessage("Select success").execute();
    }

    public void execEntrance() {
        if (showValueOne.getText().length() > 0 || showValueTwo.getText().length() > 0) {
            execData(showValueOne.getText().toString(), showValueTwo.getText().toString());
        } else {
            int i = (int) (Math.random() * 3);
            String one = array[i][0];
            String two = array[i][1];
            execData(one, two);
        }
    }

    public void queryEntrance() {
        if (adapter.isEmpty()) {
            new ToastAction(testDataBase.this).setMessage("Nothing cannot been selected in result").execute();
        } else {
            if (showValueOne.getText().length() > 0) {
                queryData(showValueOne.getText().toString());
            } else {
                new ToastAction(testDataBase.this).setMessage("query failed").execute();
            }
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy(); //To change body of generated methods, choose Tools | Templates.
        helper.drop();
        Log.e("cus", "onDestroy");
    }

    //init database
    public boolean initFromAssets(String DBName) {
        initializer = new AppInitializer(this);

        try {
            File f = new File(initializer.getDatabaseDir(), DBName);
            if (f.exists()) {
                f.delete();
                Toast.makeText(this, "exists! delete database", Toast.LENGTH_SHORT).show();
            }
            initializer.syncAsset(DBName, initializer.getDatabaseDir());
            //Config.logi(initializer.);
            //Toast.makeText(this, "init delete database", Toast.LENGTH_SHORT).show();
            return true;
        } catch (IOException ex) {
            Config.logi("ex:" + ex);
            return false;
        }
    }
}
