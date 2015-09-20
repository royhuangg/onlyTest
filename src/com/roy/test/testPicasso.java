/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roy.test;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import static com.google.android.gms.internal.b.cm;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;

/**
 *
 * @author ROY
 */
public class testPicasso extends SampleFace {

    private static final String[] CONTENT = new String[]{"头条", "娱乐", "体育", "财经", "科技", "汽车", "NBA"};
    ImageView iv;
    TextView tv;
    
//    String imgUrl = "http://img.youtube.com/vi/YzRLI_PqzxY/maxresdefault.jpg";
    String imgUrl ="https://scontent-tpe1-1.xx.fbcdn.net/hphotos-xpf1/v/t1.0-9/1484120_703037516382811_1930426869_n.jpg?oh=ab0380e7d1bdf9bae33fd80ebdec6a0a&oe=5661C0EB";
    CropSquareTransformation cst=new CropSquareTransformation();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        maker.addColLayout(false, maker.layFW());
        {
            iv = maker.addImage(R.drawable.ic_launcher, maker.layFF(0));
            tv=maker.addTextView("");
            //maker.add(maker.createStyledText("ff").nowrap().get(),maker.layFF());
        }
        //Picasso.with(this).load().into(iv);
        Picasso.with(this)
                .load(imgUrl)
                .resize(480, 360)
              //  .transform(cst)
             //   .centerCrop()
              //  .fit()
                .placeholder(R.drawable.ic_launcher)
                .error(R.drawable.ic_launcher)
                .into(iv);
        iv.setOnLongClickListener(new View.OnLongClickListener() {

            public boolean onLongClick(View v) {
                
                ClipboardManager cmb = (ClipboardManager) getSystemService(Context.CLIPBOARD_SERVICE);
                cmb.setPrimaryClip(ClipData.newPlainText("data", "Jack"));
                ClipData cd=cmb.getPrimaryClip();
                
                cmb.setText("Text place in ClipboardManager");
                tv.setText(cmb.getText()+cd.getItemAt(0).getText().toString());
                return false;
                
            }
        });
        
        
        
        
        
    }

    public  class CropSquareTransformation implements Transformation {

        @Override
        public Bitmap transform(Bitmap source) {
            int size = Math.min(source.getWidth(), source.getHeight());
            int x = (source.getWidth()-size)/2;
            int y = (source.getHeight() - size);
            Config.logi("height is :"+source.getHeight() + "y is: "+y);
                    
            Bitmap result = Bitmap.createBitmap(source, 0, 0, source.getWidth(), source.getHeight()-50);
            if (result != source) {
                source.recycle();
            }
            return result;
        }

        @Override
        public String key() {
            return "square()";
        }
    }
}
