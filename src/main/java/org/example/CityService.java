package org.example;

import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvValidationException;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CityService {

    private List<City> cities;

    public CityService(String filePath) {
        cities = new ArrayList<>();
        try {
            CSVReader reader = new CSVReader(new FileReader(filePath));
            String[] nextLine;
            int index = 0;
            while ((nextLine = reader.readNext()) != null){
                if (index > 0) cities.add(new City(Integer.parseInt(nextLine[0]),nextLine[1]));
                index++;
            }
        } catch (IOException | CsvValidationException e) {
            throw new RuntimeException(e);
        }
    }

    public List<City> getCities(){
        return this.cities;
    }

}
