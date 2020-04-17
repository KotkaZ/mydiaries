package com.kotkaz.mydiaries;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kotkaz.mydiaries.diary.Entries;
import com.kotkaz.mydiaries.diary.FoodTable;


import org.joda.time.LocalDate;

import java.util.Map;

/**
 * CustomAdapter class for listView.
 */
public class CustomAdapter extends BaseAdapter {

    private LocalDate[] entries;
    private Map<LocalDate, Entries.FoodTableEntry> foodTableEntryMap; //Has to be changed.
    private LayoutInflater layoutInflater;

    public CustomAdapter(FoodTable foodTableDefaultTable, LayoutInflater layoutInflater) {
        this.foodTableEntryMap = foodTableDefaultTable.getTabel();
        this.entries = foodTableEntryMap.keySet().toArray(new LocalDate[0]);
        this.layoutInflater = layoutInflater;
    }


    @Override
    public int getCount() {
        return foodTableEntryMap.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.food_table_item, null);

        TextView foodTitle = convertView.findViewById(R.id.txtFoodTitle);
        TextView foodDate = convertView.findViewById(R.id.txtFoodExpDate);
        TextView foodAmount = convertView.findViewById(R.id.txtFoodAmount);

        foodTitle.setText(foodTableEntryMap.get(entries[position]).getType());
        foodDate.setText(foodTableEntryMap.get(entries[position]).getExpDate().toString());
        foodAmount.setText(String.valueOf(foodTableEntryMap.get(entries[position]).getAmount()) );

        return convertView;
    }
}
