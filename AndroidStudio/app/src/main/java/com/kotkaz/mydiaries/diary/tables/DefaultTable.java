package com.kotkaz.mydiaries.diary.tables;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.kotkaz.mydiaries.diary.tools.LocalDateSerializer;

import org.joda.time.LocalDate;

import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Main table class
 *
 * @param <T> Table Entry type
 */
public class DefaultTable<T> implements Serializable {

    private List<T> tabelData = new ArrayList<>();


    public DefaultTable(List<T> tabelData) {
        this.tabelData = tabelData;
    }

    DefaultTable() {
    }

    /**
     * Adds new entry to table.
     *
     * @param data Entry
     */
    public void addData(T data) {
        tabelData.add(data);
    }

    /**
     * Removes entry from table.
     *
     * @param index
     */
    public void removeData(int index) {
        tabelData.remove(index);
    }


    /**
     * Loads table from JSON file.
     */
    public void fromBytes(byte[] bytes) throws IOException {

        final GsonBuilder builder = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        final Gson gson = builder.create();

        String data = new String(bytes);

        DefaultTable defaultTable = gson.fromJson(data, this.getClass());
        if (defaultTable == null) return;
        this.tabelData = defaultTable.tabelData;


    }


    /**
     * Saves table to JSON file.
     */
    public byte[] toBytes() {
        final GsonBuilder builder = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        final Gson gson = builder.create();

        final Type REVIEW_TYPE = new TypeToken<DefaultTable<T>>() {
        }.getType();

        return gson.toJson(this, REVIEW_TYPE).getBytes();


        /*
        try (final FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.write(jsonFile);
        }*/


    }


    /**
     * Tabel data Getter.
     *
     * @return ArrayList of Entry T
     */
    public List<T> getTabel() {
        return tabelData;
    }

    /**
     * Table ordered data Getter
     *
     * @param comparator Comparator, that is being used to sort data.
     * @return Ordered ArrayList of Entry T.
     */

    List<T> getOrderedTable(Comparator<T> comparator) {
        List<T> tempData = new ArrayList<>(tabelData);
        Collections.sort(tempData, comparator);
        return tempData;
    }

    /**
     * Table entry Getter.
     *
     * @param index Entry indeks.
     * @return Entry T
     */
    public T getEntry(int index) {
        return tabelData.get(index);
    }


}
