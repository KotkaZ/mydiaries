package com.kotkaz.mydiaries.diary;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import org.joda.time.LocalDate;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Main table class
 *
 * @param <T> Table Entry type
 */
public class DefaultTable<T> {
    private List<LocalDate> inputDate = new ArrayList<>();
    private List<T> tabelData = new ArrayList<>();


    public DefaultTable(List<LocalDate> inputDate, List<T> tabelData) {
        this.inputDate = inputDate;
        this.tabelData = tabelData;
    }

    public DefaultTable() {
    }

    /**
     * Adds new entries to table.
     *
     * @param data Entry
     */
    public void addData(T data) {
        inputDate.add(LocalDate.now());
        tabelData.add(data);
    }

    public void removeData(List<LocalDate> inputDateArray) {
        for (LocalDate inputDateToRemove : inputDateArray) {
            for (int i = 0; i < inputDate.size(); ) {
                if (inputDate.get(i) == inputDateToRemove) {
                    inputDate.remove(i);
                    tabelData.remove(i);
                } else i++;

            }
        }

    }


    /**
     * Loads table from JSON file.
     *
     * @param fileName Filename with extension.
     * @throws IOException If file was not found.
     */
    public void loadTable(String fileName) throws IOException {

        //final Type REVIEW_TYPE = new TypeToken<DefaultTable<T>>() {}.getType();
        final Gson gson = new Gson();

        try (final JsonReader jsonReader = new JsonReader(new FileReader(fileName))) {
            DefaultTable<T> defaultTable = gson.fromJson(jsonReader, this.getClass());
            if (defaultTable == null) return;
            this.inputDate = defaultTable.inputDate;
            this.tabelData = defaultTable.tabelData;
        }

    }


    /**
     * Saves table to JSON file.
     *
     * @param fileName Filename with extension.
     * @throws IOException If file was not found.
     */
    public void saveTabel(String fileName) throws IOException {
        final Gson gson = new Gson();

        final Type REVIEW_TYPE = new TypeToken<DefaultTable<T>>() {
        }.getType();
        String jsonFile = gson.toJson(this, REVIEW_TYPE);

        try (final FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(jsonFile);
        }


    }


    /**
     * Puts two arrays into Map, where input date is key and entry is value.
     *
     * @return Map
     * @throws RuntimeException If table is corrupted. Shouldn't happen tho.
     */
    public Map<LocalDate, T> getTabel() throws RuntimeException {
        if (inputDate.size() != tabelData.size())
            throw new RuntimeException("Table is corrupted!");

        Map<LocalDate, T> defaultTable = new LinkedHashMap<>();
        for (int i = 0; i < inputDate.size(); i++)
            defaultTable.put(inputDate.get(i), tabelData.get(i));

        return defaultTable;
    }


}
