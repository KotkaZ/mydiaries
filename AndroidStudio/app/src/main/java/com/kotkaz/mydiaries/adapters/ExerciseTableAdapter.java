package com.kotkaz.mydiaries.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.kotkaz.mydiaries.R;
import com.kotkaz.mydiaries.diary.entries.ExerciseTableEntry;
import com.kotkaz.mydiaries.diary.tables.ExerciseTable;

import java.util.List;

/**
 * CustomAdapter class for listView.
 */
public class ExerciseTableAdapter extends BaseAdapter {

    private List<ExerciseTableEntry> exerciseTableEntries; //Has to be changed.
    private LayoutInflater layoutInflater;

    public ExerciseTableAdapter(ExerciseTable exerciseTable, LayoutInflater layoutInflater) {
        this.exerciseTableEntries = exerciseTable.getTabel();
        this.layoutInflater = layoutInflater;
    }


    @Override
    public int getCount() {
        return exerciseTableEntries.size();
    }

    @Override
    public Object getItem(int position) {
        return exerciseTableEntries.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        convertView = layoutInflater.inflate(R.layout.exercise_table_item, null);

        TextView exerciseTitle = convertView.findViewById(R.id.txtExerciseTitle);
        TextView exerciseDate = convertView.findViewById(R.id.txtExerciseDate);
        TextView exerciseLength = convertView.findViewById(R.id.txtExerciseLength);
        TextView exerciseDesc = convertView.findViewById(R.id.txtExerciseDesc);
        TextView exerciseLocation = convertView.findViewById(R.id.txtExerciseLocation);

        ExerciseTableEntry exerciseTableEntry = exerciseTableEntries.get(position);

        exerciseTitle.setText(exerciseTableEntry.getType());
        exerciseLocation.setText(exerciseTableEntry.getLocation());
        exerciseDesc.setText(exerciseTableEntry.getDescription());
        exerciseDate.setText(exerciseTableEntry.getExerciseDate().toString());
        exerciseLength.setText(String.valueOf(exerciseTableEntry.getLength()));

        return convertView;
    }
}
