/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roy.test;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;
import com.viewpagerindicator.TabPageIndicator;
import grandroid.adapter.SimpleRowAdapter;
import grandroid.adapter.ViewPagerAdapter;

/**
 *
 * @author ROY
 */
public class testTabIndicator extends SampleFace {

    private static final String[] CONTENT = new String[]{"头条", "娱乐", "体育", "财经", "科技", "汽车", "NBA"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        //ViewPager vp = (ViewPager)findViewById(R.id.)
        TabPageIndicator tpi = maker.add(new TabPageIndicator(this), maker.layWF(0));
        FragmentPagerAdapter adapter = new WYNewsAdapter(getSupportFragmentManager());


        maker.addColLayout(false, maker.layFF());
        {

            //maker.add(tpi, maker.layWF(0));
           // maker.add(vp, maker.layFF(0));
         //  vp.setAdapter(adapter);
        }

    }

    class WYNewsAdapter extends FragmentPagerAdapter {

        public WYNewsAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return ContentFragment.newInstance(CONTENT[position % CONTENT.length]);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return CONTENT[position % CONTENT.length].toUpperCase();
        }

        @Override
        public int getCount() {
            return CONTENT.length;
        }
    }
}
