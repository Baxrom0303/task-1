package org.example;

import junit.framework.TestCase;

import java.io.InputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.List;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{

    public void test() {
        Path path = Paths.get("file/myFile0.csv");
        CityService cityService = new CityService(path.toAbsolutePath().toString());
        List<City> cities = cityService.getCities();
        cities.sort(Comparator.comparing(City::getName));
        printCities(cities);
        cities.sort(Comparator.comparing(City::getCode));
        printCities(cities);
    }

    private void printCities(List<City> cities){
        cities.forEach(city -> {
            System.out.println(city.getCode() + "," + city.getName());
        });
    }
}
