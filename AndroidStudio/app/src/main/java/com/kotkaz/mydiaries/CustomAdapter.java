package com.kotkaz.mydiaries;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kotkaz.mydiaries.diary.Entries;
import com.kotkaz.mydiaries.diary.FoodTable;

import java.time.LocalDateTime;
import java.util.Map;

public class CustomAdapter extends BaseAdapter {

    LocalDateTime[] entries;
    Map<LocalDateTime, Entries.FoodTableEntry> foodTableEntryMap;
    private LayoutInflater layoutInflater;

    public CustomAdapter(FoodTable foodTableDefaultTable, LayoutInflater layoutInflater) {
        this.foodTableEntryMap = foodTableDefaultTable.getTabel();
        this.entries = foodTableEntryMap.keySet().toArray(new LocalDateTime[0]);
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
        TextView foodDate = convertView.findViewById(R.id.txtExpDate);
        TextView foodAmount = convertView.findViewById(R.id.txtAmount);

        foodTitle.setText(foodTableEntryMap.get(entries[position]).getType());
        foodDate.setText(foodTableEntryMap.get(entries[position]).getExpDate().toString());
        foodAmount.setText(String.valueOf(foodTableEntryMap.get(entries[position]).getAmount()) );

        return convertView;
    }
}
