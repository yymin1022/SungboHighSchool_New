package com.sungbospot.lunch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

class MealViewHolder {
    public TextView mCalender;
    public TextView mDayOfTheWeek;
    public TextView mLunch;
    public TextView mDinner;
}

class MealListData {
    public String mCalender;
    public String mDayOfTheWeek;
    public String mLunch;
    public String mDinner;
}

public class MealListAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<MealListData> mListData = new ArrayList<MealListData>();

    public MealListAdapter(Context mContext) {
        super();

        this.mContext = mContext;
    }

    public void addItem(String mCalender, String mDayOfTheWeek, String mLunch, String mDinner) {

        MealListData addItemInfo = new MealListData();
        addItemInfo.mCalender = mCalender;
        addItemInfo.mDayOfTheWeek = mDayOfTheWeek;
        addItemInfo.mLunch = mLunch;
        addItemInfo.mDinner = mDinner;

        mListData.add(addItemInfo);
    }

    public void clearData() {
        mListData.clear();
    }

    @Override
    public int getCount() {
        return mListData.size();
    }

    @Override
    public MealListData getItem(int position) {
        return mListData.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        MealViewHolder mHolder;

        if (convertView == null) {
            mHolder = new MealViewHolder();

            LayoutInflater inflater = (LayoutInflater) mContext
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.list_meal_item, null);

            mHolder.mCalender = convertView.findViewById(R.id.mCalender);
            mHolder.mDayOfTheWeek = convertView.findViewById(R.id.mDayOfTheWeek);
            mHolder.mLunch = convertView.findViewById(R.id.mLunch);
            mHolder.mDinner = convertView.findViewById(R.id.mDinner);

            convertView.setTag(mHolder);

        } else {
            mHolder = (MealViewHolder) convertView.getTag();
        }

        MealListData mData = mListData.get(position);

        String mCalender = mData.mCalender;
        String mDayOfTheWeek = mData.mDayOfTheWeek;
        String mLunch = mData.mLunch;
        String mDinner = mData.mDinner;

        if (MealTool.mStringCheck(mLunch))
            mLunch = mData.mLunch = "점심이 없습니다.";
        if (MealTool.mStringCheck(mDinner))
            mDinner = mData.mDinner = "저녁이 없습니다.";

        mHolder.mCalender.setText(mCalender);
        mHolder.mDayOfTheWeek.setText(mDayOfTheWeek);
        mHolder.mLunch.setText(mLunch);
        mHolder.mDinner.setText(mDinner);

        return convertView;
    }
}