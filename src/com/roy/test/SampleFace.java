package com.roy.test;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;
import com.roy.test.Config;
import grandroid.view.Face;
import grandroid.view.LayoutMaker;

public class SampleFace extends Face {

    protected LayoutMaker maker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        maker = new LayoutMaker(this);
        maker.setDrawableDesignWidth(this, Config.SCREEN_WIDTH);

    }

    protected void displayComment(LayoutMaker maker, Activity context, int res) {
        displayComment(maker, context, res, LinearLayout.LayoutParams.WRAP_CONTENT);
    }

    protected void displayComment(LayoutMaker maker, Activity context, int res, int h) {
        maker.add(maker.createStyledText(context.getString(res)).color(Color.GRAY).padding(20, 20, 20, 20).size(Config.TEXT_SIZE).get(), maker.layAbsolute(0, 0, Config.MAX_LENGTH, h));
    }

    protected void displayLine(LayoutMaker maker) {
        maker.addColLayout(false, maker.layAbsolute(0, 0, Config.MAX_LENGTH, 2));
        {
            maker.addLine(Color.GRAY, 2);
            maker.escape();
        }
    }

    protected void displayListLine(LayoutMaker maker) {
        maker.addColLayout(false, maker.layAbsolute(0  , 0, Config.MAX_LENGTH, 1)).setPadding(20, 0, 20, 0);
        {
            maker.addLine(Color.GRAY);
            maker.escape();
        }
    }
}
