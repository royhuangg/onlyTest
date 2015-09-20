/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roy.test;

import android.content.Context;
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
import grandroid.action.AsyncAction;
import grandroid.adapter.JSONAdapter;
import grandroid.adapter.ObjectAdapter;
import grandroid.net.Mon;
import grandroid.view.LayoutMaker;
import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author ROY
 */
public class testPullToFresh extends SampleFace implements SwipeRefreshLayout.OnRefreshListener {
    protected JSONAdapter jadapter;
    protected JSONObject object = null;
    ArrayAdapter<String> aa;
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
        new AsyncAction<JSONArray>(this) {

                @Override
                public void afterExecution(JSONArray result) {
                    jadapter.setArray(result);
                    jadapter.notifyDataSetChanged();
                }

                @Override
                public boolean execute(Context context) {
                    JSONObject obj = null;
                    try {
                        obj = new Mon("http://www.json-generator.com/api/json/get/bUpnyUCUSW?indent=2").sendAndWrap();
                        setResult(obj.getJSONArray("users"));
                    } catch (Exception exception) {
                    }

                    return true;
                }
            }.cancelable().execute();
        jadapter = new JSONAdapter(this, new JSONArray()) {

                            @Override
                            public View createRowView(int index, JSONObject item) {
                                LinearLayout layout = new LinearLayout(context);
                                final LayoutMaker m = new LayoutMaker(context, layout, false);
                                m.addRowLayout(false, m.layFW());
                                {
                                    m.add(m.createStyledText("").tag("id").center().size(14).color(Color.rgb(102, 139, 139)).get(), m.layFW(5));
                                    m.add(m.createStyledText("").tag("name").center().size(11).get(), m.layFW(4));
                                    m.add(m.createStyledText("").tag("age").center().size(13).get(), m.layFW(5));
                                    m.add(m.createStyledText("").tag("gender").center().size(13).get(), m.layFW(5));
                                    m.add(m.createStyledText("").tag("team").center().size(12).get(), m.layFW(4));
                                   // Log.i("test","index is : "+String.valueOf(index) );
                                    
                                    m.escape();
                                }
                                return layout;
                            }

                            @Override
                            public void fillRowView(int index, View cellRenderer, JSONObject item) throws JSONException {
                                findView(cellRenderer, "id", TextView.class).setText(item.getString("userId"));
                                findView(cellRenderer, "name", TextView.class).setText(item.getString("name"));
                                findView(cellRenderer, "age", TextView.class).setText(item.getString("age"));
                                findView(cellRenderer, "gender", TextView.class).setText(item.getString("gender"));
                               // findView(cellRenderer, "team", TextView.class).setText(item.getString("team"));
                                findView(cellRenderer, "team", TextView.class).setText(String.valueOf(index));
                            }

                        };
        list.add("Hello");
        list.add("This is stormzhang");
        list.add("An Android Developer");
        list.add("Love Open Source");
        list.add("My GitHub: stormzhang");
        list.add("weibo: googdev");
        // aa=new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        //mListView = (ListView) findViewById(R.id.listview);
        //    mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getDataa()));
        //mSwipeLayout = (SwipeRefreshLayout) findViewById(R.id.swipe_container);
        mSwipeLayout = new SwipeRefreshLayout(this);
        mSwipeLayout.setOnRefreshListener(this);
        mListView = new ListView(this);
        // mListView.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, getDataa()));
        mListView.setAdapter(jadapter);
        mSwipeLayout.addView(mListView);

        mSwipeLayout.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light, android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        maker.addColLayout(false, maker.layFW());
        {
//            maker.getLastLayout().setBackgroundColor(Color.YELLOW);
            Config.logi("test");
            maker.add(mSwipeLayout, maker.layFF());
            //maker.add(mListView,maker.layWW(0));

        }

        adapter = new ObjectAdapter<String>(this, list) {
            @Override
            public View createRowView(int index, String item) {
                LinearLayout layout_main = new LinearLayout(context);
                LayoutMaker m = new LayoutMaker(context, layout_main, false);
                m.addColLayout(false, m.layFF(1));
                {
                    m.add(m.createButton("test"), m.layFW(0));
                    m.add(m.createStyledText("123").color(Color.WHITE).get(), m.layFW(0));
                }
                Config.logi("create row");
                return layout_main;
            }

            @Override
            public void fillRowView(int index, View cellRenderer, String item) {
                //findView(cellRenderer, "fruit", TextView.class).setText(item);
                Config.logi("fill row");
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
