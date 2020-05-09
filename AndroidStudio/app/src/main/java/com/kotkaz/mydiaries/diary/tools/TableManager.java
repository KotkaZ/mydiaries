package com.kotkaz.mydiaries.diary.tools;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.kotkaz.mydiaries.diary.tables.DefaultTable;

import org.apache.commons.io.IOUtils;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;

public class TableManager {

    public static void saveTable(Context context, DefaultTable defaultTable, String FILE_NAME) {
        try (FileOutputStream fileOutputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)) {

            final GsonBuilder builder = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
            final Gson gson = builder.create();

            final Type REVIEW_TYPE = new TypeToken<DefaultTable>() {
            }.getType();


            fileOutputStream.write(gson.toJson(defaultTable, REVIEW_TYPE).getBytes());


        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    public static void loadTable(Context context, DefaultTable defaultTable, String FILE_NAME) {
        try (InputStream fileInputStream = context.openFileInput(FILE_NAME)) {
            byte[] bytes = IOUtils.toByteArray(fileInputStream);

            final GsonBuilder builder = new GsonBuilder()
                    .registerTypeAdapter(LocalDate.class, new LocalDateSerializer())
                    .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer());
            final Gson gson = builder.create();

            String data = new String(bytes);

            defaultTable.setData(gson.fromJson(data, defaultTable.getClass()).getTabel());


        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
