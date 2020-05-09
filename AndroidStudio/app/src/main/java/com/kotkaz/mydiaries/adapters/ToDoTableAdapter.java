package com.kotkaz.mydiaries.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.kotkaz.mydiaries.R;
import com.kotkaz.mydiaries.diary.entries.ToDoTableEntry;
import com.kotkaz.mydiaries.diary.tables.ToDoTable;

import java.util.List;
import java.util.Locale;

/**
 * CustomToDoTableAdapter class for listView.
 */
public class ToDoTableAdapter extends BaseAdapter {

    private List<ToDoTableEntry> toDoTableEntries; //Has to be changed.
    private LayoutInflater layoutInflater;
    private Context context;

    /**
     * ToDoTableAdapter default constructor.
     *
     * @param toDoTable      ToDoTable
     * @param layoutInflater ApplicationContext layoutInflater.
     * @param context        ApplicationContext. For getting resources and colors.
     */
    public ToDoTableAdapter(ToDoTable toDoTable, LayoutInflater layoutInflater, Context context) {
        this.toDoTableEntries = toDoTable.getTabel();
        this.layoutInflater = layoutInflater;
        this.context = context;
    }


    @Override
    public int getCount() {
        return toDoTableEntries.size();
    }

    @Override
    public Object getItem(int position) {
        return toDoTableEntries.get(position);
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
            convertView = layoutInflater.inflate(R.layout.todo_table_item, parent, false);

        //Finding all views.
        TextView toDoTitle = convertView.findViewById(R.id.txtToDoTitle);
        TextView toDoDesc = convertView.findViewById(R.id.txtToDoDesc);
        TextView toDoDate = convertView.findViewById(R.id.txtToDoDate);
        TextView toDoPriority = convertView.findViewById(R.id.txtToDoPriority);

        //Getting entry with the same index.
        ToDoTableEntry toDoTableEntry = toDoTableEntries.get(position);

        //Setting textview texts with entry values.
        toDoTitle.setText(toDoTableEntry.getType());
        toDoDesc.setText(toDoTableEntry.getDescription());
        toDoPriority.setText(String.valueOf(toDoTableEntry.getPriority()));

        toDoDate.setText(String.format(Locale.getDefault(), "%d.%d.%d %d:%d", toDoTableEntry.getDeadline().getDayOfMonth()
                , toDoTableEntry.getDeadline().getMonthOfYear()
                , toDoTableEntry.getDeadline().getYear()
                , toDoTableEntry.getDeadline().getHourOfDay()
                , toDoTableEntry.getDeadline().getMinuteOfHour()));

        //High priority is set red, med is orange and low priority wont have colored background.
        if (toDoTableEntry.getPriority() == 1)
            convertView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorDarkMenuTrans));
        else if (toDoTableEntry.getPriority() == 2)
            convertView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorMedPriorityTrans));
        else if (toDoTableEntry.getPriority() == 3)
            convertView.setBackgroundColor(ContextCompat.getColor(context, R.color.colorHighPriorityTrans));

        return convertView;
    }
}
