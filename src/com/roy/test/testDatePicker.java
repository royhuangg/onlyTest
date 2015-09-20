/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.roy.test;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import grandroid.dialog.DialogMask;
import grandroid.dialog.GDialog;
import grandroid.view.Face;
import grandroid.view.LayoutMaker;
import java.util.Calendar;

/**
 *
 * @author ROY
 */
public class testDatePicker extends SampleFace {

    private int mYear, mMonth, mDay, mHour, mMinute;
    private TextView tvDate;
    protected TextView dateButton;
    protected String Year, Mon, Day;
    protected DatePicker dp;
    protected DatePickerDialog dpd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dateButton = maker.createButton("extension");
        maker.addColLayout(false, maker.layFF());
        {
            maker.addButton("click").setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {

                    showDatePickerDialog();
                }
            });
            maker.add(dateButton, maker.layAbsolute(0, 0, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)).setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {
                    showDateDialog();
                }
            });

            tvDate = maker.add(maker.createStyledText("no date").color(Color.WHITE).center().get(), maker.layWW(0));

            maker.addButton("click show inputEditText").setOnClickListener(new View.OnClickListener() {

                public void onClick(View v) {

                    showInputDialog();
                }
            });
        }

    }

    private void showInputDialog() {
        final AlertDialog dialog;
        final EditText input = maker.createEditText("");
        input.setHeight(200);

        dialog = new AlertDialog.Builder(this)
                .setTitle("Title")
                .setMessage("Message")
                .setView(input)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // 在此處理 input
                        Config.logi(input.getText().toString());
                    }
                }).show();

        input.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    dialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_VISIBLE);
                }
            }
        });
    }

    public void showDatePickerDialog() {
        // 設定初始日期
        final Calendar c = Calendar.getInstance();
        mYear = c.get(Calendar.YEAR);
        mMonth = c.get(Calendar.MONTH);
        mDay = c.get(Calendar.DAY_OF_MONTH);

        // 跳出日期選擇器
        DatePickerDialog dpd = new DatePickerDialog(this,
                new DatePickerDialog.OnDateSetListener() {
                    public void onDateSet(DatePicker view, int year,
                            int monthOfYear, int dayOfMonth) {
                        // 完成選擇，顯示日期
                        tvDate.setText(year + "-" + (monthOfYear + 1) + "-"
                                + dayOfMonth);

                    }
                }, mYear, mMonth, mDay);
        dpd.show();
    }

    private void showDateDialog() {
        new DialogMask(testDatePicker.this) {
            @Override
            public boolean setupMask(Context context, GDialog.Builder builder, LayoutMaker maker) throws Exception {
                Calendar TodayDate = Calendar.getInstance();
                final int sYear = TodayDate.get(Calendar.YEAR);
                final int sMon = TodayDate.get(Calendar.MONTH);
                final int sDay = TodayDate.get(Calendar.DAY_OF_MONTH);
                Year = String.valueOf(sYear);
                Mon = String.valueOf(sMon + 1);
                Day = String.valueOf(sDay);
                maker.addColLayout(false, maker.layWW(1));
                {
                    maker.getLastLayout().setBackgroundColor(Color.WHITE);
                    maker.add(maker.createStyledText("Dat").color(Color.WHITE).bgc(Color.BLACK).get(), maker.layFW(1));
                    dp = new DatePicker(testDatePicker.this);
                    maker.add(dp, maker.layFW(1));
                    //initial datepicker
                    dp.init(sYear, sMon, sDay, new DatePicker.OnDateChangedListener() {
                        public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
//                                            Log.e("cus", String.valueOf(monthOfYear));
                            Year = String.valueOf(year);
                            //TodayDate.get(Calendar.MONTH) index(0~11)
                            Mon = String.valueOf(monthOfYear + 1);
                            Day = String.valueOf(dayOfMonth);
                        }
                    });
                    maker.addRowLayout(false, maker.layFW(1));
                    {
                        maker.add(maker.createButton("confirm"), maker.layFW(1)).setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                dateButton.setText(Year + "-" + Mon + "-" + Day);
                                dialog.dismiss();
                            }
                        });
                        maker.add(maker.createButton("cancel"), maker.layFW(1)).setOnClickListener(new View.OnClickListener() {
                            public void onClick(View v) {
                                dialog.cancel();
                            }
                        });
                        maker.escape();
                    }
                    maker.escape();
                }
                return false;
            }
        }.cancelable().show(GDialog.DialogStyle.Custom);
    }

    public void showTimePickerDialog() {
        // 設定初始時間
        final Calendar c = Calendar.getInstance();
        mHour = c.get(Calendar.HOUR_OF_DAY);
        mMinute = c.get(Calendar.MINUTE);

        // 跳出時間選擇器
        TimePickerDialog tpd = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {
                    public void onTimeSet(TimePicker view, int hourOfDay,
                            int minute) {
                        // 完成選擇，顯示時間
                        // tvTime.setText(hourOfDay + ":" + minute);
                    }

                }, mHour, mMinute, false);
        tpd.show();
    }
}
