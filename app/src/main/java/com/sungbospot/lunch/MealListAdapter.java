package com.sungbospot.lunch;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by whdghks913 on 2015-02-17.
 */
class MealViewHolder {
    public TextView mCalender;
    public TextView mDayOfTheWeek;
    public TextView mMorning;
    public TextView mLunch;
    public TextView mDinner;
}

class MealListData {
    public String mCalender;
    public String mDayOfTheWeek;
    public String mMorning;
    public String mLunch;
    public String mDinner;
}

public class MealListAdapter extends BaseAdapter {
    private Context mContext = null;
    private ArrayList<MealListData> mListData = new ArrayList<MealListData>();

    public MealListAdapter(Context mContext) {
        super();

        this.mContext = mContext;
    }

    public void addItem(String mCalender, String mDayOfTheWeek, String mMorning, String mLunch, String mDinner) {

        MealListData addItemInfo = new MealListData();
        addItemInfo.mCalender = mCalender;
        addItemInfo.mDayOfTheWeek = mDayOfTheWeek;
        addItemInfo.mMorning = mMorning;
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

            mHolder.mCalender = (TextView) convertView.findViewById(R.id.mCalender);
            mHolder.mDayOfTheWeek = (TextView) convertView.findViewById(R.id.mDayOfTheWeek);
            mHolder.mLunch = (TextView) convertView.findViewById(R.id.mLunch);
            mHolder.mDinner = (TextView) convertView.findViewById(R.id.mDinner);

            convertView.setTag(mHolder);

        } else {
            mHolder = (MealViewHolder) convertView.getTag();
        }

        MealListData mData = mListData.get(position);

        String mCalender = mData.mCalender;
        String mDayOfTheWeek = mData.mDayOfTheWeek;
        String mMorning = mData.mMorning;
        String mLunch = mData.mLunch;
        String mDinner = mData.mDinner;

        /**
         * 급식이 없을경우 없다는 정보를 표시합니다.
         */
        /**
         * TODO 아침이 없습니다 부분은 개발자가 string.xml 파일에 <string> 정의하기 귀찮아서 하드코딩함.
         * TODO 여러분도 귀찮으면 걍 하드코딩 하세요. 유지 보수만 좀 힘듦
         */
        if (MealTool.mStringCheck(mMorning))
            mMorning = mData.mMorning = "아침이 없습니다.";
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