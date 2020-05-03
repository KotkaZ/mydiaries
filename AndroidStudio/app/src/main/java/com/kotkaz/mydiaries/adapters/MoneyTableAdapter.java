package com.kotkaz.mydiaries.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.kotkaz.mydiaries.R;
import com.kotkaz.mydiaries.diary.entries.MoneyTableEntry;
import com.kotkaz.mydiaries.diary.tables.MoneyTable;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.List;

/**
 * CustomAdapter class for listView.
 */
public class MoneyTableAdapter extends BaseAdapter {

    private List<MoneyTableEntry> moneyTableEntries; //Has to be changed.
    private LayoutInflater layoutInflater;
    private Context context;

    public MoneyTableAdapter(MoneyTable moneyTable, LayoutInflater layoutInflater, Context context) {
        this.moneyTableEntries = moneyTable.getTabel();
        this.layoutInflater = layoutInflater;
        this.context = context;
    }


    @Override
    public int getCount() {
        return moneyTableEntries.size();
    }

    @Override
    public Object getItem(int position) {
        return moneyTableEntries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.money_table_item, null);

        TextView moneyTitle = convertView.findViewById(R.id.txtMoneyTitle);
        TextView moneyDesc = convertView.findViewById(R.id.txtMoneyDesc);
        TextView moneyAmount = convertView.findViewById(R.id.txtMoneyAmount);
        TextView moneyUseDate = convertView.findViewById(R.id.txtMoneyUseDate);

        MoneyTableEntry moneyTableEntry = moneyTableEntries.get(position);
        moneyTitle.setText(moneyTableEntry.getType());
        moneyDesc.setText(moneyTableEntry.getDescription());

        DecimalFormat moneyFormat = new DecimalFormat("€#,##0.00;-€#,##0.00");

        moneyAmount.setText(moneyFormat.format(moneyTableEntry.getAmount() / 100));
        moneyUseDate.setText(moneyTableEntry.getUseDate().toString());

        if (moneyTableEntry.getAmount() < 0)
            moneyAmount.setTextColor(ContextCompat.getColor(context, R.color.colorNegativeMoney));
        else
            moneyAmount.setTextColor(ContextCompat.getColor(context, R.color.colorPositiveMoney));


        return convertView;
    }
}
