package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;

import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class CityService {

    private List<City> cities;
    private Map<Integer,String> cityMap = new TreeMap<>();

    private List<String[]> stringOfArrays;
    private int count;
    public CityService() {
        cities = new ArrayList<>();
        stringOfArrays = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(new FileReader(Paths.get("file/myFile0.csv").toAbsolutePath().toString()));
            String[] nextLine;
            int index = 0;
            while ((nextLine = reader.readNext()) != null){
                if (index > 0){
                    if (index < 10) stringOfArrays.add(nextLine);
                    Integer code = Integer.parseInt(nextLine[0].trim());
                    String name = nextLine[1].trim();
                    if (cityMap.containsKey(code)){
                        count++;
                    }else {
                        cityMap.put(code,name);
                    }
                    cities.add(new City(code,name));
                }
                index++;
            }
        } catch (IOException | CsvException e) {
            throw new RuntimeException(e);
        }
    }

    public void bubbleSort(List<String[]> strings,int sortType){
        for (int i = 0; i < strings.size() - 1; i++){
            for (int j = 0; j < strings.size() - i - 1; j++){
                if (sortType == 0 ? (Integer.parseInt(strings.get(j)[0].trim()) > Integer.parseInt(strings.get(j + 1)[0].trim())) : (Integer.parseInt(strings.get(j)[0].trim()) < Integer.parseInt(strings.get(j + 1)[0].trim()))){
                    String[] temp = strings.get(j);
                    strings.set(j,strings.get(j + 1));
                    strings.set(j+1,temp);
                }
            }
        }
    }

    public List<City> getCities(){
        return this.cities;
    }

    public Map<Integer,String> getCityMap(){
        return this.cityMap;
    }

    public int getCount(){
        return this.count;
    }

    public List<String[]> getStringOfArrays(){
        return this.stringOfArrays;
    }

}
