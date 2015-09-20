/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roy.test;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import grandroid.adapter.ObjectAdapter;
import grandroid.view.LayoutMaker;
import java.util.ArrayList;

/**
 *
 * @author ROY
 */
public class testPullToFresh extends SampleFace implements SwipeRefreshLayout.OnRefreshListener {

    ObjectAdapter adapter;
    private SwipeRefreshLayout mSwipeLayout;
    private ListView mListView;
    private ArrayList<String> list = new ArrayList<String>();
    ImageView iv;
    TextView tv;

//    String imgUrl = "http://img.youtube.com/vi/YzRLI_PqzxY/maxresdefault.jpg";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        list.add("Hello");
        list.add("This is stormzhang");
        list.add("An Android Developer");
        list.add("Love Open Source");
        list.add("My GitHub: stormzhang");
        list.add("weibo: googdev");

        //mListView = (ListView) findViewById(R.id.listview);
        //    mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getDataa()));
        //mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        mSwipeLayout = new SwipeRefreshLayout(this);
        mSwipeLayout.setOnRefreshListener(this);
        mListView = new ListView(this);
        // mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getDataa()));
        mListView.setAdapter(adapter);
        mSwipeLayout.addView(mListView);

        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        maker.addColLayout(false, maker.layFW());
        {
//            maker.getLastLayout().setBackgroundColor(Color.YELLOW);
            maker.add(mSwipeLayout, maker.layFF());
            //maker.add(mListView,maker.layWW(0));
        }

        adapter = new ObjectAdapter<String>(this, list) {
            @Override
            public View createRowView(int index, String item) {
                LinearLayout layout_main = new LinearLayout(context);
                LayoutMaker m = new LayoutMaker(context, layout_main, false);
                m.addColLayout(false, m.layFF());
                {
                    m.add(m.createStyledText("123").color(Color.WHITE).get(), m.layFW(0));
                }
                return layout_main;
            }

            @Override
            public void fillRowView(int index, View cellRenderer, String item) {
                //findView(cellRenderer, "fruit", TextView.class).setText(item);
                Log.e("list", item);
            }
        };

    }

    public void onRefresh() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mSwipeLayout.setRefreshing(false);
            }
        }, 3000);
    }

}
