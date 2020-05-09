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
 * CustomExerciseAdapter class for listView.
 */
public class ExerciseTableAdapter extends BaseAdapter {

    private List<ExerciseTableEntry> exerciseTableEntries; //Has to be changed.
    private LayoutInflater layoutInflater;

    /**
     * Exercise Table Adapter Constructor.
     *
     * @param exerciseTableEntries  ExerciseTable
     * @param layoutInflater ApplicationContext layoutInflater.
     */
    public ExerciseTableAdapter(List<ExerciseTableEntry> exerciseTableEntries, LayoutInflater layoutInflater) {
        this.exerciseTableEntries = exerciseTableEntries;
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
            convertView = layoutInflater.inflate(R.layout.exercise_table_item, parent, false);

        //Finding all views.
        TextView exerciseTitle = convertView.findViewById(R.id.txtExerciseTitle);
        TextView exerciseDate = convertView.findViewById(R.id.txtExerciseDate);
        TextView exerciseLength = convertView.findViewById(R.id.txtExerciseLength);
        TextView exerciseDesc = convertView.findViewById(R.id.txtExerciseDesc);
        TextView exerciseLocation = convertView.findViewById(R.id.txtExerciseLocation);

        //Getting entry with the same index.
        ExerciseTableEntry exerciseTableEntry = exerciseTableEntries.get(position);

        //Setting textview texts with entry values.
        exerciseTitle.setText(exerciseTableEntry.getType());
        exerciseLocation.setText(exerciseTableEntry.getLocation());
        exerciseDesc.setText(exerciseTableEntry.getDescription());
        exerciseDate.setText(exerciseTableEntry.getExerciseDate().toString());
        exerciseLength.setText(String.format("%s min", String.valueOf(exerciseTableEntry.getLength())));

        return convertView;
    }
}
