package com.sungbospot.lunch;

import android.content.Context;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import toast.library.meal.MealLibrary;

public class MealTool {
    public static final String Meal_PREFERENCE_NAME = "MealData";

    public static final int TYPE_LUNCH = 1;
    public static final int TYPE_DINNER = 2;

    public static String getMealStringFormat(int year, int month, int day, int type) {
        return year + "-" + month + "-" + day + "-" + type;
    }

    public static void saveMealData(Context mContext, String[] Calender, String[] Lunch, String[] Dinner) {
        Preference mPref = new Preference(mContext, Meal_PREFERENCE_NAME);
        SimpleDateFormat mFormat = new SimpleDateFormat("yyyy.MM.dd(E)", Locale.KOREA);

        for (int index = 0; index < Calender.length; index++) {
            try {
                Calendar mDate = Calendar.getInstance();
                mDate.setTime(mFormat.parse(Calender[index]));

                int year = mDate.get(Calendar.YEAR);
                int month = mDate.get(Calendar.MONTH) + 1;
                int day = mDate.get(Calendar.DAY_OF_MONTH);

                String mPrefLunchName = getMealStringFormat(year, month, day, TYPE_LUNCH);
                String mPrefDinnerName = getMealStringFormat(year, month, day, TYPE_DINNER);

                String mLunch = Lunch[index];
                String mDinner = Dinner[index];

                if (!MealLibrary.isMealCheck(mLunch)) mLunch = "";
                if (!MealLibrary.isMealCheck(mDinner)) mDinner = "";

                mPref.putString(mPrefLunchName, mLunch);
                mPref.putString(mPrefDinnerName, mDinner);

            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
    }

    public static restoreMealDateClass restoreMealData(Context mContext, int year, int month, int day) {
        Preference mPref = new Preference(mContext, Meal_PREFERENCE_NAME);
        SimpleDateFormat mCalenderFormat = new SimpleDateFormat("yyyy년 MM월 dd일", Locale.KOREA);
        SimpleDateFormat mDayOfWeekFormat = new SimpleDateFormat("E요일", Locale.KOREA);
        Calendar mDate = Calendar.getInstance();
        mDate.set(year, month, day);

        restoreMealDateClass mData = new restoreMealDateClass();

        String mPrefLunchName = getMealStringFormat(year, month + 1, day, TYPE_LUNCH);
        String mPrefDinnerName = getMealStringFormat(year, month + 1, day, TYPE_DINNER);

        mData.Calender = mCalenderFormat.format(mDate.getTime());
        mData.DayOfTheWeek = mDayOfWeekFormat.format(mDate.getTime());
        mData.Lunch = mPref.getString(mPrefLunchName, null);
        mData.Dinner = mPref.getString(mPrefDinnerName, null);

        if (mData.Lunch == null && mData.Dinner == null) {
            mData.isBlankDay = true;
        }

        return mData;
    }

    public static class restoreMealDateClass {
        public String Calender;
        public String DayOfTheWeek;
        public String Lunch;
        public String Dinner;
        public boolean isBlankDay = false;
    }

    public static boolean mStringCheck(String mString) {
        return (mString == null || "".equals(mString) || " ".equals(mString));
    }
}