package com.kotkaz.mydiaries.diary.tables;

import com.kotkaz.mydiaries.diary.entries.DefaultEntry;

import java.io.Serializable;
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
    void addData(T data) {
        tabelData.add(data);
    }

    /**
     * Sets tableData field.
     *
     * @param data
     */
    public void setData(List<T> data) {
        tabelData = data;
    }

    /**
     * Removes entry from table.
     *
     * @param indeks
     */
    public void removeData(int indeks) {
        tabelData.remove(indeks);
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
    public List<T> getOrderedTable(Comparator<T> comparator) {
        List<T> tempData = new ArrayList<>(tabelData);
        Collections.sort(tempData, comparator);
        return tempData;
    }

    /**
     * This method orderes list by Entry-class object variables.
     *
     * @param type      Int typenumber
     *                  0- type
     *                  1- inputdate
     *                  2- expDate
     * @param ascending
     * @return
     */
    public List<T> getOrderedTable(int type, boolean ascending) {
        return this.getOrderedTable((o1, o2) -> {
            int value;
            switch (type) {
                case 0:
                    value = ((DefaultEntry) o1).getType().compareTo(((DefaultEntry) o2).getType());
                    break;
                case 1:
                    value = ((DefaultEntry) o1).getInputDate().compareTo(((DefaultEntry) o2).getInputDate());
                    break;
                default:
                    throw new IllegalArgumentException("DefaultTable#getOrderedTabel wrong ordering type");
            }
            return value * (ascending ? 1 : -1);
        });
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
