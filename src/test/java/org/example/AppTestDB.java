package org.example;

import junit.framework.TestCase;

import java.io.File;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class AppTestDB extends TestCase {


    public void test() {
        CityService cityService = new CityService();
        try {
            Connection connection = DriverManager.getConnection("jdbc:derby:task-1;create=true");
            CityRepository cityRepository = new CityRepository(cityService,connection);
            cityRepository.createTableAndInsertData();
            printCities(cityRepository.getCityListSorted(1));
            printCities(cityRepository.getCityListSorted(2));
        } catch (
                SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void printCities(List<City> cities){
        cities.forEach(city -> {
            System.out.println(city.getCode() + "," + city.getName());
        });
    }
}
