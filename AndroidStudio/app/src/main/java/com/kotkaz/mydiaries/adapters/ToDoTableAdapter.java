package com.kotkaz.mydiaries.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kotkaz.mydiaries.R;
import com.kotkaz.mydiaries.diary.entries.ToDoTableEntry;
import com.kotkaz.mydiaries.diary.tables.ToDoTable;

import java.util.List;

/**
 * CustomAdapter class for listView.
 */
public class ToDoTableAdapter extends BaseAdapter {

    private List<ToDoTableEntry> toDoTableEntries; //Has to be changed.
    private LayoutInflater layoutInflater;

    public ToDoTableAdapter(ToDoTable toDoTable, LayoutInflater layoutInflater) {
        this.toDoTableEntries = toDoTable.getTabel();
        this.layoutInflater = layoutInflater;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.todo_table_item, null);

        TextView toDoTitle = convertView.findViewById(R.id.txtToDoTitle);
        TextView toDoDesc = convertView.findViewById(R.id.txtToDoDesc);
        TextView toDoDate = convertView.findViewById(R.id.txtToDoDate);
        TextView toDoPriority = convertView.findViewById(R.id.txtToDoPriority);

        ToDoTableEntry toDoTableEntry = toDoTableEntries.get(position);
        toDoTitle.setText(toDoTableEntry.getType());
        toDoDesc.setText(toDoTableEntry.getDescription());
        toDoPriority.setText(String.valueOf(toDoTableEntry.getPriority()));
        toDoDate.setText(toDoTableEntry.getDeadline().toString());

        return convertView;
    }
}
