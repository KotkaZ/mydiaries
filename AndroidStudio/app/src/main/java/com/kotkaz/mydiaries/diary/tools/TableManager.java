package com.kotkaz.mydiaries.diary.tools;

import android.content.Context;

import com.kotkaz.mydiaries.diary.tables.DefaultTable;

import org.apache.commons.io.IOUtils;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class TableManager {

    public static void saveTable(Context context, DefaultTable defaultTable, String FILE_NAME) {
        try (FileOutputStream fileOutputStream = context.openFileOutput(FILE_NAME, Context.MODE_PRIVATE)) {
            fileOutputStream.write(defaultTable.toBytes());

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }

    public static void loadTable(Context context, DefaultTable defaultTable, String FILE_NAME) {
        try (InputStream fileInputStream = context.openFileInput(FILE_NAME)) {
            byte[] bytes = IOUtils.toByteArray(fileInputStream);
            defaultTable.fromBytes(bytes);

        } catch (FileNotFoundException e) {
            System.out.println("FileNotFoundException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
