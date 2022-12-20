package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

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
    private int count;
    public CityService() {
        cities = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(new FileReader(Paths.get("file/myFile0.csv").toAbsolutePath().toString()));
            String[] nextLine;
            int index = 0;
            while ((nextLine = reader.readNext()) != null){
                if (index > 0){
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
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
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

}
