package org.example;

import junit.framework.TestCase;

import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    private CityService cityService = new CityService();
    List<City> cities = cityService.getCities();
    public void testA(){
        cities.sort(Comparator.comparing(City::getName));
        printCities(cities);
    }

    public void testB(){
        cities.sort(Comparator.comparing(City::getCode));
        printCities(cities);
    }

    public void testC(){
        Map<Integer,String> cityMap = cityService.getCityMap();
        for (Map.Entry<Integer,String> entry : cityMap.entrySet()){
            System.out.println(entry.getKey() + " - " + entry.getValue());
        }
    }

    public void testCityCodeDuplicateCount(){
        System.out.println(cityService.getCount());
    }

    private void printCities(List<City> cities){
        cities.forEach(city -> {
            System.out.println(city.getCode() + "," + city.getName());
        });
    }
}
