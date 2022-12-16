package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CityRepository {
    private final CityService cityService;
    private final Connection connection;
    public CityRepository(CityService cityService,Connection connection) {
        this.cityService = cityService;
        this.connection = connection;
    }

    public void createTableAndInsertData(){
        try {
            Statement statement = connection.createStatement();
            if (isTableExist("cities")){
                statement.execute("DROP TABLE CITIES");
            }
            statement.execute("CREATE TABLE cities(id int not null generated always as identity,city_code CHAR(20),city_name VARCHAR(255))");
            System.out.println("Cities table created!");
            int rows = 0;
            for (City city : cityService.getCities()){
                String sql = "INSERT INTO cities (city_code,city_name) VALUES ('" + city.getCode() + "','" + city.getName().replace("'","''") + "')";
                statement.execute(sql);
                System.out.println(sql);
                rows++;
            }
            System.out.println(rows + " rows created!");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<City> getCityListSorted(int choice){
        String query;
        if (choice == 1){
            query = "select city_code,city_name from cities order by city_code asc";
        }else {
            query = "select city_code,city_name from cities order by city_name asc";
        }
        List<City> cities = new ArrayList<>();
        try {
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                String cityCode = (String) rs.getObject("city_code");
                cities.add(new City(Integer.parseInt(cityCode.trim()),(String) rs.getObject("city_name")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return cities;
    }

    public boolean isTableExist(String sTablename) throws SQLException{

            DatabaseMetaData dbmd = connection.getMetaData();
            ResultSet rs = dbmd.getTables(null, null, sTablename.toUpperCase(),null);
            if(rs.next())
            {
                System.out.println("Table "+rs.getString("TABLE_NAME")+" already exists !!");
                return true;
            }
            else
            {
                return false;
            }

    }
}
