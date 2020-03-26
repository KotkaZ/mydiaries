import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.util.*;

public class DefaultTable<T> {
    private List<LocalDateTime> inputDate = new ArrayList<>();
    private List<T> tabelData = new ArrayList<>();
    private String fileName;


    public DefaultTable(String fileName, List<LocalDateTime> inputDate, List<T> tabelData) {
        this.fileName = fileName;
        this.inputDate = inputDate;
        this.tabelData = tabelData;
    }

    public void loadTable(){
        this.loadTable("");
    }

    public void loadTable(String path) {

        final Type REVIEW_TYPE = new TypeToken<DefaultTable<T>>() {
        }.getType();

        final Gson gson = new Gson();

        try(final JsonReader jsonReader = new JsonReader(new FileReader( fileName))){
            DefaultTable<T> defaultTable = gson.fromJson(jsonReader,REVIEW_TYPE);
            this.inputDate = defaultTable.inputDate;
            this.tabelData = defaultTable.tabelData;

        }
        catch (FileNotFoundException fileNotFoundException){
            System.out.println("File was not found!");
        }
        catch (IOException ioException){
            //TODO
            ioException.printStackTrace();
        }

    }

    public DefaultTable(){
    }

    //ONLY for testing purposes
    public void setName(String name){
        fileName = name;
    }

    public void addData(T data){
        inputDate.add(LocalDateTime.now());
        tabelData.add(data);
    }


    public void saveTable(){
        this.saveTabel("");
    }

    public void saveTabel(String path){
        final Gson gson = new Gson();

        String jsonFile = gson.toJson(this);

        try(final FileWriter fileWriter = new FileWriter( fileName)) {
            System.out.println(path + "/" + fileName);
            fileWriter.write(jsonFile);
        }
        catch (IOException ioException){
            System.out.println("File couldn't be written! :(");
        }


    }


    public Map<LocalDateTime, T> getTabel(){
        //Object[][] defaultTable= new Object[inputDate.size()][];
        /*
        for (Object[] objects : defaultTable) {
            objects =
        }*/

        Map<LocalDateTime, T> defaultTable = new LinkedHashMap<>();
        for (int i = 0; i < inputDate.size(); i++) {
            defaultTable.put(inputDate.get(i),tabelData.get(i));
        }

        return defaultTable;
    }
}
