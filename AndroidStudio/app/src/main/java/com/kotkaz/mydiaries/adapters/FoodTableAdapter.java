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
 * CustomFoodTableAdapter class for listView.
 */
public class FoodTableAdapter extends BaseAdapter {

    private List<FoodTableEntry> foodTableEntries; //Has to be changed.
    private LayoutInflater layoutInflater;

    /**
     * FoodTableAdapter Constructor.
     *
     * @param foodTableDefaultTable FoodTable
     * @param layoutInflater        ApplicationContext layoutInflater.
     */
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


    /**
     * Inflates new view view, finds all textviews and sets texts.
     *
     * @param position    View index, is common with entry index.
     * @param convertView Will be inflated from layout.
     * @param parent      ListView.
     * @return Returns inflated view, where text is set according to entry values.
     */

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null)
            convertView = layoutInflater.inflate(R.layout.food_table_item, parent, false);

        //Finding all views.
        TextView foodTitle = convertView.findViewById(R.id.txtFoodTitle);
        TextView foodDate = convertView.findViewById(R.id.txtFoodExpDate);
        TextView foodAmount = convertView.findViewById(R.id.txtFoodAmount);

        //Getting entry with the same index.
        FoodTableEntry foodTableEntry = foodTableEntries.get(position);

        //Setting textview texts with entry values.
        foodTitle.setText(foodTableEntry.getType());
        foodDate.setText(foodTableEntry.getExpDate().toString());
        foodAmount.setText(String.valueOf(foodTableEntry.getAmount()));

        return convertView;
    }
}
