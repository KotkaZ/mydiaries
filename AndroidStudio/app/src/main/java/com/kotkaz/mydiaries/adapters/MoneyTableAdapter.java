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
import com.kotkaz.mydiaries.diary.tools.TableManager;

import java.text.DecimalFormat;
import java.util.List;

/**
 * CustomMoneyTableAdapter class for listView.
 */
public class MoneyTableAdapter extends BaseAdapter {

    private List<MoneyTableEntry> moneyTableEntries; //Has to be changed.
    private LayoutInflater layoutInflater;
    private Context context;

    /**
     * MoneyTableAdapter default constructor.
     *
     * @param moneyTableEntries MoneyTable
     * @param layoutInflater    ApplicationContext layoutInflater.
     * @param context           ApplicationContext. For getting resources and colors.
     */
    public MoneyTableAdapter(List<MoneyTableEntry> moneyTableEntries, LayoutInflater layoutInflater, Context context) {
        this.moneyTableEntries = moneyTableEntries;
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
            convertView = layoutInflater.inflate(R.layout.money_table_item, parent, false);

        //Finding all views.
        TextView moneyTitle = convertView.findViewById(R.id.txtMoneyTitle);
        TextView moneyDesc = convertView.findViewById(R.id.txtMoneyDesc);
        TextView moneyAmount = convertView.findViewById(R.id.txtMoneyAmount);
        TextView moneyUseDate = convertView.findViewById(R.id.txtMoneyUseDate);

        //Getting entry with the same index.
        MoneyTableEntry moneyTableEntry = moneyTableEntries.get(position);

        //Setting textview texts with entry values.
        moneyTitle.setText(moneyTableEntry.getType());
        moneyDesc.setText(moneyTableEntry.getDescription());

        //Formatting string for money textview.
        DecimalFormat moneyFormat = new DecimalFormat("€#,##0.00;-€#,##0.00");

        moneyAmount.setText(moneyFormat.format(moneyTableEntry.getAmount() / 100));
        moneyUseDate.setText(TableManager.lDateTimetoString(moneyTableEntry.getUseDate()));

        //Outgoes are set red, incomes green.
        if (moneyTableEntry.getAmount() < 0)
            moneyAmount.setTextColor(ContextCompat.getColor(context, R.color.colorNegativeMoney));
        else
            moneyAmount.setTextColor(ContextCompat.getColor(context, R.color.colorPositiveMoney));


        return convertView;
    }
}
