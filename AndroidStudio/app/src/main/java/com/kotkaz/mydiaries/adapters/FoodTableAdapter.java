package com.kotkaz.mydiaries.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kotkaz.mydiaries.R;
import com.kotkaz.mydiaries.diary.entries.FoodTableEntry;
import com.kotkaz.mydiaries.diary.tables.FoodTable;

import java.util.List;

/**
 * CustomAdapter class for listView.
 */
public class FoodTableAdapter extends BaseAdapter {

    private List<FoodTableEntry> foodTableEntries; //Has to be changed.
    private LayoutInflater layoutInflater;

    public FoodTableAdapter(FoodTable foodTableDefaultTable, LayoutInflater layoutInflater) {
        this.foodTableEntries = foodTableDefaultTable.getTabel();
        this.layoutInflater = layoutInflater;
    }


    @Override
    public int getCount() {
        return foodTableEntries.size();
    }

    @Override
    public Object getItem(int position) {
        return foodTableEntries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.food_table_item, null);

        TextView foodTitle = convertView.findViewById(R.id.txtFoodTitle);
        TextView foodDate = convertView.findViewById(R.id.txtFoodExpDate);
        TextView foodAmount = convertView.findViewById(R.id.txtFoodAmount);

        FoodTableEntry foodTableEntry = foodTableEntries.get(position);
        foodTitle.setText(foodTableEntry.getType());
        foodDate.setText(foodTableEntry.getExpDate().toString());
        foodAmount.setText(String.valueOf(foodTableEntry.getAmount()));

        return convertView;
    }
}
