package com.kotkaz.mydiaries;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kotkaz.mydiaries.diary.entries.FoodTableEntry;
import com.kotkaz.mydiaries.diary.tables.FoodTable;

import org.joda.time.LocalDate;

import java.util.List;

/**
 * CustomAdapter class for listView.
 */
public class CustomAdapter extends BaseAdapter {

    private LocalDate[] entries;
    private List<FoodTableEntry> foodTableEntries; //Has to be changed.
    private LayoutInflater layoutInflater;

    public CustomAdapter(FoodTable foodTableDefaultTable, LayoutInflater layoutInflater) {
        this.foodTableEntries = foodTableDefaultTable.getTabel();
        this.layoutInflater = layoutInflater;
    }


    @Override
    public int getCount() {
        return foodTableEntries.size();
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

        foodTitle.setText(foodTableEntries.get(position).getType());
        foodDate.setText(foodTableEntries.get(position).getExpDate().toString());
        foodAmount.setText(String.valueOf(foodTableEntries.get(position).getAmount()));

        return convertView;
    }
}
